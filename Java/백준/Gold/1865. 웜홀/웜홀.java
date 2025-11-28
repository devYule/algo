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
		int[] head=new int[n+1];
		Arrays.fill(head, -1);
		int[] to=new int[m*2+w];
		int[] next=new int[m*2+w];
		int[] wt=new int[m*2+w];
		int ei=0;
		for(int[] e: edge) {
			int a=e[0], b=e[1], d=e[2];
			wt[ei]=d;
			to[ei]=b;
			next[ei]=head[a];
			head[a]=ei++;

			if(d>0) {
				wt[ei]=d;
				to[ei]=a;
				next[ei]=head[b];
				head[b]=ei++;
			}
		}
		int[] dist=new int[n+1];
		boolean updated=false;
		for(int i=0; i<=n; i++) {
			updated=false;
			for(int a=0; a<=n; a++) {
				for(int ni=head[a]; ni!=-1; ni=next[ni]) {
					int b=to[ni];
					if(dist[b]>dist[a]+wt[ni]) {
						dist[b]=dist[a]+wt[ni];
						updated=true;
					}
				}
			}
			if(!updated) break;
		}
		if(updated) return "YES";
		return "NO";
	}

}
