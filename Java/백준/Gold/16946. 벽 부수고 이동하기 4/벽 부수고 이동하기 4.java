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


	String resolve(int n, int m, int[][] map) {
		int[] rg=new int[] {-1, 0, 1, 0};
		int[] cg=new int[] {0, 1, 0, -1};
		int id=1;
		int[][] ids=new int[n][m];
		Map<Integer, Integer> distPerId=new HashMap<>();
		boolean[][] vis=new boolean[n][m];
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				if(map[i][j]==1 || vis[i][j]) continue;
				
				int dist=0;
				ArrayDeque<int[]> q=new ArrayDeque<>();
				q.add(new int[] {i, j});
				vis[i][j]=true;
				while(!q.isEmpty()) {
					int[] as=q.removeFirst();
					int ay=as[0], ax=as[1];
					ids[ay][ax]=id;
					dist++;
					for(int k=0; k<4; k++) {
						int ny=ay+rg[k];
						int nx=ax+cg[k];
						if(ny>=0 && nx>=0 && ny<n && nx<m && map[ny][nx]==0 && !vis[ny][nx]) {
							q.add(new int[] {ny, nx});
							vis[ny][nx]=true;
						}
					}
				}
				distPerId.put(id, dist%10);

				id++;
			}
		}


		StringBuilder sb=new StringBuilder();
		for(int i=0; i<n; i++) {
			if(i!=0) sb.append("\n");
			for(int j=0; j<m; j++) {
				if(map[i][j]==0) {
					sb.append(0);
					continue;
				}
				int l=1;
				Set<Integer> idSet=new HashSet<>();
				for(int k=0; k<4; k++) {
					int ny=i+rg[k];
					int nx=j+cg[k];
					if(ny>=0 && nx>=0 && ny<n && nx<m && map[ny][nx]!=1) {
						idSet.add(ids[ny][nx]);
					}
				}

				l+=idSet.stream().mapToInt(it->distPerId.get(it)).sum();
				
				sb.append(String.valueOf(l%10));
			}
		}
		return sb.toString();
	}
}
