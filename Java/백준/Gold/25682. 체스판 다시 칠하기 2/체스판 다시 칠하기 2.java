import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			StringTokenizer st=new StringTokenizer(br.readLine());
			int n=Integer.parseInt(st.nextToken());
			int m=Integer.parseInt(st.nextToken());
			int k=Integer.parseInt(st.nextToken());

			char[][] map=new char[n][];
			for(int i=0; i<n; i++) {
				map[i]=br.readLine().toCharArray();
			}

			bw.write(
				String.valueOf(
					new Main().resolve(
						n, m, k, map
					)
				)
			);
			bw.flush();
		}
	}

	int[] rg={-1, 0, 1, 0};
	int[] cg={0, 1, 0, -1};
	int resolve(int n, int m, int k, char[][] map) {
		int[][] bstart=new int[n][m];
		int[][] wstart=new int[n][m];

		Queue<int[]> q=new ArrayDeque<>();
		boolean[][] vis=new boolean[n][m];
		q.add(new int[] {0, 0});
		while(!q.isEmpty()) {
			int[] curs=q.poll();
			int y=curs[0], x=curs[1];
			char color=map[y][x];
			int loc=y+x;
			if(loc%2==0) {
				bstart[y][x]=color=='B' ? 0 : 1;
				wstart[y][x]=color=='W' ? 0 : 1;
			} else {
				bstart[y][x]=color=='W' ? 0 : 1;
				wstart[y][x]=color=='B' ? 0 : 1;
			}
			for(int i=0; i<4; i++) {
				int ny=y+rg[i];
				int nx=x+cg[i];
				if(ny>=0 && nx>=0 && ny<n && nx<m && !vis[ny][nx]) {
					q.add(new int[] {ny, nx});
					vis[ny][nx]=true;
				}
			}
		}
		for(int i=0; i<n; i++) {
			for(int j=1; j<m; j++) {
				bstart[i][j]+=bstart[i][j-1];
				wstart[i][j]+=wstart[i][j-1];
			}
		}

		for(int i=1; i<n; i++) {
			for(int j=0; j<m; j++) {
				bstart[i][j]+=bstart[i-1][j];
				wstart[i][j]+=wstart[i-1][j];
			}
		}

		int ret=Math.min(bstart[n-1][m-1], wstart[n-1][m-1]);
		for(int i=k-1; i<n; i++) {
			for(int j=k-1; j<m; j++) {
				int tmp=(int)1e9;
				if(i-k>=0 && j-k>=0) {
					tmp=Math.min(
						bstart[i][j]-bstart[i-k][j]-bstart[i][j-k]+bstart[i-k][j-k],
						wstart[i][j]-wstart[i-k][j]-wstart[i][j-k]+wstart[i-k][j-k]
					);
				} else if(i-k>=0) {
					tmp=Math.min(bstart[i][j]-bstart[i-k][j], wstart[i][j]-wstart[i-k][j]);
				} else if(j-k>=0) {
					tmp=Math.min(bstart[i][j]-bstart[i][j-k], wstart[i][j]-wstart[i][j-k]);
				}
				ret=Math.min(ret, tmp);
			}
		}

		return ret;
	}

}
