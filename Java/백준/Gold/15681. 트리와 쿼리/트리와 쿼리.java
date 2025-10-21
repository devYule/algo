import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			StringTokenizer st=new StringTokenizer(br.readLine());
			int n=Integer.parseInt(st.nextToken());
			int root=Integer.parseInt(st.nextToken());
			int qcnt=Integer.parseInt(st.nextToken());

			int[][] edge=new int[n-1][2];
			for(int i=0; i<n-1; i++) {
				st=new StringTokenizer(br.readLine());
				edge[i][0]=Integer.parseInt(st.nextToken());
				edge[i][1]=Integer.parseInt(st.nextToken());
			}

			int[] Q=new int[qcnt];
			for(int i=0; i<qcnt; i++) {
				Q[i]=Integer.parseInt(br.readLine());
			}

			bw.write(
				String.valueOf(
					new Main().resolve(
						n, root, qcnt, edge, Q
					)
				)
			);
			bw.flush();
		}
	}

	List<Integer>[] adj;
	int V, subsize[];


	String resolve(int n, int root, int qcnt, int[][] edge, int[] Q) {
		init(n, edge);
		this.subsize=new int[V];

		find(root-1, new boolean[V]);

		StringBuilder sb=new StringBuilder();
		for(int i=0; i<qcnt; i++) {
			if(i!=0) sb.append("\n");
			sb.append(subsize[Q[i]-1]);
		}
		return sb.toString();
	}
	
	int find(int cur, boolean[] vis) {
		vis[cur]=true;

		int ret=1;
		for(int next: adj[cur]) {
			if(vis[next]) continue;
			ret+=find(next, vis);
		}

		return subsize[cur]=ret;
	}

	@SuppressWarnings("unchecked")
	void init(int v, int[][] edge) {
		this.V=v;
		this.adj=new ArrayList[V];
		for(int i=0; i<v; i++) adj[i]=new ArrayList<>();
		for(int[] e: edge) {
			int a=e[0]-1, b=e[1]-1;
			adj[a].add(b);
			adj[b].add(a);
		}
	}
}
