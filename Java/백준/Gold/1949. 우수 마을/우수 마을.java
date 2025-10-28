import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			int n=Integer.parseInt(br.readLine());

			int[] pp=new int[n];
			StringTokenizer st=new StringTokenizer(br.readLine());
			for(int i=0; i<n; i++) {
				pp[i]=Integer.parseInt(st.nextToken());
			}

			int[][] edge=new int[n-1][2];
			for(int i=0; i<n-1; i++) {
				st=new StringTokenizer(br.readLine());
				edge[i][0]=Integer.parseInt(st.nextToken());
				edge[i][1]=Integer.parseInt(st.nextToken());
			}

			bw.write(
				String.valueOf(
					new Main().resolve(
						n, pp, edge
					)
				)
			);
			bw.flush();
		}
	}

	int head[], nxt[], to[], ei;
	int n, memo[][], pp[];

	int resolve(int n, int[] pp, int[][] edge) {
		this.pp=pp;
		init(n, edge);

		return Math.max(dfs(1, 0, -1), dfs(1, 1, -1));
	}


	int dfs(int a, int cs, int parent) {
		if(memo[a][cs]!=-1) return memo[a][cs];

		int ret=cs==1 ? pp[a-1] : 0;
		for(int i=head[a]; i!=-1; i=nxt[i]) {
			int b=to[i];
			if(b==parent) continue;
			if(cs==1) ret+=dfs(b, 0, a);
			else ret+=Math.max(dfs(b, 0, a), dfs(b, 1, a));
		}
		return memo[a][cs]=ret;
	}


	void init(int n, int[][] edge) {
		this.n=n;
		int E=edge.length;

		this.head=new int[n+1];
		Arrays.fill(head, -1);
		this.nxt=new int[E*2];
		this.to=new int[E*2];
		this.ei=0;

		this.memo=new int[n+1][2];
		for(int i=1; i<=n; i++) Arrays.fill(memo[i], -1);

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
