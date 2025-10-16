import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			StringTokenizer st=new StringTokenizer(br.readLine());

			int n=Integer.parseInt(st.nextToken());
			int m=Integer.parseInt(st.nextToken());
			int[][] map=new int[n][m];
			for(int i=0; i<n; i++) {
				st=new StringTokenizer(br.readLine());
				for(int j=0; j<m; j++) {
					map[i][j]=Integer.parseInt(st.nextToken());
				}
			}

			bw.write(
				String.valueOf(
					new Main().resolve(
						n, m, map
					)
				)
			);
			bw.flush();
		}
	}

	int n, m, map[][], memo[][];
	int[] rg={-1, 0, 1, 0};
	int[] cg={0, 1, 0, -1};
	int resolve(int n, int m, int[][] map) {
		this.n=n; this.m=m; this.map=map;
		this.memo=new int[n][m];
		for(int i=0; i<n; i++) Arrays.fill(memo[i], -1);
		return find(0, 0);
	}

	int find(int y, int x) {
		if(y==n-1 && x==m-1) return 1;
		if(memo[y][x]!=-1) return memo[y][x];

		int ret=0;
		for(int i=0; i<4; i++) {
			int ny=y+rg[i];
			int nx=x+cg[i];
			if(valid(ny, nx) && map[y][x]>map[ny][nx]) {
				ret+=find(ny, nx);
			}
		}
		return memo[y][x]=ret;
	}
	boolean valid(int y, int x) {
		return y>=0 && x>=0 && y<n && x<m;
	}
}
