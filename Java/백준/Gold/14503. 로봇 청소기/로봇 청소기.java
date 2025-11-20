import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			StringTokenizer st=new StringTokenizer(br.readLine());

			int n=Integer.parseInt(st.nextToken());
			int m=Integer.parseInt(st.nextToken());

			st=new StringTokenizer(br.readLine());
			int sy=Integer.parseInt(st.nextToken());
			int sx=Integer.parseInt(st.nextToken());
			int dir=Integer.parseInt(st.nextToken());

			int[][] map=new int[n][m];
			for(int i=0; i<n; i++) {
				st=new StringTokenizer(br.readLine());
				for(int j=0; j<m; j++) {
					map[i][j]=Integer.parseInt(st.nextToken());
				}
			}

			bw.write(
				String.valueOf(
					new Main().resolve(
						n, m, sy, sx, dir, map
					)
				)
			);
			bw.flush();
		}
	}

	int[] rg={-1, 0, 1, 0};
	int[] cg={0, 1, 0, -1};
	int resolve(int n, int m, int sy, int sx, int dir, int[][] map) {
		boolean[][] clean=new boolean[n][m];
		int lv=0;
		int y=sy, x=sx;
		while(true) {
			if(map[y][x]==0 && !clean[y][x]) {
				lv++;
				clean[y][x]=true;
			}
			boolean mv=false;
			for(int i=0; i<4; i++) {
				int turn=i+1;
				int newDir=(dir+turn*3)%4;
				int cy=y+rg[newDir];
				int cx=x+cg[newDir];
				if(cy>=0 && cx>=0 && cy<n && cx<m && map[cy][cx]==0 && !clean[cy][cx]) {
					mv=true;
					y=cy;
					x=cx;
					dir=newDir;
					break;
				}
			}
			if(!mv) {
				int backDir=(dir+2)%4;
				int by=y+rg[backDir];
				int bx=x+cg[backDir];
				if(by<0 || bx<0 || by==n || bx==m || map[by][bx]==1) return lv;
				y=by;
				x=bx;
			}
		}
	}

}
