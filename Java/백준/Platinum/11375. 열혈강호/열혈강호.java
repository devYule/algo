import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			StringTokenizer st=new StringTokenizer(br.readLine());

			int n=Integer.parseInt(st.nextToken());
			int m=Integer.parseInt(st.nextToken());

			int[][] edge=new int[n+1][];

			for(int i=1;i<=n;i++) {
				st=new StringTokenizer(br.readLine());
				int size=Integer.parseInt(st.nextToken());
				edge[i]=new int[size];
				for(int j=0;j<size;j++) {
					edge[i][j]=Integer.parseInt(st.nextToken());
				}
			}

			bw.write(
				String.valueOf(
					new Main().resolve(
						n, m, edge
					)
				)
			);
			bw.flush();
		}
	}

	int resolve(int n, int m, int[][] edge) {
		return new Match(n, m, edge).getMatch();
	}

	class Match {
		List<Integer>[] adj;
		int ma[], mb[], dist[], na, nb;

		@SuppressWarnings("unchecked")
		Match(int na, int nb, int[][] edge) {
			this.na=na;
			this.nb=nb;
			this.ma=new int[na+1];
			this.mb=new int[nb+1];
			this.dist=new int[na+1];

			this.adj=new ArrayList[na+1];

			for(int i=1; i<=na; i++) adj[i]=new ArrayList<>();

			for(int i=1; i<edge.length; i++) {
				for(int j=0; j<edge[i].length; j++) {
					adj[i].add(edge[i][j]);
				}
			}
		}

		boolean bfs() {
			Arrays.fill(dist, -1);
			Queue<Integer> q=new ArrayDeque<>();
			for(int i=1; i<=na; i++) if(ma[i]==0) {
				dist[i]=0;
				q.add(i);
			}

			boolean cross=false;
			while(!q.isEmpty()) {
				int a=q.poll();
				for(int b: adj[a]) {
					int matcha=mb[b];
					if(matcha==0) cross=true;
					else if(dist[matcha]==-1) {
						dist[matcha]=dist[a]+1;
						q.add(matcha);
					}
				}
			}
			return cross;
		}

		boolean dfs(int a) {
			for(int b: adj[a]) {
				int matcha=mb[b];
				if(matcha==0 || (dist[matcha]==dist[a]+1 && dfs(matcha))) {
					ma[a]=b;
					mb[b]=a;
					return true;
				}
			}
			dist[a]=-1;
			return false;
		}

		int getMatch() {
			int ret=0;
			while(bfs()) {
				for(int a=1; a<=na; a++) {
					if(ma[a]==0 && dfs(a)) ret++;
				}
			}
			return ret;
		}
	}

}
