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

	int head[], to[], nxt[], wt[];

	String resolve(int n, int m, int[][] edge) {
		init(n, edge);

		int[] dist=new int[n+1];
		Arrays.fill(dist, (int)1e9);
		dist[1]=0;
		boolean changed=false;
		for(int r=0; r<n; r++) {
			changed=false;
			for(int a=1; a<=n; a++) {
				if(dist[a]==(int)1e9) continue;
				for(int ni=head[a]; ni!=-1; ni=nxt[ni]) {
					int b=to[ni];
					if(dist[b]>dist[a]+(long)wt[ni]) {
						dist[b]=dist[a]+wt[ni];
						changed=true;
					}
				}
			}
			if(!changed) break;
		}
		if(changed) return "-1";
		StringBuilder sb=new StringBuilder();
		for(int i=2; i<=n; i++) {
			if(i!=2) sb.append("\n");
			sb.append(dist[i]==(int)1e9 ? -1 : dist[i]);
		}
		return sb.toString();
	}

	void init(int n, int[][] edge) {
		this.head=new int[n+1];
		Arrays.fill(head, -1);

		int E=edge.length;
		this.to=new int[E];
		this.nxt=new int[E];
		this.wt=new int[E];

		int ei=0;
		for(int[] e: edge) {
			int a=e[0], b=e[1], d=e[2];
			wt[ei]=d;
			to[ei]=b;
			nxt[ei]=head[a];
			head[a]=ei++;
		}
	}

}
