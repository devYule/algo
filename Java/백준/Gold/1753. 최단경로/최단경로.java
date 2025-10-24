import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			StringTokenizer st=new StringTokenizer(br.readLine());
			int v=Integer.parseInt(st.nextToken());
			int e=Integer.parseInt(st.nextToken());
			int start=Integer.parseInt(br.readLine());

			int[][] edge=new int[e][3];
			for(int i=0; i<e; i++) {
				st=new StringTokenizer(br.readLine());
				edge[i][0]=Integer.parseInt(st.nextToken());
				edge[i][1]=Integer.parseInt(st.nextToken());
				edge[i][2]=Integer.parseInt(st.nextToken());
			}

			bw.write(
				String.valueOf(
					new Main().resolve(
						v, e, start, edge
					)
				)
			);
			bw.flush();
		}
	}

	int ni, head[], nxt[], map[], wt[];

	String resolve(int v, int e, int start, int[][] edge) {
		v++;
		init(v, edge);

		int[] dist=new int[v];
		Arrays.fill(dist, (int)1e9);
		PriorityQueue<int[]> q=new PriorityQueue<>((a, b)->a[1]-b[1]);
		q.add(new int[] {start, 0});
		dist[start]=0;
		while(!q.isEmpty()) {
			int[] curs=q.poll();
			int cur=curs[0];
			int cost=curs[1];

			if(dist[cur]<cost) continue;

			for(int i=head[cur]; i!=-1; i=nxt[i]) {
				int next=map[i];
				int nextDist=cost+wt[i];
				if(dist[next]>nextDist) {
					dist[next]=nextDist;
					q.add(new int[] {next, nextDist});
				}
			}
		}

		StringBuilder sb=new StringBuilder();
		for(int i=1; i<v; i++) {
			if(i!=1) sb.append("\n");
			sb.append(dist[i]==(int)1e9 ? "INF" : String.valueOf(dist[i]));
		}
		return sb.toString();
	}

	void init(int v, int[][] edge) {
		this.head=new int[v];
		Arrays.fill(head, -1);
		int m=edge.length;
		this.nxt=new int[m];
		this.map=new int[m];
		this.wt=new int[m];

		this.ni=0;
		for(int[] e: edge) {
			wt[ni]=e[2];
			map[ni]=e[1];
			nxt[ni]=head[e[0]];
			head[e[0]]=ni++;
		}
	}

}
