import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {
			int R=Integer.parseInt(br.readLine());
			for(int i=0; i<R; i++) {
				int m=0, n=0, k=0;
				int[][] axis=null;

				String[] base=br.readLine().split("\\s+");
				m=Integer.parseInt(base[0]);
				n=Integer.parseInt(base[1]);
				k=Integer.parseInt(base[2]);

				axis=new int[k][2];
				for(int j=0; j<k; j++) {
					String[] ax=br.readLine().split("\\s+");
					axis[j][0]=Integer.parseInt(ax[1]);
					axis[j][1]=Integer.parseInt(ax[0]);
				}

				bw.write(String.valueOf(new Main().resolve(n, m, axis)));
				if(i<R-1) bw.write("\n");
			}
			bw.flush();
		}
	}

	int[] rg={-1, 0, 1, 0};
	int[] cg={0, 1, 0, -1};
	int[][] map;
	int n, m;
	int resolve(int n, int m, int[][] axis) {
		this.n=n;
		this.m=m;
		this.map=new int[n][m];
		for(int[] a: axis) map[a[0]][a[1]]=1;

		int ret=0;
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				if(map[i][j]!=0) { clear(i, j); ret++; }
			}
		}
		return ret;
	}

	void clear(int y, int x) {
		map[y][x]=0;
		for(int i=0; i<4; i++) {
			int ny=y+rg[i];
			int nx=x+cg[i];
			if(valid(ny, nx)) clear(ny, nx);
		}
	}

	boolean valid(int y, int x) {
		return x>=0 && y>=0 && x<m && y<n && map[y][x]!=0;
	}
	
}