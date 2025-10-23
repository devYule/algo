import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			int T=Integer.parseInt(br.readLine());

			for(int r=0; r<T; r++) {
				if(r!=0) bw.write("\n");

				StringTokenizer st=new StringTokenizer(br.readLine());
				int n=Integer.parseInt(st.nextToken());
				int m=Integer.parseInt(st.nextToken());

				int[][] edge=new int[m][3];
				for(int i=0; i<m; i++) {
					st=new StringTokenizer(br.readLine());
					edge[i][0]=Integer.parseInt(st.nextToken());
					edge[i][1]=Integer.parseInt(st.nextToken());
					edge[i][2]=Integer.parseInt(st.nextToken());
				}

				bw.write(
					String.valueOf(
						new Main().resolve(
							n+1, m, edge
						)
					)
				);
			}
			
			bw.flush();
		}
	}

	int V, first[], nxt[], map[], wt[], ai;
	int m;
	final int INF=(int)1e9;

	int resolve(int n, int m, int[][] edge) {
		this.m=m;

		int[] ind=init(n, edge);
		int[] cost=new int[V];

		for(int i=2; i<n; i++) if(ind[i]==1) cost[i]=INF;

		Queue<Integer> q=new ArrayDeque<>();
		int[] parent=new int[V];
		q.add(1);
		parent[1]=1;
		int[] order=new int[V*2];
		int oi=0;

		while(!q.isEmpty()) {
			int cur=q.poll();
			if(cost[cur]==0) order[oi++]=cur;

			for(int i=first[cur]; i!=-1; i=nxt[i]) {
				int next=map[i];
				if(parent[next]!=0) continue;
				parent[next]=cur;
				q.add(next);
			}
		}

		for(int i=oi-1; i>=0; i--) {
			int cur=order[i];

			int best=0;
			for(int j=first[cur]; j!=-1; j=nxt[j]) {
				int next=map[j];
				best+=Math.min(wt[j], cost[next]);
			}
			cost[cur]=best;
		}

		return cost[1];
	}

	int[] init(int v, int[][] edge) {
		this.V=v;
		this.first=new int[v];
		Arrays.fill(first, -1);
		this.nxt=new int[m*2];
		this.map=new int[m*2];
		this.wt=new int[m*2];
		this.ai=0;

		int[] ind=new int[v];

		for(int[] e: edge) {
			int a=e[0], b=e[1], w=e[2];

			map[ai]=b;
			wt[ai]=w;
			nxt[ai]=first[a];
			first[a]=ai++;

			map[ai]=a;
			wt[ai]=w;
			nxt[ai]=first[b];
			first[b]=ai++;

			ind[a]++; ind[b]++;
		}
		return ind;
	}

}
