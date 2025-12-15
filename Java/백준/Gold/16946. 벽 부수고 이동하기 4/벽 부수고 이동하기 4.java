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
		int[] distPerId=new int[n*m+1];
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
				distPerId[id]=dist%10;

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
				int ida=0, idb=0, idc=0, idd=0;

				for(int k=0; k<4; k++) {
					int ny=i+rg[k];
					int nx=j+cg[k];
					if(ny>=0 && nx>=0 && ny<n && nx<m && map[ny][nx]!=1) {
						int cid=ids[ny][nx];
						if(cid==ida || cid==idb || cid==idc || cid==idd) {
							continue;
						}
						if(ida==0) ida=cid;
						else if(idb==0) idb=cid;
						else if(idc==0) idc=cid;
						else idd=cid;
					}
				}

				l+=distPerId[ida]+distPerId[idb]+distPerId[idc]+distPerId[idd];
				sb.append(String.valueOf(l%10));
			}
		}
		return sb.toString();
	}
}
