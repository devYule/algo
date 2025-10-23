import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			int n=Integer.parseInt(br.readLine());
			int[][] edge=new int[n-1][2];
			for(int i=0; i<n-1; i++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				edge[i][0]=Integer.parseInt(st.nextToken());
				edge[i][1]=Integer.parseInt(st.nextToken());
			}

			bw.write(
				String.valueOf(
					new Main().resolve(
						n, edge
					)
				)
			);
			bw.flush();
		}
	}

	List<Integer>[] adj;
	int V, memo[][];

	int resolve(int n, int[][] edge) {
		n++;
		int start=init(n, edge);
		this.memo=new int[n][2];
		for(int i=0; i<n; i++) Arrays.fill(memo[i], -1);

		return Math.min(find(start, 0, -1), find(start, 1, -1));
	}

	int find(int cur, int cs, int parent) {
		if(memo[cur][cs]!=-1) return memo[cur][cs];

		int ret=0;

		for(int next: adj[cur]) {
			if(next==parent) continue;
			if(cs==0) {
				ret+=find(next, 1, cur);
			} else {
				ret+=Math.min(find(next, 0, cur), find(next, 1, cur));
			}
		}

		return memo[cur][cs]=ret+cs;
	}

	@SuppressWarnings("unchecked")
	int init(int v, int[][] edge) {
		this.V=v;
		this.adj=new ArrayList[V];
		int[] ind=new int[V];
		for(int i=1; i<V; i++) adj[i]=new ArrayList<>();
		for(int[] e: edge) {
			adj[e[0]].add(e[1]);
			adj[e[1]].add(e[0]);
			ind[e[0]]++; ind[e[1]]++;
		}

		for(int i=1; i<V; i++) {
			if(ind[i]==1) return i;
		}
		return -1;
	}

}
