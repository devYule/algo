import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			String[] split=br.readLine().split("\\s+");

			int y=Integer.parseInt(split[0]);
			int x=Integer.parseInt(split[1]);
			char[][] map=new char[y][];
			for(int i=0; i<y; i++) {
				map[i]=br.readLine().toCharArray();
			}

			bw.write(
				String.valueOf(
					new Main().resolve(y, x, map)
				)
			);
			bw.flush();
		}
	}

	char[][] map;
	int[][][] memo;
	int resolve(int N, int M, char[][] map) {
		this.map=map;
		this.memo=new int[N][M-7][2];
		int ret=(int)1e9;
		for(int i=0; i<N; i++) for(int j=0; j<M-7; j++) Arrays.fill(memo[i][j], -1);
		for(int i=0; i<N-7; i++) {
			for(int j=0; j<M-7; j++) {
				int type0=0;
				int type1=0;
				int toggle=0;
				for(int k=0; k<8; k++) {
					type0+=find(i+k, j, toggle);
					type1+=find(i+k, j, toggle=(toggle+1)%2);
				}
				ret=Math.min(ret, Math.min(type0, type1));
			}
		}
		return ret;
	}

	int find(int y, int x, int type) {
		if(memo[y][x][type]!=-1) return memo[y][x][type];
		int t=type;
		int cnt=0;
		for(int i=0; i<8; i++) {
			char expect=t==0?'B':'W';
			if(map[y][x+i]!=expect) cnt++;
			t=(t+1)%2;
		}
		return memo[y][x][type]=cnt;
	}
}