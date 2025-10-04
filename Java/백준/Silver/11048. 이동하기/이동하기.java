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

	int resolve(int n, int m, int[][] map) {
		int[][] dp=new int[n][m];
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				if(i>0) dp[i][j]=dp[i-1][j];
				if(j>0) dp[i][j]=Math.max(dp[i][j], dp[i][j-1]);
				if(i>0 && j>0) dp[i][j]=Math.max(dp[i][j], dp[i-1][j-1]);
				dp[i][j]+=map[i][j];
			}
		}
		return dp[n-1][m-1];
	}
}
