import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			int n=Integer.parseInt(br.readLine());
			int m=Integer.parseInt(br.readLine());

			int[][] edge=new int[m][3];
			for(int i=0; i<m; i++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				edge[i][0]=Integer.parseInt(st.nextToken());
				edge[i][1]=Integer.parseInt(st.nextToken());
				edge[i][2]=Integer.parseInt(st.nextToken());
			}

			bw.write(
				String.valueOf(
					new Main().resolve(
						n, m, edge
					)
				)
			);
			bw.flush();
		}
	}

	String resolve(int n, int m, int[][] edge) {
		int[][] adj=new int[n+1][n+1];
		for(int i=1; i<=n; i++) {
			Arrays.fill(adj[i], (int)1e9);
			adj[i][i]=0;
		}
		int ei=0;
		for(int[] e: edge) {
			int a=e[0], b=e[1], d=e[2];
			adj[a][b]=Math.min(adj[a][b], d);
		}


		for(int k=1; k<=n; k++) {
			for(int i=1; i<=n; i++) {
				if(adj[i][k]==(int)1e9) continue;
				for(int j=1; j<=n; j++) {
					if(adj[k][j]==(int)1e9) continue;
					adj[i][j]=Math.min(adj[i][j], adj[i][k]+adj[k][j]);
				}
			}
		}

		StringBuilder sb=new StringBuilder();
		for(int i=1; i<=n; i++) {
			for(int j=1; j<=n; j++) {
				if(j!=1) sb.append(" ");
				sb.append(adj[i][j]==(int)1e9 ? 0 : adj[i][j]);
			}
			sb.append("\n");
		}
		return sb.toString();
	}
}
