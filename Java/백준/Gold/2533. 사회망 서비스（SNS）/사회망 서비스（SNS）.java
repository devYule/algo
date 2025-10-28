import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			int n=Integer.parseInt(br.readLine());

			int[][] edge=new int[n-1][2];
			for(int i=0; i<n-1; i++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				edge[i][0]=Integer.parseInt(st.nextToken());
				edge[i][1]=Integer.parseInt(st.nextToken());
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

	int head[], nxt[], to[], ei;
	int n, memo[][];

	int resolve(int n, int[][] edge) {
		init(n, edge);
		this.memo=new int[n+1][2];
		for(int i=1; i<=n; i++) Arrays.fill(memo[i], -1);

		return Math.min(dfs(1, 1, -1), dfs(1, 0, -1));
	}

	int dfs(int cur, int ps, int parent) {
		if(memo[cur][ps]!=-1) return memo[cur][ps];
		int ret=ps;
		for(int i=head[cur]; i!=-1; i=nxt[i]) {
			int next=to[i];
			if(next==parent) continue;

			if(ps==1) ret+=Math.min(dfs(next, 0, cur), dfs(next, 1, cur));
			else ret+=dfs(next, 1, cur);
		}
		return memo[cur][ps]=ret;
	}


	void init(int n, int[][] edge) {
		this.n=n;
		this.head=new int[n+1];
		Arrays.fill(head, -1);
		int E=edge.length;
		this.nxt=new int[E*2];
		this.to=new int[E*2];
		this.ei=0;

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
