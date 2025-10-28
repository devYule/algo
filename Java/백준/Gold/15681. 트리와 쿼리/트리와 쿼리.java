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

		dfs(r, -1);

		StringBuilder sb=new StringBuilder();
		for(int i=0; i<q; i++) {
			if(i!=0) sb.append("\n");
			sb.append(memo[Q[i]]);
		}
		return sb.toString();
	}

	int dfs(int a, int parent) {
		if(memo[a]!=-1) return memo[a];

		int ret=1;
		for(int i=head[a]; i!=-1; i=nxt[i]) {
			int b=to[i];
			if(b==parent) continue;
			ret+=dfs(b, a);
		}
		return memo[a]=ret;
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
