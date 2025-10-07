import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			StringTokenizer st=new StringTokenizer(br.readLine());
			int n=Integer.parseInt(st.nextToken());
			int m=Integer.parseInt(st.nextToken());
			int k=Integer.parseInt(st.nextToken());

			int[][] map=new int[n][];
			for(int i=0; i<n; i++) {
				map[i]=Arrays.stream(br.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();
			}

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

	final int[][] g={
		{-1, 0}, {0, 1}, {1, 0}, {0, -1}
	};
	final String POS="POSSIBLE", NEG="IMPOSSIBLE";

	String resolve(int n, int m, int k, int[][] map) {
		int used=0;
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				if(map[i][j]==0) {
					int zc=0;
					Queue<int[]> q=new ArrayDeque<>();
					q.add(new int[] {i, j});
					while(!q.isEmpty()) {
						int[] ax=q.poll();
						if(map[ax[0]][ax[1]]!=0) continue;
						zc++;
						map[ax[0]][ax[1]]=1;
						for(int l=0; l<4; l++) {
							int ny=ax[0]+g[l][0];
							int nx=ax[1]+g[l][1];
							if(ny>=0 && nx>=0 && ny<n && nx<n && map[ny][nx]==0) q.add(new int[] {ny, nx});
						}
					}
					used+=zc/k;
					if(zc%k>0) used++;
					if(used>m) return NEG;
				}
			}
		}
		if(used==0 || used>m) return NEG;
		return POS + "\n" + (m-used);
	}

}
