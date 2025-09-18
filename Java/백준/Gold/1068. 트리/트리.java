import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			int n=Integer.parseInt(br.readLine());
			int[] edge=Arrays.stream(br.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();
			int del=Integer.parseInt(br.readLine());

			bw.write(
				String.valueOf(
					new Main().resolve(
						n, edge, del
					)
				)
			);
			bw.flush();
		}
	}

	int n;
	int[][] adj;
	int resolve(int n, int[] edge, int del) {
		this.n=n;
		this.adj=new int[n][n];
		int[] parent=new int[n];
		int root=-1;
		for(int i=0; i<edge.length; i++) {
			if(edge[i]==-1) { root=i; continue; }
			adj[edge[i]][i]=1;
			parent[i]=edge[i];
		}
		if(del==root) return 0;
		adj[parent[del]][del]=0;

		return end(root);
	}

	int end(int cur) {
		int ret=0;
		boolean is=true;
		for(int i=0; i<n; i++) {
			if(adj[cur][i]==0) continue;
			adj[cur][i]=0;
			ret+=end(i);
			is=false;
		}
		if(is) ret=1;
		return ret;
	}

}
