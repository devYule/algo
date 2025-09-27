import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			int n=Integer.parseInt(br.readLine());
			List<int[]> edge=new ArrayList<>();
			for(int i=0; i<n; i++) {
				String[] info=br.readLine().split("\\s");
				for(int j=1; j<info.length; j+=2) {
					if("-1".equals(info[j])) break;
					edge.add(new int[] {Integer.parseInt(info[0]), Integer.parseInt(info[j]), Integer.parseInt(info[j+1])});
				}
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

	List<int[]>[] adj;
	int V;
	long ret;
	long resolve(int n, List<int[]> edges) {
		init(n+1, edges);
		dfs(1, -1);
		return ret;
	}

	long dfs(int cur, int parent) {
		long amax=0;
		long bmax=0;
		for(int[] nexts: adj[cur]) {
			if(nexts[1]==parent) continue;
			long sub=dfs(nexts[1], cur)+nexts[0];
			if(sub>amax) {
				bmax=amax;
				amax=sub;
			} else if(sub>bmax) {
				bmax=sub;
			}
		}
		ret=Math.max(ret, amax+bmax);
		return amax;
	}

	@SuppressWarnings("unchecked")
	void init(int V, List<int[]> edges) {
		this.adj=new ArrayList[V];
		this.V=V;
		for(int i=1; i<V; i++) adj[i]=new ArrayList<>();
		for(int[] e: edges) {
			adj[e[0]].add(new int[] {e[2], e[1]});
		}
	}

}
