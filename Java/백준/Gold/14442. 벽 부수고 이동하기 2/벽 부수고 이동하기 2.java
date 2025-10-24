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
				String line=br.readLine();
				for(int j=0; j<m; j++) {
					map[i][j]=line.charAt(j)-'0';
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

	int resolve(int n, int m, int k, int[][] map) {

		final int INF=(int)1e9;
		final int[] rg={-1, 0, 1, 0};
		final int[] cg={0, 1, 0, -1};

		int[][][] dist=new int[n][m][k+1];
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) Arrays.fill(dist[i][j], INF);
		}
		Queue<int[]> q=new ArrayDeque<>();

		q.add(new int[] {0, 0, k});
		dist[0][0][k]=1;

		while(!q.isEmpty()) {
			int[] curs=q.poll();
			int y=curs[0];
			int x=curs[1];
			int c=curs[2];
			int cost=dist[y][x][c];
			for(int i=0; i<4; i++) {
				int ny=y+rg[i];
				int nx=x+cg[i];
				if(ny>=0 && nx>=0 && ny<n && nx<m) {
					int nextDist=cost+1;
					int restCh=c-map[ny][nx];
					if(restCh<0) continue;
					if(dist[ny][nx][restCh]>nextDist) {
						dist[ny][nx][restCh]=nextDist;
						q.add(new int[] {ny, nx, restCh});
					}
				}
			}
		}

		int ret=INF;
		for(int i=0; i<=k; i++) {

			ret=Math.min(ret, dist[n-1][m-1][i]);
		}
		return ret==INF ? -1 : ret;
	}

}
