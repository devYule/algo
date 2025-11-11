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

			StringTokenizer st=new StringTokenizer(br.readLine());
			int from=Integer.parseInt(st.nextToken());
			int to=Integer.parseInt(st.nextToken());

			bw.write(
				String.valueOf(
					new Main().resolve(
						n, m, edge, from, to
					)
				)
			);
			bw.flush();
		}
	}

	int head[], to[], nxt[], wt[];

	String resolve(int n, int m, int[][] edge, int from, int to) {
		init(n, edge);

		PriorityQueue<int[]> q=new PriorityQueue<>((a, b) -> a[0]-b[0]);
		int[] dist=new int[n+1];
		int[] parent=new int[n+1];
		Arrays.fill(dist, (int)1e9+1);
		Arrays.fill(parent, -1);

		dist[from]=0;
		parent[from]=0;
		q.add(new int[] {0, from});

		while(!q.isEmpty()) {
			int[] curs=q.poll();
			int a=curs[1];
			int cost=curs[0];

			if(dist[a]!=cost) continue;

			for(int i=head[a]; i!=-1; i=nxt[i]) {
				int b=this.to[i];

				int nextCost=cost+wt[i];
				if(dist[b]>nextCost) {
					parent[b]=a;
					dist[b]=nextCost;
					q.add(new int[] {nextCost, b});
				}
			}
		}

		List<Integer> ret=new ArrayList<>();

		ret.add(to);
		int cur=to;
		while(true) {
			cur=parent[cur];
			ret.add(cur);
			if(cur==from) break;
		}

		Collections.reverse(ret);

		return dist[to] + "\n" + ret.size() + "\n" + ret.stream().map(String::valueOf).collect(java.util.stream.Collectors.joining(" "));

	}

	void init(int n, int[][] edge) {
		int E=edge.length;
		this.head=new int[n+1];
		Arrays.fill(head, -1);

		this.to=new int[E];
		this.nxt=new int[E];
		this.wt=new int[E];
		int ei=0;
		for(int[] e: edge) {
			int a=e[0], b=e[1], w=e[2];

			wt[ei]=w;
			to[ei]=b;
			nxt[ei]=head[a];
			head[a]=ei++;

		}
	}

}
