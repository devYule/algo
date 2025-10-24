import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			int n=Integer.parseInt(br.readLine());

			int[][] dist=new int[n][n];
			for(int i=0; i<n; i++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				for(int j=0; j<n; j++) {
					dist[i][j]=Integer.parseInt(st.nextToken());
				}
			}

			bw.write(
				String.valueOf(
					new Main().resolve(
						n, dist
					)
				)
			);
			bw.flush();
		}
	}

	int n, memo[][], dist[][];

	int resolve(int n, int[][] dist) {
		this.n=n;
		this.dist=dist;
		this.memo=new int[n][1<<n];
		for(int i=0; i<n; i++) Arrays.fill(memo[i], -1);
		return find(0, 1<<0);
	}

	int find(int cur, int mask) {
		if(Integer.bitCount(mask)==n) return dist[cur][0]==0 ? (int)1e9 : dist[cur][0];
		if(memo[cur][mask]!=-1) return memo[cur][mask];
		int ret=(int)1e9;
		for(int i=0; i<n; i++) {
			if((mask&1<<i)!=0 || dist[cur][i]==0) continue;
			ret=Math.min(ret, find(i, mask|1<<i)+dist[cur][i]);
		}
		return memo[cur][mask]=ret;
	}

}
