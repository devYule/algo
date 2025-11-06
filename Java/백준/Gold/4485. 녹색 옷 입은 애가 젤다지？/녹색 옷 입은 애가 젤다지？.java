import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			int round=1;
			while(true) {
				int n=Integer.parseInt(br.readLine());
				if(n==0) break;
				if(round>1) bw.write("\n");

				int[][] map=new int[n][n];
				for(int i=0; i<n; i++) {
					StringTokenizer st=new StringTokenizer(br.readLine());
					for(int j=0; j<n; j++) {
						map[i][j]=Integer.parseInt(st.nextToken());
					}
				}
				bw.write("Problem " + round + ": " +
					String.valueOf(
						new Main().resolve(
							n, map
						)
					)
				);
				round++;
			}

			
			bw.flush();
		}
	}

	int resolve(int n, int[][] map) {
		int[] rg={-1, 0, 1, 0};
		int[] cg={0, 1, 0, -1};

		PriorityQueue<int[]> q=new PriorityQueue<>((a, b) -> a[0]-b[0]);
		int[][] dist=new int[n][n];
		for(int i=0; i<n; i++) Arrays.fill(dist[i], (int)1e9);
		dist[0][0]=map[0][0];
		q.add(new int[] {dist[0][0], 0, 0});

		while(!q.isEmpty()) {
			int[] curs=q.poll();
			int cy=curs[1];
			int cx=curs[2];
			int cost=curs[0];

			if(dist[cy][cx]!=cost) continue;

			for(int i=0; i<4; i++) {
				int ny=cy+rg[i];
				int nx=cx+cg[i];
				if(ny>=0 && nx>=0 && ny<n && nx<n) {
					int nextDist=cost+map[ny][nx];
					if(dist[ny][nx]>nextDist) {
						dist[ny][nx]=nextDist;
						q.add(new int[] {nextDist, ny, nx});
					}
				}
			}
		}
		return dist[n-1][n-1];
	}
}
