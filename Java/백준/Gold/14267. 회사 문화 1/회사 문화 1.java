import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			StringTokenizer st=new StringTokenizer(br.readLine());

			int n=Integer.parseInt(st.nextToken());
			int m=Integer.parseInt(st.nextToken());

			int[] rel=new int[n];
			st=new StringTokenizer(br.readLine());
			
			for(int i=0; i<n; i++) rel[i]=Integer.parseInt(st.nextToken());

			int[][] good=new int[m][2];
			for(int i=0; i<m; i++) {
				st=new StringTokenizer(br.readLine());
				good[i][0]=Integer.parseInt(st.nextToken());
				good[i][1]=Integer.parseInt(st.nextToken());
			}

			bw.write(
				String.valueOf(
					new Main().resolve(
						n, m, rel, good
					)
				)
			);
			bw.flush();
		}
	}

	List<Integer>[] adj;
	int V;
	long[] cost;

	String resolve(int n, int m, int[] rel, int[][] good) {
		init(n, rel);

		for(int[] g: good) cost[g[0]-1]+=g[1];

		fire(0, 0);

		StringBuilder sb=new StringBuilder();
		for(int i=0; i<V; i++) {
			if(i!=0) sb.append(" ");
			sb.append(cost[i]);
		}

		return sb.toString();
	}

	void fire(int cur, long acc) {
		cost[cur]+=acc;
		for(int next: adj[cur]) {
			fire(next, cost[cur]);
		}
	}

	@SuppressWarnings("unchecked")
	void init(int v, int[] rel) {
		this.V=v;
		this.adj=new ArrayList[V];
		for(int i=0; i<V; i++) adj[i]=new ArrayList<>();
		this.cost=new long[V];
		for(int i=1; i<V; i++) {
			adj[rel[i]-1].add(i);
		}
	}

}
