import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			StringTokenizer st=new StringTokenizer(br.readLine());
			int n=Integer.parseInt(st.nextToken());
			int r=Integer.parseInt(st.nextToken());
			int q=Integer.parseInt(st.nextToken());

			int[][] edge=new int[n-1][2];
			for(int i=0; i<n-1; i++) {
				st=new StringTokenizer(br.readLine());
				edge[i][0]=Integer.parseInt(st.nextToken());
				edge[i][1]=Integer.parseInt(st.nextToken());
			}

			int[] Q=new int[q];
			for(int i=0; i<q; i++) Q[i]=Integer.parseInt(br.readLine());

			bw.write(
				String.valueOf(
					new Main().resolve(
						n, r, q, edge, Q
					)
				)
			);
			bw.flush();
		}
	}

	int head[], nxt[], to[], ei;
	int n, memo[];

	String resolve(int n, int r, int q, int[][] edge, int[] Q) {
		init(n, edge);

		dfs(r);

		StringBuilder sb=new StringBuilder();
		for(int i=0; i<q; i++) {
			if(i!=0) sb.append("\n");
			sb.append(memo[Q[i]]);
		}
		return sb.toString();
	}

	void dfs(int s) {
		int[] parent=new int[n+1];
		int[] order=new int[n+1];
		int oi=0;

		Queue<Integer> q=new ArrayDeque<>();

		q.add(s);
		parent[s]=s;

		while(!q.isEmpty()) {
			int a=q.poll();
			order[oi++]=a;

			for(int i=head[a]; i!=-1; i=nxt[i]) {
				int b=to[i];
				if(parent[b]==0) {
					parent[b]=a;
					q.add(b);
				}
			}
		}

		for(int i=oi-1; i>=0; i--) {
			int a=order[i];

			int cnt=1;
			for(int ni=head[a]; ni!=-1; ni=nxt[ni]) {
				int b=to[ni];
				if(b==parent[a]) continue;

				cnt+=memo[b];
			}
			memo[a]=cnt;
		}

	}

	void init(int n, int[][] edge) {
		this.n=n;
		int E=edge.length;
		this.head=new int[n+1];
		Arrays.fill(head, -1);

		this.nxt=new int[E*2];
		this.to=new int[E*2];
		this.ei=0;

		this.memo=new int[n+1];
		Arrays.fill(memo, -1);

		for(int[] e: edge) {
			int a=e[0], b=e[1];
			to[ei]=b;
			nxt[ei]=head[a];
			head[a]=ei++;

			to[ei]=a;
			nxt[ei]=head[b];
			head[b]=ei++;
		}
	}

}
