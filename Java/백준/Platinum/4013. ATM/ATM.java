import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			StringTokenizer st=new StringTokenizer(br.readLine());
			int n=Integer.parseInt(st.nextToken());
			int m=Integer.parseInt(st.nextToken());

			int[][] edge=new int[m][2];
			for(int i=0; i<m; i++) {
				st=new StringTokenizer(br.readLine());
				edge[i][0]=Integer.parseInt(st.nextToken());
				edge[i][1]=Integer.parseInt(st.nextToken());
			}

			int[] money=new int[n];

			for(int i=0; i<n; i++) money[i]=Integer.parseInt(br.readLine());

			st=new StringTokenizer(br.readLine());
			int start=Integer.parseInt(st.nextToken());
			int k=Integer.parseInt(st.nextToken());

			int[] tg=new int[k];
			st=new StringTokenizer(br.readLine());
			for(int i=0; i<k; i++) tg[i]=Integer.parseInt(st.nextToken());

			bw.write(
				String.valueOf(
					new Main().resolve(
						n, m, edge, start, money, k, tg
					)
				)
			);
			bw.flush();
		}
	}

	int head[], to[], nxt[];
	int rank[], scc[], rankId, sccId;
	ArrayDeque<Integer> st;

	int resolve(int n, int m, int[][] edge, int start, int[] money, int k, int[] tg) {
		init(n, edge);

		scc(start);
		
		int[] sccSum=new int[sccId];
		for(int a=1; a<=n; a++) if(scc[a]!=0) {
			sccSum[scc[a]]+=money[a-1];
		}

		int[] shead=new int[sccId];
		Arrays.fill(shead, -1);
		int[] sto=new int[m];
		int[] snxt=new int[m];
		int[] ind=new int[sccId];
		int sei=0;
		for(int a=1; a<=n; a++) if(scc[a]!=0) {
			for(int i=head[a]; i!=-1; i=nxt[i]) {
				int b=to[i];
				if(scc[b]==0 || scc[a]==scc[b]) continue;
				ind[scc[b]]++;
				sto[sei]=scc[b];
				snxt[sei]=shead[scc[a]];
				shead[scc[a]]=sei++;
			}
		}

		ArrayDeque<Integer> q=new ArrayDeque<>();
		int[] dist=new int[sccId];
		q.add(scc[start]);
		dist[scc[start]]=sccSum[scc[start]];
		
		while(!q.isEmpty()) {
			int a=q.removeFirst();
			for(int i=shead[a]; i!=-1; i=snxt[i]) {
				int b=sto[i];
				dist[b]=Math.max(dist[b], dist[a]+sccSum[b]);
				if(--ind[b]==0) {
					q.add(b);
				}
			}
		}
		int ret=0;
		for(int t: tg) if(scc[t]!=0) {
			int tscc=scc[t];
			ret=Math.max(ret, dist[tscc]);
		}

		return ret;
	}

	int scc(int a) {
		rank[a]=rankId++;
		int min=rank[a];
		st.add(a);

		for(int i=head[a]; i!=-1; i=nxt[i]) {
			int b=to[i];

			if(rank[b]!=0) {
				if(scc[b]==0) {
					min=Math.min(min, rank[b]);
				}
				continue;
			}
			min=Math.min(min, scc(b));
		}

		if(min==rank[a]) {
			while(true) {
				int nd=st.removeLast();
				scc[nd]=sccId;
				if(nd==a) break;
			}
			sccId++;
		}
		return min;
	}

	void init(int n, int[][] edge) {
		this.head=new int[n+1];
		Arrays.fill(head, -1);
		int E=edge.length;
		this.to=new int[E];
		this.nxt=new int[E];

		this.rank=new int[n+1];
		this.scc=new int[n+1];
		this.rankId=1;
		this.sccId=1;

		this.st=new ArrayDeque<>();

		int ei=0;
		for(int[] e: edge) {
			int a=e[0], b=e[1];
			to[ei]=b;
			nxt[ei]=head[a];
			head[a]=ei++;
		}
	}

}
