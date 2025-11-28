import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			int R=Integer.parseInt(br.readLine());


			for(int r=0; r<R; r++) {
				if(r!=0) bw.write("\n");

				StringTokenizer st=new StringTokenizer(br.readLine());
				int n=Integer.parseInt(st.nextToken());
				int m=Integer.parseInt(st.nextToken());
				int w=Integer.parseInt(st.nextToken());

				int[][] edge=new int[m+w][3];
				for(int i=0; i<m; i++) {
					st=new StringTokenizer(br.readLine());
					edge[i][0]=Integer.parseInt(st.nextToken());
					edge[i][1]=Integer.parseInt(st.nextToken());
					edge[i][2]=Integer.parseInt(st.nextToken());
				}
				for(int i=m; i<edge.length; i++) {
					st=new StringTokenizer(br.readLine());
					edge[i][0]=Integer.parseInt(st.nextToken());
					edge[i][1]=Integer.parseInt(st.nextToken());
					edge[i][2]=-Integer.parseInt(st.nextToken());
				}

				bw.write(
					String.valueOf(
						new Main().resolve(
							n, m, w, edge
						)
					)
				);
			}
			bw.flush();
		}
	}

	String resolve(int n, int m, int w, int[][] edge) {
		int MAX=(int)1e9;
		int[][] adj=new int[n+1][n+1];
		for(int i=0; i<=n; i++) {
			Arrays.fill(adj[i], MAX);
			adj[i][i]=0;
		}
		for(int[] e: edge) {
			adj[e[0]][e[1]]=Math.min(adj[e[0]][e[1]], e[2]);
			if(e[2]>0) adj[e[1]][e[0]]=Math.min(adj[e[1]][e[0]], e[2]);
		}

		for(int k=1; k<=n; k++) {
			for(int i=1; i<=n; i++) {
				if(i==k || adj[i][k]==MAX) continue;
				for(int j=1; j<=n; j++) {
					if(j==k) continue;
					adj[i][j]=Math.min(adj[i][j], adj[i][k]+adj[k][j]);
					if(adj[j][j]<0) return "YES";
				}
			}
		}

		return "NO";
	}

}
