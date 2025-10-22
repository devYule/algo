import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			int n=Integer.parseInt(br.readLine());
			int[] d=new int[n];

			StringTokenizer st=new StringTokenizer(br.readLine());
			for(int i=0; i<n; i++) d[i]=Integer.parseInt(st.nextToken());

			int[][] edge=new int[n-1][2];
			for(int i=0; i<n-1; i++) {
				st=new StringTokenizer(br.readLine());
				edge[i][0]=Integer.parseInt(st.nextToken());
				edge[i][1]=Integer.parseInt(st.nextToken());
			}

			bw.write(
				String.valueOf(
					new Main().resolve(
						n, d, edge
					)
				)
			);
			bw.flush();
		}
	}

	List<Integer>[] adj;
	int V, d[], memo[][];

	String resolve(int n, int[] d, int[][] edge) {
		/*
					1
					2
			 3  4  6
				  5  7
		*/
		this.d=d;
		this.memo=new int[n][2];
		for(int i=0; i<n; i++) Arrays.fill(memo[i], -1);
		int start=init(n, edge);

		int p0=find(start, 0, -1);
		int p1=find(start, 1, -1);

		List<Integer> ret=new ArrayList<>();
		buildString(start, p0>p1 ? 0 : 1, -1, ret);
		
		

		return Math.max(p0, p1)+"\n"+ret.stream().sorted().map(String::valueOf).collect(java.util.stream.Collectors.joining(" "));
	}

	void buildString(int cur, int sel, int parent, List<Integer> holder) {
		if(sel==1) holder.add(cur+1);

		for(int next: adj[cur]) {
			if(next==parent) continue;
			if(sel==1) {
				buildString(next, 0, cur, holder);
			} else {
				int next0=find(next, 0, -1);
				int next1=find(next, 1, -1);
				buildString(next, next0>next1 ? 0 : 1, cur, holder);
			}
		}

	}

	int find(int cur, int ps, int parent) {
		if(memo[cur][ps]!=-1) return memo[cur][ps];
		
		int ret=0;
		if(ps==1) {
			ret=d[cur];
			for(int next: adj[cur]) {
				if(next==parent) continue;
				ret+=find(next, 0, cur);
			}
		} else {
			for(int next: adj[cur]) {
				if(next==parent) continue;
				ret+=Math.max(find(next, 0, cur), find(next, 1, cur));
			}
		}
		return memo[cur][ps]=ret;
	}

	@SuppressWarnings("unchecked")
	int init(int v, int[][] edge) {
		this.V=v;
		this.adj=new ArrayList[V];
		for(int i=0; i<V; i++) adj[i]=new ArrayList<>();
		int[] ind=new int[V];
		for(int[] e: edge) {
			adj[e[0]-1].add(e[1]-1);
			adj[e[1]-1].add(e[0]-1);
			ind[e[0]-1]++; ind[e[1]-1]++;
		}

		for(int i=0; i<V; i++) {
			if(ind[i]==1) return i;
		}
		return -1;
	}

}
