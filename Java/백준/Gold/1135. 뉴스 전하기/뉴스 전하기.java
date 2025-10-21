import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			bw.write(
				String.valueOf(
					new Main().resolve(
						Integer.parseInt(br.readLine()),
						Arrays.stream(br.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray()
					)
				)
			);
			bw.flush();
		}
	}

	List<Integer>[] adj;
	int V;
	int resolve(int n, int[] rel) {
		init(n, rel);

		return find(0);
	}

	int find(int cur) {
		List<Integer> ct=new ArrayList<>();
		for(int next: adj[cur]) {
			ct.add(find(next));
		}

		ct.sort(Comparator.reverseOrder());

		int ret=0;
		for(int i=0; i<ct.size(); i++) {
			ret=Math.max(ret, i+ct.get(i)+1);
		}

		return ret;
	}

	@SuppressWarnings("unchecked")
	void init(int n, int[] rel) {
		this.V=n;
		this.adj=new ArrayList[V];
		for(int i=0; i<V; i++) adj[i]=new ArrayList<>();
		for(int i=1; i<n; i++) {
			adj[rel[i]].add(i);
		}
	}

}
