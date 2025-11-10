import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			int n=Integer.parseInt(br.readLine());

			int[][] map=new int[n][n];
			for(int i=0; i<n; i++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				for(int j=0; j<n; j++) {
					map[i][j]=Integer.parseInt(st.nextToken());
				}
			}

			bw.write(
				String.valueOf(
					new Main().resolve(
						n, map
					)
				)
			);
			bw.flush();
		}
	}

	int map[][], n, memo[][];
	int[] rg={-1, 0, 1, 0};
	int[] cg={0, 1, 0, -1};
	int resolve(int n, int[][] map) {
		this.map=map; this.n=n;
		this.memo=new int[n][n];
		for(int i=0; i<n; i++) Arrays.fill(memo[i], -1);

		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				if(memo[i][j]==-1) find(i, j);
			}
		}

		int ret=0;
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				ret=Math.max(ret, memo[i][j]);
			}
		}
		return ret;
	}

	int find(int y, int x) {
		if(memo[y][x]!=-1) return memo[y][x];

		int ret=1;
		int cur=map[y][x];
		for(int i=0; i<4; i++) {
			int ny=y+rg[i];
			int nx=x+cg[i];
			if(valid(ny, nx) && map[ny][nx]>cur) {
				ret=Math.max(ret, find(ny, nx)+1);
			}
		}
		return memo[y][x]=ret;
	}

	boolean valid(int y, int x) {
		return y>=0 && x>=0 && y<n && x<n;
	}
}
