import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			String[] split=br.readLine().split("\\s");
			int m=Integer.parseInt(split[0]);
			int n=Integer.parseInt(split[1]);

			int[][] map=new int[n][];
			for(int i=0; i<n; i++) {
				map[i]=Arrays.stream(br.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();
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

	final String pos="Yes", neg="No";
	final int[] rg={0, 1};
	final int[] cg={1, 0};
	String resolve(int n, int m, int[][] map) {
		Queue<int[]> q=new ArrayDeque<>();
		q.add(new int[] {0, 0});
		boolean[][] vis=new boolean[n][m];
		vis[0][0]=true;

		while(!q.isEmpty()) {
			if(vis[n-1][m-1]) return pos;

			int[] curAx=q.poll();
			int cury=curAx[0];
			int curx=curAx[1];

			for(int i=0; i<2; i++) {
				int ny=cury+rg[i];
				int nx=curx+cg[i];
				if(ny>=0 && nx>=0 && ny<n && nx<m && map[ny][nx]==1 && !vis[ny][nx]) {
					q.add(new int[] {ny, nx});
					vis[ny][nx]=true;
				}
			}
		}
		return neg;
	}

}
