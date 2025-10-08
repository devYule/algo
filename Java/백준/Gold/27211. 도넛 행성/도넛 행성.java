import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			StringTokenizer st=new StringTokenizer(br.readLine());
			int n=Integer.parseInt(st.nextToken());
			int m=Integer.parseInt(st.nextToken());
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
						n, m, map
					)
				)
			);
			bw.flush();
		}
	}

	final int[] rg={-1, 0, 1, 0};
	final int[] cg={0, 1, 0, -1};

	int resolve(int n, int m, int[][] map) {
		int cnt=0;
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				if(map[i][j]==0) {
					cnt++;
					Queue<int[]> q=new ArrayDeque<>();
					q.add(new int[] {i, j});
					while(!q.isEmpty()) {
						int[] curs=q.poll();
						if(map[curs[0]][curs[1]]!=0) continue;
						map[curs[0]][curs[1]]=1;
						for(int k=0; k<4; k++) {
							int ny=curs[0]+rg[k];
							int nx=curs[1]+cg[k];
							if(ny==-1) ny=n-1;
							if(nx==-1) nx=m-1;
							if(ny==n) ny=0;
							if(nx==m) nx=0;
							if(ny>=0 && nx>=0 && ny<n && nx<m && map[ny][nx]==0) {
								q.add(new int[] {ny, nx});
							}
						}
					}
				}
			}
		}
		return cnt;
	}
}
