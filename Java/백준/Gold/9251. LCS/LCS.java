import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			bw.write(
				String.valueOf(
					new Main().resolve(
						br.readLine(),
						br.readLine()
					)
				)
			);
			bw.flush();
		}
	}

	int resolve(String a, String b) {
		int n=a.length(), m=b.length();
		int[][] dp=new int[n+1][m+1];
		for(int i=1; i<=n; i++) {
			for(int j=1; j<=m; j++) {
				int cost=a.charAt(i-1)==b.charAt(j-1) ? 1 : 0;
				dp[i][j]=Math.max(dp[i-1][j-1]+cost, Math.max(dp[i-1][j], dp[i][j-1]));
			}
		}
		return dp[n][m];
	}

}
