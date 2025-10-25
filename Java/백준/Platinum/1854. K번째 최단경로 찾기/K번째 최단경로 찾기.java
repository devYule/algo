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

	@SuppressWarnings("unchecked")
	String resolve(int n, int m, int k, int[][] edge) {
		n++;
		init(n, m, edge);
		PriorityQueue<Integer>[] dist=new PriorityQueue[n];
		for(int i=1; i<n; i++) dist[i]=new PriorityQueue<>((a, b) -> b-a);
		PriorityQueue<int[]> q=new PriorityQueue<>((a, b) -> a[0]-b[0]);
		q.add(new int[] {0, 1});
		dist[1].add(0);
		while(!q.isEmpty()) {
			int[] curs=q.poll();
			int cur=curs[1];
			int cost=curs[0];

			if(dist[cur].size()==k && dist[cur].peek()<cost) continue;

			for(int i=head[cur]; i!=-1; i=nxt[i]) {
				int next=to[i];
				int nextDist=cost+wt[i];
				if(dist[next].size()<k) {
					dist[next].add(nextDist);
					q.add(new int[] {nextDist, next});
				} else if(dist[next].peek()>nextDist) {
					dist[next].poll();
					dist[next].add(nextDist);
					q.add(new int[] {nextDist, next});
				}
			}
		}

		List<Integer> ret=new ArrayList<>();
		for(int i=1; i<n; i++) {
			if(dist[i].size()<k) ret.add(-1);
			else ret.add(dist[i].peek());
		}
		return ret.stream().map(String::valueOf).collect(java.util.stream.Collectors.joining("\n"));
	}

	void init(int v, int m, int[][] edge) {
		this.head=new int[v];
		Arrays.fill(head, -1);
		this.nxt=new int[m];
		this.to=new int[m];
		this.wt=new int[m];

		for(int[] e: edge) {
			int a=e[0], b=e[1], w=e[2];
			wt[ni]=w;
			to[ni]=b;
			nxt[ni]=head[a];
			head[a]=ni++;
		}
	}

}
