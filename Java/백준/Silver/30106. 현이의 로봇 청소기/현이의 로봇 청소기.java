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
						n, m, k, map
					)
				)
			);
			bw.flush();
		}
	}

	final int[] rg={-1, 0, 1, 0};
	final int[] cg={0, 1, 0, -1};

	int resolve(int n, int m, int k, int[][] map) {
		boolean[][] vis=new boolean[n][m];
		int ret=0;
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				if(vis[i][j]) continue;
				ret++;
				Queue<int[]> q=new ArrayDeque<>();
				q.add(new int[] {i, j});
				while(!q.isEmpty()) {
					int[] curs=q.poll();
					int cury=curs[0];
					int curx=curs[1];
					for(int l=0; l<4; l++) {
						int ny=cury+rg[l];
						int nx=curx+cg[l];
						if(ny>=0 && nx>=0 && ny<n && nx<m && Math.abs(map[ny][nx]-map[cury][curx])<=k && !vis[ny][nx]) {
							vis[ny][nx]=true;
							q.add(new int[] {ny, nx});
						}
					}
				}
			}
		}
		return ret;
	}
}
