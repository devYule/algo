import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			int n=Integer.parseInt(br.readLine());
			int[] p=new int[n];
			StringTokenizer st=new StringTokenizer(br.readLine());

			for(int i=0; i<n; i++) p[i]=Integer.parseInt(st.nextToken());

			int[][] edge=new int[n-1][2];
			for(int i=0; i<n-1; i++) {
				st=new StringTokenizer(br.readLine());
				edge[i][0]=Integer.parseInt(st.nextToken());
				edge[i][1]=Integer.parseInt(st.nextToken());
			}

			bw.write(
				String.valueOf(
					new Main().resolve(
						n, p, edge
					)
				)
			);
			bw.flush();
		}
	}

	List<Integer>[] adj;
	int V;

	int resolve(int n, int[] p, int[][] edge) {
		init(n, edge);

		int[] parent=new int[V];
		Arrays.fill(parent, -1);
		List<Integer> order=new ArrayList<>();
		int root=0;
		parent[root]=0;
		Queue<Integer> q=new ArrayDeque<>();
		q.add(root);

		while(!q.isEmpty()) {
			int cur=q.poll();
			order.add(cur);
			for(int next: adj[cur]) {
				if(parent[next]!=-1) continue;
				parent[next]=cur;
				q.add(next);
			}
		}

		Collections.reverse(order);

		int[] dp0=new int[V];
		int[] dp1=new int[V];
		for(int cur: order) {
			dp1[cur]=p[cur];
			for(int next: adj[cur]) {
				if(next==parent[cur]) continue;
				dp1[cur]+=dp0[next];
				dp0[cur]+=Math.max(dp0[next], dp1[next]);
			}
		}
		return Math.max(dp0[root], dp1[root]);
	}

	@SuppressWarnings("unchecked")
	void init(int n, int[][] edge) {
		this.V=n;
		this.adj=new ArrayList[V];
		for(int i=0; i<n; i++) adj[i]=new ArrayList<>();
		for(int[] e: edge) {
			adj[e[0]-1].add(e[1]-1);
			adj[e[1]-1].add(e[0]-1);
		}
	}

}
