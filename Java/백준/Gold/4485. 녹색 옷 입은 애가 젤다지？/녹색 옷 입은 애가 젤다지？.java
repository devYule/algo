import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {


			int pi=1;
			while(true) {
				
				int n=Integer.parseInt(br.readLine());
				if(n==0) break;
				if(pi!=1) bw.write("\n");

				int[][] map=new int[n][n];
				for(int i=0; i<n; i++) {
					StringTokenizer st=new StringTokenizer(br.readLine());
					for(int j=0; j<n; j++) {
						map[i][j]=Integer.parseInt(st.nextToken());
					}
				}

				bw.write("Problem "+ pi + ": "+
					String.valueOf(
						new Main().resolve(
							n, map
						)
					)
				);
				pi++;
			}
			bw.flush();
		}
	}

	int resolve(int n, int[][] map) {

		final int[] rg={-1, 0, 1, 0};
		final int[] cg={0, 1, 0, -1};

		final int[][] dist=new int[n][n];
		for(int i=0; i<n; i++) Arrays.fill(dist[i], (int)1e9);
		PriorityQueue<int[]> q=new PriorityQueue<>((a, b) -> dist[a[0]][a[1]]-dist[b[0]][b[1]]);
		q.add(new int[] {0, 0});
		dist[0][0]=map[0][0];
		while(!q.isEmpty()) {
			int[] curs=q.poll();
			int y=curs[0];
			int x=curs[1];
			int cost=dist[y][x];

			for(int i=0; i<4; i++) {
				int ny=y+rg[i];
				int nx=x+cg[i];
				if(ny>=0 && nx>=0 && ny<n && nx<n) {
					if(dist[ny][nx]>cost+map[ny][nx]) {
						dist[ny][nx]=cost+map[ny][nx];
						q.add(new int[] {ny, nx});
					}
				}
			}
		}
		return dist[n-1][n-1];
	}

}
