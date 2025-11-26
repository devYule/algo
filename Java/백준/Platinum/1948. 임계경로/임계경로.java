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

	int head[], next[], to[], wt[], ind[];

	String resolve(int n, int m, int[][] edge, int f, int t) {
		init(n, edge);

		int[] dist=new int[n+1];
		Arrays.fill(dist, -1);
		ArrayDeque<Integer> q=new ArrayDeque<>();
		q.add(f);
		dist[f]=0;
		while(!q.isEmpty()) {
			int a=q.removeFirst();
			for(int ni=head[a]; ni!=-1; ni=next[ni]) {
				int b=to[ni];
				int nd=dist[a]+wt[ni];
				if(dist[b]<nd) dist[b]=nd;
				if(--ind[b]==0) q.add(b);
			}
		}

		int rd=dist[t];
		int rc=0;
		rinit(n, edge);

		q.add(t);
		boolean[] vis=new boolean[n+1];
		while(!q.isEmpty()) {
			int b=q.removeFirst();
			for(int ni=head[b]; ni!=-1; ni=next[ni]) {
				int a=to[ni];
				if(dist[a]+wt[ni]==dist[b]) {
					rc++;
					if(!vis[a]) {
						vis[a]=true;
						q.add(a);
					}
				}
			}
		}
		return rd + "\n" + rc;
	}

	void init(int n, int[][] edge) {
		head=new int[n+1];
		Arrays.fill(head, -1);
		int E=edge.length;
		next=new int[E];
		to=new int[E];
		wt=new int[E];
		ind=new int[n+1];

		int ei=0;
		for(int[] e: edge) {
			int a=e[0], b=e[1], d=e[2];
			wt[ei]=d;
			to[ei]=b;
			next[ei]=head[a];
			head[a]=ei++;
			ind[b]++;
		}
	}

	void rinit(int n, int[][] edge) {
		head=new int[n+1];
		Arrays.fill(head, -1);
		int E=edge.length;
		next=new int[E];
		to=new int[E];
		wt=new int[E];

		int ei=0;
		for(int[] e: edge) {
			int b=e[0], a=e[1], d=e[2];
			wt[ei]=d;
			to[ei]=b;
			next[ei]=head[a];
			head[a]=ei++;
		}
	}
}
