import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			int n=Integer.parseInt(br.readLine());

			char[][] p=new char[5][];
			for(int i=0; i<5; i++) p[i]=br.readLine().toCharArray();

			bw.write(
				String.valueOf(
					new Main().resolve(
						n, p
					)
				)
			);
			bw.flush();
		}
	}

	int[][] ALL;

	String resolve(int n, char[][] P) {
		init();
		int block=n-1;

		int[] cnts=new int[n];
		int[] nums=new int[n];
		while(block>=0) {
			int cur=0;
			int locCnt=0;
			for(int i=0; i<10; i++) {
				boolean can=true;
				for(int row=0; row<5; row++) {
					int col=block*4;
					int line=0;
					for(int right=0; right<3; right++) {
						if(P[row][col+right]=='#') {
							line|=1<<(2-right);
						}
					}					
					if((ALL[i][row]|line)!=ALL[i][row]) { can=false; break; }
				}
				if(can) { 
					locCnt++;
					cur+=i;
				}
			}
			cnts[block]=locCnt;
			nums[block]=cur;
			block--;
		}

		for(int i=0; i<n; i++) if(cnts[i]==0) return "-1";

		int place=1;
		double sum=0;
		for(int i=n-1; i>=0; i--) {
			sum+=place*(nums[i]/(double)cnts[i]);
			place*=10;
		}

		return String.valueOf(sum);
	}


	void init() {
		this.ALL=new int[10][5];
		String[] base={
			"###...#.###.###.#.#.###.###.###.###.###",
			"#.#...#...#...#.#.#.#...#.....#.#.#.#.#",
			"#.#...#.###.###.###.###.###...#.###.###",
			"#.#...#.#.....#...#...#.#.#...#.#.#...#",
			"###...#.###.###...#.###.###...#.###.###"
		};
		int num=0;
		while(num<10) {
			int j=num*4;
			for(int i=0; i<5; i++) {
				for(int k=0; k<3; k++) {
					if(base[i].charAt(j+k)=='#') {
						ALL[num][i]|=(1<<(2-k));
					}
				}
			}
			num++;
		}
	}
}
