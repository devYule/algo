import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			String[] split=br.readLine().split("\\s+");
			int n=Integer.parseInt(split[0]);
			int m=Integer.parseInt(split[1]);

			int[][] prices=new int[m][];
			for(int i=0; i<m; i++) {
				prices[i]=Arrays.stream(br.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
			}

			bw.write(
				String.valueOf(
					new Main().resolve(
						n, m, prices
					)
				)
			);
			bw.flush();
		}
	}

	int resolve(int n, int m, int[][] prices) {
		int[] best=new int[] {1001, 1001};
		for(int[] p: prices) {
			best[0]=Math.min(best[0], p[0]);
			best[1]=Math.min(best[1], p[1]);
		}

		int[] dp=new int[n+1];
		for(int i=1; i<=Math.min(6, n); i++) dp[i]=Math.min(best[0], best[1]*i);
		for(int i=7; i<=n; i++) dp[i]=Math.min(dp[i-6]+dp[6], dp[i-1]+dp[1]);
		return dp[n];
	}

}
