import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			String[] nm=br.readLine().split("\\s+");
			String[] know=br.readLine().split("\\s+");
			int n=Integer.parseInt(nm[0]);
			int m=Integer.parseInt(nm[1]);
			int knowCnt=Integer.parseInt(know[0]);
			int[] knows=new int[knowCnt];
			for(int i=0; i<knowCnt; i++) {
				knows[i]=Integer.parseInt(know[i+1]);
			}
			int[][] party=new int[m][];
			for(int i=0; i<m; i++) {
				String[] info=br.readLine().split("\\s+");
				party[i]=new int[Integer.parseInt(info[0])];
				for(int j=0; j<party[i].length; j++) {
					party[i][j]=Integer.parseInt(info[j+1]);
				}
			}

			bw.write(
				String.valueOf(
					new Main().resolve(
						n, m, knowCnt, knows, party
					)
				)
			);
			bw.flush();
		}
	}


	int resolve(int n, int m, int kc, int[] kn, int[][] p) {
		n++;
		boolean[] known=new boolean[n];
		for(int k: kn) known[k]=true;

		DSU d=new DSU(n, known);

		for(int i=0; i<m; i++) {
			int cur=p[i][0];
			for(int j=1; j<p[i].length; j++) {
				if(d.find(cur)==d.find(p[i][j])) continue;
				d.union(cur, p[i][j]);
			}
		}

		int ret=0;
		for(int i=0; i<m; i++) {
			if(!known[d.find(p[i][0])]) ret++;
		}

		return ret;
	}

	static class DSU {
		int[] uf;
		boolean[] known;
		DSU(int n, boolean[] known) {
			this.uf=new int[n];
			this.known=known;
			for(int i=0; i<n; i++) uf[i]=i;
		}

		int find(int a) {
			if(uf[a]==a) return a;
			return uf[a]=find(uf[a]);
		}

		void union(int a, int b) {
			int fa=find(a);
			int fb=find(b);
			if(fa==fb) return;
			if(!known[fa]) {
				int tmp=fa;
				fa=fb;
				fb=tmp;
			}
			uf[fb]=fa;
		}
	}

}
