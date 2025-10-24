import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			int v=Integer.parseInt(br.readLine());
			int e=Integer.parseInt(br.readLine());

			int[][] edge=new int[e][3];
			for(int i=0; i<e; i++) {
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
						v, e, edge, from, to
					)
				)
			);
			bw.flush();
		}
	}

	int ni, head[], nxt[], to[], wt[];

	String resolve(int v, int e, int[][] edge, int from, int end) {
		v++;
		init(v, e, edge);
		int[] parent=new int[v];
		int[] dist=new int[v];
		final int INF=(int)1e9;
		for(int i=0; i<v; i++) {
			parent[i]=-1;
			dist[i]=INF;
		}
		PriorityQueue<Integer> q=new PriorityQueue<>((a, b) -> dist[a]-dist[b]);
		q.add(from);
		dist[from]=0;
		parent[from]=from;

		while(!q.isEmpty()) {
			int cur=q.poll();
			int cost=dist[cur];
			for(int i=head[cur]; i!=-1; i=nxt[i]) {
				int next=to[i];
				int nextDist=cost+wt[i];
				if(dist[next]>nextDist) {
					parent[next]=cur;
					dist[next]=nextDist;
					q.add(next);
				}
			}
		}

		List<Integer> ret=new ArrayList<>();
		int target=end;
		while(true) {
			ret.add(target);
			if(parent[target]==target) break;
			target=parent[target];
		}

		Collections.reverse(ret);

		return dist[end] + "\n" +
			ret.size() + "\n" +
			ret.stream().map(String::valueOf).collect(java.util.stream.Collectors.joining(" "));
	}

	void init(int v, int m, int[][] edge) {
		this.head=new int[v];
		Arrays.fill(head, -1);
		this.nxt=new int[m];
		this.to=new int[m];
		this.wt=new int[m];
		this.ni=0;

		for(int[] e: edge) {
			int a=e[0], b=e[1], w=e[2];
			wt[ni]=w;
			to[ni]=b;
			nxt[ni]=head[a];
			head[a]=ni++;
		}
	}

}
