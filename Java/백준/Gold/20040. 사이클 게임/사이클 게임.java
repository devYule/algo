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
				edge[i]=new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
			}

			bw.write(
				String.valueOf(
					new Main().resolve(
						n, m, edge
					)
				)
			);
			bw.flush();
		}
	}

	int resolve(int n, int m, int[][] edge) {
		DSU dsu=new DSU(n);
		for(int i=0; i<m; i++) {
			int[] e=edge[i];
			int fa=dsu.find(e[0]), fb=dsu.find(e[1]);
			if(fa==fb) return i+1;

			dsu.union(e[0], e[1]);
		}
		return 0;
	}

	class DSU {
		int n, uf[], rank[];
		DSU(int n) {
			this.n=n;
			this.uf=new int[n];
			this.rank=new int[n];

			for(int i=1; i<n; i++) uf[i]=i;
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
			if(rank[fa]==rank[fb]) {
				rank[fa]++;
			}

			uf[fb]=fa;
		}
	}
}

