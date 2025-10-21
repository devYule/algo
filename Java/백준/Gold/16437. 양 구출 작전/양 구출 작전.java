import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			int n=Integer.parseInt(br.readLine());

			int[] sheep=new int[n+1];
			int[] wolf=new int[n+1];
			int[] rel=new int[n+1];

			for(int i=1; i<n; i++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				if(st.nextToken().equals("S")) {
					sheep[i+1]+=Integer.parseInt(st.nextToken());
				} else {
					wolf[i+1]+=Integer.parseInt(st.nextToken());
				}
				rel[i+1]=Integer.parseInt(st.nextToken());
			}
			bw.write(
				String.valueOf(
					new Main().resolve(
						n, sheep, wolf, rel
					)
				)
			);
			bw.flush();
		}
	}

	int[] sheep, wolf;
	int V;
	List<Integer>[] adj;
	long resolve(int n, int[] sheep, int[] wolf, int[] rel) {
		this.sheep=sheep; this.wolf=wolf;
		init(n+1, rel);
		return find(1);
	}

	long find(int cur) {
		long restSheep=sheep[cur];
		for(int next: adj[cur]) {
			restSheep+=find(next);
		}

		int curWolf=wolf[cur];

		return Math.max(0L, restSheep-curWolf);
	}

	@SuppressWarnings("unchecked")
	void init(int v, int[] rel) {
		this.V=v;
		this.adj=new ArrayList[V];
		for(int i=1; i<V; i++) adj[i]=new ArrayList<>();

		for(int i=2; i<rel.length; i++) {
			adj[rel[i]].add(i);
		}
	}

}
