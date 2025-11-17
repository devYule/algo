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

	int[] rg={-1, 0, 1, 0};
	int[] cg={0, 1, 0, -1};

	int resolve(int n, int m, int[][] map) {
		int nis=1;
		boolean[][] vis=new boolean[n][m];
		ArrayDeque<int[]> q=new ArrayDeque<>();
		for(int i=0; i<n; i++) for(int j=0; j<m; j++) {
			if(map[i][j]==0 || vis[i][j]) continue;
			q.add(new int[] {i, j});
			while(!q.isEmpty()) {
				int[] as=q.removeFirst();
				int ay=as[0];
				int ax=as[1];
				map[ay][ax]=nis;
				vis[ay][ax]=true;
				for(int k=0; k<4; k++) {
					int ny=ay+rg[k];
					int nx=ax+cg[k];
					if(ny>=0 && nx>=0 && ny<n && nx<m && map[ny][nx]==1 && !vis[ny][nx]) {
						vis[ny][nx]=true;
						q.add(new int[] {ny, nx});
					}
				}
			}
			nis++;
		}

		int[][] dist=new int[nis][nis];
		for(int i=0; i<nis; i++) Arrays.fill(dist[i], (int)1e9);
		for(int i=0; i<n; i++) for(int j=0; j<m; j++) {
			if(map[i][j]==0) continue;
			int a=map[i][j];
			int cy=i+1;
			while(cy<n) {
				if(map[cy][j]==map[i][j]) break;
				if(map[cy][j]!=0) {
					int b=map[cy][j];
					int gap=cy-i-1;
					if(gap>1 && dist[a][b]>gap) {
						dist[a][b]=gap;
						dist[b][a]=gap;
					}
					break;
				}
				cy++;
			}
			int cx=j+1;
			while(cx<m) {
				if(map[i][cx]==map[i][j]) break;
				if(map[i][cx]!=0) {
					int b=map[i][cx];
					int gap=cx-j-1;
					if(gap>1 && dist[a][b]>gap) {
						dist[a][b]=gap;
						dist[b][a]=gap;
					}
					break;
				}
				cx++;
			}
		}


		List<int[]> edge=new ArrayList<>();
		for(int i=1; i<nis; i++) {
			for(int j=i+1; j<nis; j++) {
				if(dist[i][j]==(int)1e9) continue;
				edge.add(new int[] {i, j, dist[i][j]});
			}
		}

		edge.sort((a, b) -> a[2]-b[2]);

		int ret=0;
		DSU dsu=new DSU(nis);
		for(int[] e: edge) {
			int a=e[0], b=e[1], d=e[2];
			int fa=dsu.find(a);
			int fb=dsu.find(b);
			if(fa==fb) continue;
			dsu.union(a, b);
			ret+=d;
		}

		int exp=dsu.find(1);
		for(int i=2; i<nis; i++) {
			if(dsu.find(i)!=exp) return -1;
		}

		return ret;
	} 

	class DSU {
		int n, uf[], rank[];
		DSU(int n) {
			this.n=n;
			this.uf=new int[n];
			this.rank=new int[n];
			for(int i=1; i<n; i++) uf[i]=i;
		}

		int find(int a) {
			if(uf[a]==a) return a;
			return uf[a]=find(uf[a]);
		}

		void union(int a, int b) {
			int fa=find(a), fb=find(b);
			if(fa==fb) return;
			if(rank[fa]<rank[fb]) {
				int tmp=fa;
				fa=fb;
				fb=tmp;
			}

			if(rank[fa]==rank[fb]) rank[fa]++;

			uf[fb]=fa;
		}
	}

}
