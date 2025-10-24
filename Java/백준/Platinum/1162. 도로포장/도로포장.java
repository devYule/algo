import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			StringTokenizer st=new StringTokenizer(br.readLine());

			int n=Integer.parseInt(st.nextToken());
			int m=Integer.parseInt(st.nextToken());
			int k=Integer.parseInt(st.nextToken());

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
						n, m, k, edge
					)
				)
			);
			bw.flush();
		}
	}

	int head[], nxt[], to[], wt[], ni;

	long resolve(int n, int m, int k, int[][] edge) {
		n++;
		init(n, m, edge);

		long[][] dist=new long[n][k+1];
		for(int i=0; i<n; i++) Arrays.fill(dist[i], Long.MAX_VALUE);
		PriorityQueue<long[]> q=new PriorityQueue<>((a, b) -> Long.compare(a[2], b[2]));

		q.add(new long[] {1, k, 0});
		dist[1][k]=0;

		while(!q.isEmpty()) {
			long[] curs=q.poll();
			int cur=(int)curs[0];
			int ch=(int)curs[1];
			long cost=curs[2];
			if(dist[cur][ch]<cost) continue;

			if(cur==n-1) return cost;

			for(int i=head[cur]; i!=-1; i=nxt[i]) {
				int next=to[i];
				long nfDist=cost+wt[i];

				if(dist[next][ch]>nfDist) {
					dist[next][ch]=nfDist;
					q.add(new long[] {next, ch, nfDist});
				}
				if(ch>0 && dist[next][ch-1]>cost) {
					dist[next][ch-1]=cost;
					q.add(new long[] {next, ch-1, cost});
				}
			}
		}
		return -1;
	}

	void init(int v, int m, int[][] edge) {
		this.head=new int[v];
		Arrays.fill(head, -1);
		this.nxt=new int[m*2];
		this.to=new int[m*2];
		this.wt=new int[m*2];
		this.ni=0;
		for(int[] e: edge) {
			int a=e[0], b=e[1], w=e[2];
			wt[ni]=w;
			to[ni]=b;
			nxt[ni]=head[a];
			head[a]=ni++;

			wt[ni]=w;
			to[ni]=a;
			nxt[ni]=head[b];
			head[b]=ni++;
		}
	}

}
