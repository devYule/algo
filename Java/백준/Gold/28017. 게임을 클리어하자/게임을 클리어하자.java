import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			StringTokenizer st=new StringTokenizer(br.readLine());
			int n=Integer.parseInt(st.nextToken());
			int m=Integer.parseInt(st.nextToken());

			int[][] info=new int[n][m];
			for(int i=0; i<n; i++) {
				st=new StringTokenizer(br.readLine());
				for(int j=0; j<m; j++) {
					info[i][j]=Integer.parseInt(st.nextToken());
				}
			}

			bw.write(
				String.valueOf(
					new Main().resolve(
						n, m, info
					)
				)
			);
			bw.flush();
		}
	}

	int resolve(int n, int m, int[][] info) {
		int[][] dp=new int[n][m];
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				if(i==0) dp[i][j]=info[i][j];
				else dp[i][j]=(int)1e9;
			}
		}
		
		for(int i=1; i<n; i++) {
			for(int j=0; j<m; j++) {
				for(int k=0; k<m; k++) {
					if(j==k) continue;
					dp[i][j]=Math.min(dp[i][j], dp[i-1][k]+info[i][j]);
				}
			}
		}

		int min=dp[n-1][0];
		for(int i=1; i<m; i++) min=Math.min(min, dp[n-1][i]);
		return min;
	}
}
