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
	int resolve(int n, int m, int[][] edge, int from, int end) {
		init(n, edge);

		int[] dist=new int[n+1];
		Arrays.fill(dist, (int)1e9);
		PriorityQueue<int[]> q=new PriorityQueue<>((a, b) -> a[0]-b[0]);
		dist[from]=0;
		q.add(new int[] {0, from});

		while(!q.isEmpty()) {
			int[] as=q.poll();
			int a=as[1];
			int cost=as[0];
			if(dist[a]!=cost) continue;
			for(int ni=head[a]; ni!=-1; ni=nxt[ni]) {
				int b=to[ni];
				int nxtCost=cost+wt[ni];

				if(dist[b]>nxtCost) {
					dist[b]=nxtCost;
					q.add(new int[] {nxtCost, b});
				}
			}
		}

		return dist[end];
	}

	void init(int n, int[][] edge) {
		head=new int[n+1];
		Arrays.fill(head, -1);
		int E=edge.length;
		to=new int[E*2];
		nxt=new int[E*2];
		wt=new int[E*2];

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
