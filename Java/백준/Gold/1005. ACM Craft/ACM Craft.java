import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			int round=Integer.parseInt(br.readLine());

			for(int r=0; r<round; r++) {

				StringTokenizer st=new StringTokenizer(br.readLine());

				int n=Integer.parseInt(st.nextToken());
				int m=Integer.parseInt(st.nextToken());

				int[] times=new int[n];
				st=new StringTokenizer(br.readLine());
				for(int i=0; i<n; i++) times[i]=Integer.parseInt(st.nextToken());

				int[][] edge=new int[m][2];
				for(int i=0; i<m; i++) {
					st=new StringTokenizer(br.readLine());
					edge[i][0]=Integer.parseInt(st.nextToken());
					edge[i][1]=Integer.parseInt(st.nextToken());
				}
				int target=Integer.parseInt(br.readLine());
				if(r!=0) bw.write("\n");
				bw.write(
					String.valueOf(
						new Main().resolve(
							n, m, times, edge, target
						)
					)
				);
			}
			bw.flush();
		}
	}

	int head[], to[], nxt[], ind[];

	int resolve(int n, int m, int[] times, int[][] edge, int target) {
		init(n, edge);


		int[] dist=new int[n+1];
		ArrayDeque<Integer> q=new ArrayDeque<>();
		for(int i=1; i<=n; i++) if(ind[i]==0) {
			q.add(i);
			dist[i]=times[i-1];
		}

		while(!q.isEmpty()) {
			int a=q.removeFirst();
			for(int i=head[a]; i!=-1; i=nxt[i]) {
				int b=to[i];
				dist[b]=Math.max(dist[b], dist[a]+times[b-1]);
				if(--ind[b]==0) q.add(b);
			}
		}
		return dist[target];
	}

	void init(int n, int[][] edge) {
		this.head=new int[n+1];
		Arrays.fill(head, -1);
		int E=edge.length;
		this.to=new int[E];
		this.nxt=new int[E];
		this.ind=new int[n+1];

		int ei=0;
		for(int[] e: edge) {
			int a=e[0], b=e[1];
			ind[b]++;

			to[ei]=b;
			nxt[ei]=head[a];
			head[a]=ei++;
		}
	}

}
