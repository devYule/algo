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

			st=new StringTokenizer(br.readLine());
			int a=Integer.parseInt(st.nextToken());
			int b=Integer.parseInt(st.nextToken());

			bw.write(
				String.valueOf(
					new Main().resolve(
						n, m, edge, a, b
					)
				)
			);
			bw.flush();
		}
	}

	int head[], to[], next[], wt[], n;
	int MAX=(int)1e9;
	int resolve(int n, int m, int[][] edge, int a, int b) {
		init(n, edge);

		int[] dist1=dijk(1, a);
		int[] dist2=dijk(1, b);
		int[] dist3=dijk(a, b);
		int[] dist4=dijk(a, n);
		int[] dist5=dijk(b, n);

		if(dist1==null || dist2==null || dist3==null || dist4==null || dist5==null) return -1;
		return Math.min(
			dist1[a]+dist3[b]+dist5[n],
			dist2[b]+dist3[b]+dist4[n]
		);

	}

	int[] dijk(int f, int t) {
		int[] dist=new int[n+1];
		Arrays.fill(dist, MAX);
		PriorityQueue<int[]>q=new PriorityQueue<>((a, b) -> a[0]-b[0]);
		dist[f]=0;
		q.add(new int[] {0, f});
		while(!q.isEmpty()) {
			int[] as=q.poll();
			int cost=as[0];
			int a=as[1];
			if(dist[a]!=cost) continue;
			for(int ni=head[a]; ni!=-1; ni=next[ni]) {
				int b=to[ni];
				int nextCost=cost+wt[ni];
				if(dist[b]>nextCost) {
					dist[b]=nextCost;
					q.add(new int[] {nextCost, b});
				}
			}
		}

		return dist[t]==MAX ? null : dist;

	}

	void init(int n, int[][] edge) {
		int E=edge.length;
		head=new int[n+1];
		Arrays.fill(head, -1);
		to=new int[E*2];
		next=new int[E*2];
		wt=new int[E*2];
		this.n=n;

		int ei=0;
		for(int[] e: edge) {
			int a=e[0], b=e[1], d=e[2];
			wt[ei]=d;
			to[ei]=b;
			next[ei]=head[a];
			head[a]=ei++;

			wt[ei]=d;
			to[ei]=a;
			next[ei]=head[b];
			head[b]=ei++;
		}
	}
}
