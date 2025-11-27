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

	int head[], to[], next[], wt[];
	String resolve(int n, int m, int[][] edge) {
		init(n, edge);

		PriorityQueue<int[]> q=new PriorityQueue<>((a, b) -> a[0]-b[0]);
		int[] dist=new int[n+1];
		int[] parent=new int[n+1];
		for(int i=1; i<=n; i++) {
			dist[i]=(int)1e9;
			parent[i]=-1;
		}

		dist[1]=0;
		parent[1]=1;
		q.add(new int[] {0, 1});
		while(!q.isEmpty()) {
			int[] as=q.poll();
			int a=as[1];
			int cost=as[0];

			int ni=head[a];
			while(ni!=-1) {
				int b=to[ni];
				int nc=wt[ni]+cost;
				if(dist[b]>nc) {
					dist[b]=nc;
					parent[b]=a;
					q.add(new int[] {nc, b});
				}
				ni=next[ni];
			}
		}

		StringBuilder sb=new StringBuilder();
		sb.append(n-1);
		for(int i=2; i<=n; i++) {
			sb.append("\n").append(parent[i]).append(" ").append(i);
		}
		return sb.toString();
	}

	void init(int n, int[][] edge) {
		head=new int[n+1];
		Arrays.fill(head, -1);
		int E=edge.length;
		to=new int[E*2];
		next=new int[E*2];
		wt=new int[E*2];

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
