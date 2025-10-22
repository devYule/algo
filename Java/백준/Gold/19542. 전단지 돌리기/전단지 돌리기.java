import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			StringTokenizer st=new StringTokenizer(br.readLine());
			int n=Integer.parseInt(st.nextToken());
			int s=Integer.parseInt(st.nextToken());
			int d=Integer.parseInt(st.nextToken());

			int[][] edge=new int[n-1][2];

			for(int i=0; i<n-1; i++) {
				st=new StringTokenizer(br.readLine());
				edge[i][0]=Integer.parseInt(st.nextToken());
				edge[i][1]=Integer.parseInt(st.nextToken());
			}

			bw.write(
				String.valueOf(
					new Main().resolve(
						n, s, d, edge
					)
				)
			);
			bw.flush();
		}
	}

	List<Integer>[] adj;
	int V, d, s, ret;

	int resolve(int n, int s, int d, int[][] edge) {
		n++;
		init(n, edge);
		this.d=d;
		this.s=s;
		find(s, -1);
		return ret;
	}

	int find(int cur, int parent) {
		int h=0;
		for(int next: adj[cur]) {
			if(next==parent) continue;
			h=Math.max(h, find(next, cur));
		}

		if(cur!=s && h>=d) ret+=2;

		return h+1;
	}


	@SuppressWarnings("unchecked")
	void init(int n, int[][] edge) {
		this.V=n;
		this.adj=new ArrayList[V];
		for(int i=0; i<V; i++) adj[i]=new ArrayList<>();
		for(int[] e: edge) {
			adj[e[0]].add(e[1]);
			adj[e[1]].add(e[0]);
		}
	}

}
