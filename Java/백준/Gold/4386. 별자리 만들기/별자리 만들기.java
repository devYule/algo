import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			int n=Integer.parseInt(br.readLine());

			double[][] loc=new double[n][2];

			for(int i=0; i<n; i++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				loc[i][0]=Double.parseDouble(st.nextToken());
				loc[i][1]=Double.parseDouble(st.nextToken());
			}

			bw.write(
				String.valueOf(
					new Main().resolve(
						n, loc
					)
				)
			);
			bw.flush();
		}
	}

	String resolve(int n, double[][] loc) {

		List<double[]> len=new ArrayList<>();
		for(int i=0; i<n; i++) {
			double[] a=loc[i];
			for(int j=i+1; j<n; j++) {
				double[] b=loc[j];
				if(a[0]==b[0] || a[1]==b[1]) {
					len.add(new double[] {Math.abs((a[0]-b[0])+(a[1]-b[1])), i, j});
				} else {
					double w=Math.abs(a[0]-b[0]);
					double h=Math.abs(a[1]-b[1]);
					len.add(new double[] {Math.sqrt(w*w+h*h), i, j});
				}
			}
		}

		len.sort((a, b) -> Double.compare(a[0], b[0]));

		DSU dsu=new DSU(n);

		double ret=0;
		for(double[] l: len) {
			double dist=l[0];
			int a=(int)l[1], b=(int)l[2];
			if(dsu.find(a)==dsu.find(b)) continue;
			ret+=dist;
			dsu.union(a, b);
		}
		return String.format("%.2f", ret);
	}

	class DSU {
		int[] rank, uf;
		int n;
		DSU(int n) {
			this.n=n;
			this.rank=new int[n];
			this.uf=new int[n];
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
		}
	}

}
