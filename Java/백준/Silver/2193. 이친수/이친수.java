import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			bw.write(
				String.valueOf(
					new Main().resolve(
						Integer.parseInt(br.readLine())
					)
				)
			);
			bw.flush();
		}
	}

	long resolve(int n) {
		if(n<=2) return 1;
		long[][] dp=new long[n+1][2];
		dp[3][0]=1;
		dp[3][1]=1;

		for(int i=4; i<=n; i++) {
			dp[i][0]=dp[i-1][0]+dp[i-1][1];
			dp[i][1]=dp[i-1][0];
		}

		return dp[n][0]+dp[n][1];
	}

}
