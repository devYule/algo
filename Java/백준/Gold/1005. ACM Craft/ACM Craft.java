import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {
			int R=Integer.parseInt(br.readLine());
			for(int i=0; i<R; i++) {
				int n=0, k=0;
				int[] times=null;
				int[][] edge=null;
				int target=0;

				String[] split=br.readLine().split("\\s+");
				n=Integer.parseInt(split[0]); k=Integer.parseInt(split[1]);
				times=Arrays.stream(br.readLine().split("\\s+"))
					.mapToInt(s->Integer.parseInt(s))
					.toArray();
				edge=new int[k][];
				for(int j=0; j<k; j++) {
					edge[j]=Arrays.stream(br.readLine().split("\\s+"))
						.mapToInt(s->Integer.parseInt(s))
						.toArray();
				}
				target=Integer.parseInt(br.readLine());
				bw.write(String.valueOf(new Main().resolve(n, k, times, edge, target)));
				if(i<R-1) bw.write("\n");
			}
			bw.flush();
		}
	}

	List<Integer>[] adj;
	int[] in;
	int V;
	int resolve(int V, int E, int[] times, int[][] edges, int target) {
		target--;
		init(V, edges);
		if(in[target]==0) return times[target];
		int ret=0;
		Queue<Integer> q=new LinkedList<>();
		int[] buildTime=new int[V];
		for(int i=0; i<V; i++) if(in[i]==0) { q.add(i); buildTime[i]=times[i]; }
		while(!q.isEmpty()) {
			int cur=q.poll();
			if(cur==target) return buildTime[target];
			for(int next: adj[cur]) {
				buildTime[next]=Math.max(buildTime[next], buildTime[cur]+times[next]);
				if(--in[next]==0) q.add(next);
			}
		}
		return -1;
	}

	@SuppressWarnings("unchecked")
	void init(int V, int[][] edge) {
		this.V=V;
		this.adj=new ArrayList[V];
		this.in=new int[V];
		for(int i=0; i<V; i++) adj[i]=new ArrayList<>();
		for(int[] e: edge) {
			adj[e[0]-1].add(e[1]-1);
			in[e[1]-1]++;
		}
	}
}