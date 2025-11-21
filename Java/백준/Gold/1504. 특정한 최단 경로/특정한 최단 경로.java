import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			StringTokenizer st=new StringTokenizer(br.readLine());
			int n=Integer.parseInt(st.nextToken());
			int m=Integer.parseInt(st.nextToken());

			int[][] edge=new int[m][3];
			for(int i=0; i<m; i++) {
				st=new StringTokenizer(br.readLine());
				edge[i][0]=Integer.parseInt(st.nextToken());
				edge[i][1]=Integer.parseInt(st.nextToken());
				edge[i][2]=Integer.parseInt(st.nextToken());
			}

			st=new StringTokenizer(br.readLine());
			int a=Integer.parseInt(st.nextToken());
			int b=Integer.parseInt(st.nextToken());

			bw.write(
				String.valueOf(
					new Main().resolve(
						n, m, edge, a, b
					)
				)
			);
			bw.flush();
		}
	}

	long adj[][];
	long resolve(int n, int m, int[][] edge, int a, int b) {
		init(n, edge);

		for(int k=1; k<=n; k++) {
			for(int i=1; i<=n; i++) {
				for(int j=1; j<=n; j++) {
					adj[i][j]=Math.min(adj[i][j], adj[i][k]+adj[k][j]);
				}
			}
		}

		long ret=Math.min(
			adj[1][a]+adj[a][b]+adj[b][n],
			adj[1][b]+adj[b][a]+adj[a][n]
		);
		return ret>=(long)1e10 ? -1L : ret;
	}

	void init(int n, int[][] edge) {
		adj=new long[n+1][n+1];
		for(int i=0; i<=n; i++) {
			Arrays.fill(adj[i], (long)1e10);
			adj[i][i]=0;
		}
		for(int[] e: edge) {
			int a=e[0], b=e[1], d=e[2];
			adj[a][b]=d;
			adj[b][a]=d;
		}
	}
}
