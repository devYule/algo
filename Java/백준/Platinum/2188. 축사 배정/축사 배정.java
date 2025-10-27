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

			int E=0;
			for(int i=1; i<=n; i++) {
				st=new StringTokenizer(br.readLine());
				int size=Integer.parseInt(st.nextToken());
				edge[i]=new int[size];
				E+=size;
				for(int j=0; j<size; j++) {
					edge[i][j]=Integer.parseInt(st.nextToken());
				}
			}

			bw.write(
				String.valueOf(
					new Main().resolve(
						n, m, E, edge
					)
				)
			);
			bw.flush();
		}
	}

	int resolve(int n, int m, int E, int[][] edge) {
		return new Match(n, m, E, edge).getMatch();
	}

	static class Match {
		int head[], nxt[], to[], ei;
		int ma[], mb[], dist[];
		int na, nb;

		Match(int na, int nb, int E, int[][] edge) {
			this.na=na;
			this.nb=nb;
			this.head=new int[na+1];
			Arrays.fill(head, -1);
			this.nxt=new int[E];
			this.to=new int[E];
			this.ei=0;

			this.ma=new int[na+1];
			this.mb=new int[nb+1];
			this.dist=new int[na+1];

			for(int a=1; a<=na; a++) {
				for(int j=0; j<edge[a].length; j++) {
					int b=edge[a][j];
					to[ei]=b;
					nxt[ei]=head[a];
					head[a]=ei++;
				}
			}
		}

		boolean bfs() {
			Arrays.fill(dist, -1);
			Queue<Integer> q=new ArrayDeque<>();
			for(int i=1; i<=na; i++) {
				if(ma[i]==0) {
					q.add(i);
					dist[i]=0;
				}
			}
			boolean reachable=false;
			while(!q.isEmpty()) {
				int a=q.poll();
				for(int i=head[a]; i!=-1; i=nxt[i]) {
					int b=to[i];
					int matcha=mb[b];
					if(matcha==0) reachable=true;
					else if(dist[matcha]==-1) {
						dist[matcha]=dist[a]+1;
						q.add(matcha);
					}
				}
			}
			return reachable;
		}

		boolean dfs(int a) {
			for(int i=head[a]; i!=-1; i=nxt[i]) {
				int b=to[i];
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
				for(int i=1; i<=na; i++) {
					if(ma[i]==0 && dfs(i)) ret++;
				}
			}
			return ret;
		}

	}
}
