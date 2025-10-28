import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			int n=Integer.parseInt(br.readLine());

			List<int[]> edge=new ArrayList<>();
			for(int i=0; i<n; i++) {
				StringTokenizer st=new StringTokenizer(br.readLine());

				int base=Integer.parseInt(st.nextToken());
				while(true) {
					int b=Integer.parseInt(st.nextToken());
					if(b==-1) break;
					int d=Integer.parseInt(st.nextToken());
					edge.add(new int[] {base, b, d});
				}
			}

			bw.write(
				String.valueOf(
					new Main().resolve(
						n, edge
					)
				)
			);
			bw.flush();
		}
	}

	int head[], nxt[], to[], wt[], ei;
	int n;

	int resolve(int n, List<int[]> edge) {
		init(n, edge);

		int start=bfs(1)[1];
		return bfs(start)[0];
	}

	int[] bfs(int s) {
		int[] dist=new int[n+1];
		Arrays.fill(dist, -1);

		Queue<Integer> q=new ArrayDeque<>();

		q.add(s);
		dist[s]=0;

		while(!q.isEmpty()) {
			int a=q.poll();
			for(int i=head[a]; i!=-1; i=nxt[i]) {
				int b=to[i];
				if(dist[b]==-1) {
					dist[b]=wt[i]+dist[a];
					q.add(b);
				}
			}
		}
		int[] ret=new int[2];
		for(int i=1; i<=n; i++) {
			if(ret[0]<dist[i]) {
				ret[0]=dist[i];
				ret[1]=i;
			}
		}
		return ret;
	}

	void init(int n, List<int[]> edge) {
		this.n=n;
		int E=edge.size();

		this.head=new int[n+1];
		Arrays.fill(head, -1);

		this.nxt=new int[E*2];
		this.to=new int[E*2];
		this.wt=new int[E*2];
		this.ei=0;

		for(int[] e: edge) {
			int a=e[0], b=e[1], d=e[2];

			wt[ei]=d;
			to[ei]=b;
			nxt[ei]=head[a];
			head[a]=ei++;

			wt[ei]=d;
			to[ei]=a;
			nxt[ei]=head[b];
			head[b]=ei++;
		}
	}

}
