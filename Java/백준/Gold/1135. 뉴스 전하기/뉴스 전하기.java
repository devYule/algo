import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			int n=Integer.parseInt(br.readLine());
			int[] edge=Arrays.stream(br.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();


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
	int V;
	int resolve(int n, int[] edge) {
		init(n, edge);
		return find(0);
	}
	int find(int cur) {
		List<Integer> childTime=new ArrayList<>();
		for(int next: adj[cur]) {
			childTime.add(find(next));
		}
		childTime.sort((a, b)->b-a);

		int ret=0;
		for(int i=0; i<childTime.size(); i++) {
			ret=Math.max(ret, i+1+childTime.get(i));
		}
		return ret;
	}

	@SuppressWarnings("unchecked")
	void init(int V, int[] edge) {
		this.V=V;
		this.adj=new ArrayList[V];
		for(int i=0; i<V; i++) adj[i]=new ArrayList<>();
		for(int i=0; i<V; i++) {
			if(edge[i]==-1) continue;
			adj[edge[i]].add(i);
		}
	}

}
