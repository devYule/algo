import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			int n=Integer.parseInt(br.readLine());
			int[][] map=new int[n][n];
			for(int i=0; i<n; i++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				for(int j=0; j<n; j++) {
					map[i][j]=Integer.parseInt(st.nextToken());
				}
			}

			bw.write(
				String.valueOf(
					new Main().resolve(
						n, map
					)
				)
			);
			bw.flush();
		}
	}

	int n, map[][];
	long[][] memo;
	final int[] rg={0, 1};
	final int[] cg={1, 0};
	long resolve(int n, int[][] map) {
		this.n=n; this.map=map;
		this.memo=new long[n][n];
		for(int i=0; i<n; i++) Arrays.fill(memo[i], -1);
		this.memo[n-1][n-1]=1;
		return find(0, 0);
	}

	long find(int y, int x) {
		if(memo[y][x]!=-1) return memo[y][x];
		int cost=map[y][x];
		if(cost==0) return memo[y][x]=0;
		long ret=0;
		for(int i=0; i<2; i++) {
			int ny=y+(rg[i]*cost);
			int nx=x+(cg[i]*cost);
			if(valid(ny, nx)) {
				ret+=find(ny, nx);
			}
		}
		return memo[y][x]=ret;
	}

	boolean valid(int y, int x) {
		return y>=0 && x>=0 && y<n && x<n;
	}

}
