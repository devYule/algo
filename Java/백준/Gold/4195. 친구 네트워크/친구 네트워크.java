import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			int T=Integer.parseInt(br.readLine());

			for(int r=0; r<T; r++) {
				if(r!=0) bw.write("\n");

				int n=Integer.parseInt(br.readLine());
				String[][] edge=new String[n][2];
				for(int i=0; i<n; i++) {
					StringTokenizer st=new StringTokenizer(br.readLine());
					edge[i][0]=st.nextToken();
					edge[i][1]=st.nextToken();
				}

				bw.write(
					String.valueOf(
						new Main().resolve(
							n, edge
						)
					)
				);
			}

			
			bw.flush();
		}
	}

	String resolve(int n, String[][] edges) {
		Map<String, Integer> nodeMap=new HashMap<>();
		int[] inode=new int[] {0};
		int[][] edge=new int[n][2];
		for(int i=0; i<n; i++) {
			String[] e=edges[i];
			edge[i][0]=nodeMap.computeIfAbsent(e[0], k -> inode[0]++);
			edge[i][1]=nodeMap.computeIfAbsent(e[1], k -> inode[0]++);
		}

		StringBuilder sb=new StringBuilder();
		
		DSU dsu=new DSU(inode[0]);
		for(int i=0; i<n; i++) {
			int[] e=edge[i];
			if(i!=0) sb.append("\n");
			int a=e[0], b=e[1];
			int fa=dsu.find(a), fb=dsu.find(b);
			if(fa==fb) {
				sb.append(dsu.count[fa]);
				continue;
			}
			dsu.union(fa, fb);
			sb.append(dsu.count[dsu.find(fa)]);
		}


		return sb.toString();
	}

	class DSU {
		int n, uf[], rank[], count[];

		DSU(int n) {
			this.n=n;
			this.uf=new int[n];
			this.rank=new int[n];
			this.count=new int[n];
			Arrays.fill(count, 1);
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

			if(rank[fa]==rank[fb]) rank[fa]++;

			uf[fb]=fa;
			count[fa]+=count[fb];
		}

	}

}
