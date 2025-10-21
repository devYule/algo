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

	int[] sheep, wolf, ind;
	int V;
	List<Integer>[] adj;
	long resolve(int n, int[] sheep, int[] wolf, int[] rel) {
		this.sheep=sheep; this.wolf=wolf;
		init(n+1, rel);

		long[] rest=new long[V];
		Queue<Integer> q=new ArrayDeque<>();
		for(int i=2; i<V; i++) if(ind[i]==0) q.add(i);
		while(!q.isEmpty()) {
			int cur=q.poll();

			rest[cur]+=sheep[cur];
			int wolfTmp=(int)Math.max(0, wolf[cur]-rest[cur]);
			rest[cur]=Math.max(0, rest[cur]-wolf[cur]);
			wolf[cur]=(int) wolfTmp;

			for(int next: adj[cur]) {
				if(--ind[next]==0) q.add(next);
				rest[next]+=rest[cur];
			}
		}
		return rest[1];
	}


	@SuppressWarnings("unchecked")
	void init(int v, int[] rel) {
		this.V=v;
		this.adj=new ArrayList[V];
		for(int i=1; i<V; i++) adj[i]=new ArrayList<>();
		this.ind=new int[V];
		for(int i=2; i<rel.length; i++) {
			adj[i].add(rel[i]);
			ind[rel[i]]++;
		}
	}

}
