import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			StringTokenizer st=new StringTokenizer(br.readLine());
			int n=Integer.parseInt(st.nextToken());
			int m=Integer.parseInt(st.nextToken());
			int[][] each=new int[n][2];
			for(int i=0; i<n; i++) {
				st=new StringTokenizer(br.readLine());
				each[i][0]=Integer.parseInt(st.nextToken());
				each[i][1]=Integer.parseInt(st.nextToken());
			}

			bw.write(
				String.valueOf(
					new Main().resolve(
						n, m, each
					)
				)
			);
			bw.flush();
		}
	}

	int n, each[][];
	int[] best;
	int resolve(int n, int t, int[][] each) {
		this.n=n; this.each=each; this.best=new int[t+1];
		Arrays.fill(this.best, (int)1e9);
		int sum=Arrays.stream(each).mapToInt(e->e[1]).sum();
		return find(t, sum, new boolean[each.length]);
	}
	int find(int rest, int pay, boolean[] solved) {
		if(pay==0) return 0;
		if(rest==0) return pay;
		if(best[rest]<=pay) return (int)1e9;
		best[rest]=pay;

		int ret=(int)1e9;
		for(int i=0; i<n; i++) {
			if(solved[i] || rest-each[i][0]<0) continue;
			solved[i]=true;
			ret=Math.min(ret, find(rest-each[i][0], pay-each[i][1], solved));
			if(ret==0) return 0;
			solved[i]=false;
		}
		if(ret==(int)1e9) return pay;
		return ret;
	}
}
