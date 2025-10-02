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
			int k=Integer.parseInt(br.readLine());

			bw.write(
				String.valueOf(
					new Main().resolve(
						n, m, k, map
					)
				)
			);
			bw.flush();
		}
	}
	
	int[] rg={-1, 0, 1, 0};
	int[] cg={0, 1, 0, -1};

	String resolve(int n, int m, int k, int[][] map) {
		PriorityQueue<int[]> corn=new PriorityQueue<>((a, b)-> b[2]-a[2]);
		boolean[][] added=new boolean[n][m];
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				if(i==0 || j==0 || i==n-1 || j==m-1) {
					corn.add(new int[] {i, j, map[i][j]});
					added[i][j]=true;
				}
			}
		}
		StringBuilder sb=new StringBuilder(4*k-1);
		for(int i=0; i<k; i++) {
			int[] c=corn.poll();
			int y=c[0], x=c[1];
			sb.append(y+1).append(" ").append(x+1);
			if(i<k-1) sb.append("\n");
			for(int j=0; j<4; j++) {
				int ny=y+rg[j], nx=x+cg[j];
				if(ny>=0 && nx>=0 && ny<n && nx<m && !added[ny][nx]) {
					corn.add(new int[] {ny, nx, map[ny][nx]});
					added[ny][nx]=true;
				}
			}
		}
		return sb.toString();
	}
}
