import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			StringTokenizer st=new StringTokenizer(br.readLine());
			int v=Integer.parseInt(st.nextToken());
			int e=Integer.parseInt(st.nextToken());

			int[][] edge=new int[e][3];
			for(int i=0; i<e; i++) {
				st=new StringTokenizer(br.readLine());
				edge[i][0]=Integer.parseInt(st.nextToken());
				edge[i][1]=Integer.parseInt(st.nextToken());
				edge[i][2]=Integer.parseInt(st.nextToken());
			}

			bw.write(
				String.valueOf(
					new Main().resolve(
						v, e, edge
					)
				)
			);
			bw.flush();
		}
	}

	int resolve(int v, int ei, int[][] edge) {
		Arrays.sort(edge, (a, b) -> a[2]-b[2]);

		int ret=0;
		DSU dsu=new DSU(v);
		for(int[] e: edge) {
			int a=e[0], b=e[1], d=e[2];
			if(dsu.find(a)==dsu.find(b)) continue;
			ret+=d;
			dsu.union(a, b);
		}
		return ret;
	}

	class DSU {
		int[] uf, rank;
		int n;
		DSU(int n) {
			this.n=n;
			this.uf=new int[n+1];
			this.rank=new int[n+1];
			for(int i=0; i<=n; i++) uf[i]=i;
		}

		int find(int a) {
			if(uf[a]==a) return a;
			return uf[a]=find(uf[a]);
		}

		void union(int a, int b) {
			int fa=find(a), fb=find(b);
			if(fa==fb) return;
			if(rank[fa]<rank[fb]) {
				int tmp=fa;
				fa=fb;
				fb=tmp;
			}
			if(rank[fa]==rank[fb]) rank[fa]++;
			uf[fb]=fa;
		}
	}

}
