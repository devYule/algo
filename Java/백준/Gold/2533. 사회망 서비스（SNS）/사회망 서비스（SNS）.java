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

	int V, first[], nxt[], node[], eni;

	int resolve(int n, int[][] edge) {
		n++;
		int start=init(n, edge);
		int[][] memo=new int[n][2];
		for(int i=0; i<n; i++) Arrays.fill(memo[i], -1);

		Queue<Integer> q=new ArrayDeque<>();
		int[] parent=new int[V];
		int[] order=new int[V];
		q.add(start);
		parent[start]=start;
		int oi=0;
		while(!q.isEmpty()) {
			int cur=q.poll();
			order[oi++]=cur;

			for(int ni=first[cur]; ni!=-1; ni=nxt[ni]) {
				int next=node[ni];
				if(parent[next]!=0) continue;
				parent[next]=cur;
				q.add(next);
			}
		}

		for(int i=oi-1; i>=0; i--) {
			int cur=order[i];

			int curOn=1;
			int curOff=0;

			for(int ni=first[cur]; ni!=-1; ni=nxt[ni]) {
				int next=node[ni];
				if(parent[cur]==next) continue;
				curOff+=memo[next][1];
				curOn+=Math.min(memo[next][0], memo[next][1]);
			}
			memo[cur][1]=curOn;
			memo[cur][0]=curOff;
		}

		return Math.min(memo[start][0], memo[start][1]);
	}


	int init(int v, int[][] edge) {
		this.V=v;

		this.first=new int[v];
		Arrays.fill(first, -1);
		int m=edge.length;
		this.nxt=new int[m*2];
		this.node=new int[m*2];
		this.eni=0;

		int[] ind=new int[V];
		for(int[] e: edge) {
			node[eni]=e[1];
			nxt[eni]=first[e[0]];
			first[e[0]]=eni++;

			node[eni]=e[0];
			nxt[eni]=first[e[1]];
			first[e[1]]=eni++;

			ind[e[0]]++; ind[e[1]]++;
		}

		for(int i=1; i<V; i++) {
			if(ind[i]==1) return i;
		}
		return -1;
	}

}
