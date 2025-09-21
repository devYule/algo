import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			String[] nm=br.readLine().split("\\s");
			int n=Integer.parseInt(nm[0]);
			int m=Integer.parseInt(nm[1]);
			char[][] map=new char[n][];
			for(int i=0; i<n; i++) {
				map[i]=br.readLine().toCharArray();
			}

			bw.write(
				String.valueOf(
					new Main().resolve(
						n, m, map
					)
				)
			);
			bw.flush();
		}
	}

	int[] rg={-1, 0, 1, 0};
	int[] cg={0, 1, 0, -1};
	final int MAX=(int)1e9;
	int[][] memo;
	boolean[][] vis;
	int n, m;
	char[][] map;

	int resolve(int n, int m, char[][] map) {
		this.vis=new boolean[n][m];
		this.memo=new int[n][m];
		this.n=n; this.m=m; this.map=map;
		for(int i=0; i<n; i++) Arrays.fill(memo[i], -1);

		int ret=find(0, 0);
		return ret>=MAX ? -1 : ret;
	}

	int find(int y, int x) {
		if(vis[y][x]) return MAX;
		if(memo[y][x]!=-1) return memo[y][x];

		int ret=1;
		int dist=toint(map[y][x]);
		for(int i=0; i<4; i++) {
			int ny=y+(rg[i]*dist);
			int nx=x+(cg[i]*dist);
			if(!valid(ny, nx)) continue;
			vis[y][x]=true;
			ret=Math.max(ret, find(ny, nx)+1);
			vis[y][x]=false;
		}

		return memo[y][x]=ret>MAX ? MAX : ret;
	}

	int toint(char c) {
		return c-'0';
	}

	boolean valid(int y, int x) {
		return y>=0 && x>=0 && y<n && x<m && map[y][x]!='H';
	}
}
