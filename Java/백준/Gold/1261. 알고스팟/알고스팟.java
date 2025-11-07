import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			StringTokenizer st=new StringTokenizer(br.readLine());
			int m=Integer.parseInt(st.nextToken());
			int n=Integer.parseInt(st.nextToken());

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
						n, m, map
					)
				)
			);
			bw.flush();
		}
	}

	int resolve(int n, int m, int[][] map) {
		PriorityQueue<int[]> q=new PriorityQueue<>((a, b) -> a[0]-b[0]);
		int[][] dist=new int[n][m];
		for(int i=0; i<n; i++) Arrays.fill(dist[i], (int)1e9);
		q.add(new int[] {0, 0, 0});
		dist[0][0]=0;

		int[] rg={-1, 0, 1, 0};
		int[] cg={0, 1, 0, -1};
		while(!q.isEmpty()) {
			int[] as=q.poll();
			int y=as[1];
			int x=as[2];
			int cost=as[0];

			if(dist[y][x]!=cost) continue;

			for(int i=0; i<4; i++) {
				int ny=y+rg[i];
				int nx=x+cg[i];
				if(ny>=0 && nx>=0 && ny<n && nx<m) {
					int nextCost=cost+map[ny][nx];
					if(dist[ny][nx]>nextCost) {
						dist[ny][nx]=nextCost;
						q.add(new int[] {nextCost, ny, nx});
					}
				}
			}
		}

		return dist[n-1][m-1];
	}

}
