import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			StringTokenizer st=new StringTokenizer(br.readLine());
			int n=Integer.parseInt(st.nextToken());
			int s=Integer.parseInt(st.nextToken());
			int d=Integer.parseInt(st.nextToken());

			int[][] edge=new int[n-1][2];

			for(int i=0; i<n-1; i++) {
				st=new StringTokenizer(br.readLine());
				edge[i][0]=Integer.parseInt(st.nextToken());
				edge[i][1]=Integer.parseInt(st.nextToken());
			}

			bw.write(
				String.valueOf(
					new Main().resolve(
						n, s, d, edge
					)
				)
			);
			bw.flush();
		}
	}

	List<Integer>[] adj;
	int V;


	int resolve(int n, int s, int d, int[][] edge) {
		n++;
		init(n, edge);

		List<Integer> order=new ArrayList<>();
		Queue<Integer> q=new ArrayDeque<>();
		int[] parent=new int[n];
		q.add(s);
		parent[s]=s;
		int[] ch=new int[V];
		Arrays.fill(ch, (int)1e9);
		while(!q.isEmpty()) {
			int cur=q.poll();
			order.add(cur);
			if(cur!=s && adj[cur].size()==1) ch[cur]=d;
			for(int next: adj[cur]) {
				if(parent[next]!=0) continue;
				parent[next]=cur;
				q.add(next);
			}
		}

		Collections.reverse(order);
		int[] dist=new int[V];
		for(int cur: order) {
			for(int par: adj[cur]) {
				if(par!=parent[cur]) continue;
				ch[par]=Math.min(ch[par], ch[cur]==0 ? 0 : ch[cur]-1);
				dist[par]+=dist[cur]+(ch[cur]==0 ? 2 : 0);
			}
		}
		return dist[s];
	}


	@SuppressWarnings("unchecked")
	void init(int n, int[][] edge) {
		this.V=n;
		this.adj=new ArrayList[V];
		for(int i=0; i<V; i++) adj[i]=new ArrayList<>();
		for(int[] e: edge) {
			adj[e[0]].add(e[1]);
			adj[e[1]].add(e[0]);
		}
	}

}
