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
		long[] dp=new long[n+1];
		for(int i=1; i<=Math.min(6, n); i++) dp[i]=i;
		for(int i=7; i<=n; i++) {
			dp[i]=dp[i-1]+1;
			for(int j=i-3; j>=1; j--) {
				dp[i]=Math.max(dp[i], dp[j]*(i-j-1));
			}
		}
		return dp[n];
	}
}
