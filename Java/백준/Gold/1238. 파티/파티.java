import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			StringTokenizer st=new StringTokenizer(br.readLine());

			int n=Integer.parseInt(st.nextToken());
			int m=Integer.parseInt(st.nextToken());
			int x=Integer.parseInt(st.nextToken());

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
						n, m, x, edge
					)
				)
			);
			bw.flush();
		}
	}

	final int MAX=(int)1e9;
	int head[], to[], next[], wt[];

	int resolve(int n, int m, int x, int[][] edge) {
		head=new int[n+1];
		Arrays.fill(head, -1);
		int E=edge.length;
		to=new int[E];
		next=new int[E];
		wt=new int[E];
		int ei=0;
		for(int[] e: edge) {
			int a=e[0], b=e[1], d=e[2];
			wt[ei]=d;
			to[ei]=b;
			next[ei]=head[a];
			head[a]=ei++;
		}

		
		int[] dist=new int[n+1];
		for(int i=1; i<=n; i++) {
			int[] dist_=new int[n+1];
			Arrays.fill(dist_, MAX);
			bfs(i, x, dist_);
			dist[i]=dist_[x];
		}

		int[] rdist=new int[n+1];
		Arrays.fill(rdist, MAX);
		bfs(x, -1, rdist);

		int ret=0;
		for(int i=1; i<=n; i++) {
			ret=Math.max(ret, dist[i]+rdist[i]);
		}
		return ret;

	}

	void bfs(int s, int e, int[] dist) {
		PriorityQueue<int[]> q=new PriorityQueue<>((a, b) -> a[0]-b[0]);
		q.add(new int[] {0, s});
		dist[s]=0;
		while(!q.isEmpty()) {
			int as[]=q.poll();
			int a=as[1];
			int cost=as[0];
			if(dist[a]!=cost) continue;
			if(a==e) break;

			for(int ni=head[a]; ni!=-1; ni=next[ni]) {
				int b=to[ni];
				int ncost=cost+wt[ni];
				if(dist[b]>ncost) {
					dist[b]=ncost;
					q.add(new int[] {ncost, b});
				}
			}
		}
	}
}
