import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			int n=Integer.parseInt(br.readLine());
			int[][] edge=new int[n-1][3];
			for(int i=0; i<n-1; i++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				edge[i][0]=Integer.parseInt(st.nextToken());
				edge[i][1]=Integer.parseInt(st.nextToken());
				edge[i][2]=Integer.parseInt(st.nextToken());
			}

			int Q=Integer.parseInt(br.readLine());
			int[][] queries=new int[Q][2];
			for(int i=0; i<Q; i++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				queries[i][0]=Integer.parseInt(st.nextToken());
				queries[i][1]=Integer.parseInt(st.nextToken());
			}

			bw.write(
				String.valueOf(
					new Main().resolve(
						n, edge, Q, queries
					)
				)
			);
			bw.flush();
		}
	}

	int head[], to[], nxt[], wt[], depth[], up[][], P, min[][], max[][];

	String resolve(int n, int[][] edge, int Q, int[][] queries) {
		init(n, edge);

		ArrayDeque<Integer> q=new ArrayDeque<>();
		q.add(1);
		depth[1]=0;
		while(!q.isEmpty()) {
			int a=q.removeFirst();
			for(int ni=head[a]; ni!=-1; ni=nxt[ni]) {
				int b=to[ni];
				if(depth[b]==-1) {
					depth[b]=depth[a]+1;
					q.add(b);
					up[0][b]=a;
					min[0][b]=wt[ni];
					max[0][b]=wt[ni];
				}
			}
		}

		for(int i=1; i<P; i++) {
			for(int a=1; a<=n; a++) {
				up[i][a]=up[i-1][up[i-1][a]];
				min[i][a]=Math.min(min[i-1][a], min[i-1][up[i-1][a]]);
				max[i][a]=Math.max(max[i-1][a], max[i-1][up[i-1][a]]);
			}
		}

		StringBuilder sb=new StringBuilder();
		for(int[] qr: queries) {
			int[] minmax=lca(qr[0], qr[1]);
			sb.append(minmax[0] + " " + minmax[1])
				.append("\n");
		}

		return sb.toString();
	}

	int[] lca(int a, int b) {
		int[] ret=new int[] {(int)1e9, 0};
		if(depth[a]<depth[b]) {
			int tmp=a;
			a=b;
			b=tmp;
		}

		int gap=depth[a]-depth[b];

		for(int i=P-1; i>=0; i--) {
			if(((gap>>>i)&1)==1) {
				ret[0]=Math.min(ret[0], min[i][a]);
				ret[1]=Math.max(ret[1], max[i][a]);
				a=up[i][a];
			}
		}

		if(a==b) return ret;

		for(int i=P-1; i>=0; i--) {
			if(up[i][a]!=up[i][b]) {
				ret[0]=Math.min(ret[0], Math.min(min[i][a], min[i][b]));
				ret[1]=Math.max(ret[1], Math.max(max[i][a], max[i][b]));
				a=up[i][a];
				b=up[i][b];
			}
		}

		ret[0]=Math.min(ret[0], Math.min(min[0][a], min[0][b]));
		ret[1]=Math.max(ret[1], Math.max(max[0][a], max[0][b]));

		return ret;
	}

	void init(int n, int[][] edge) {
		head=new int[n+1];
		depth=new int[n+1];
		Arrays.fill(head, -1);
		Arrays.fill(depth, -1);
		int E=edge.length;
		to=new int[E*2];
		nxt=new int[E*2];
		wt=new int[E*2];

		P=0;
		while((1<<P)<=n) P++;
		up=new int[P][n+1];
		min=new int[P][n+1];
		max=new int[P][n+1];
		for(int i=0; i<P; i++) {
			Arrays.fill(min[i], (int)1e9);
		}

		int ei=0;
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
