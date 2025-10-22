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
	int V, p[], memo[][];
	boolean[] vis;

	int resolve(int n, int[] p, int[][] edge) {
		init(n, edge);
		this.p=p;
		this.vis=new boolean[V];
		this.memo=new int[n][2];

		for(int i=0; i<V; i++) Arrays.fill(memo[i], -1);
		return find(0, 0);
	}

	int find(int cur, int parentState) {
		if(memo[cur][parentState]!=-1) return memo[cur][parentState];

		vis[cur]=true;

		int off=0;
		for(int next: adj[cur]) {
			if(vis[next]) continue;
			off+=find(next, 0);
		}

		int on=0;
		if(parentState==0) {
			on=p[cur];
			for(int next: adj[cur]) {
				if(vis[next]) continue;
				on+=find(next, 1);
			}
		}
		vis[cur]=false;
		return memo[cur][parentState]=Math.max(on, off);
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
