import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			String[] split=br.readLine().split("\\s");
			int c=Integer.parseInt(split[0]);
			int n=Integer.parseInt(split[1]);
			int[][] info=new int[n][];
			for(int i=0; i<n; i++) {
				info[i]=Arrays.stream(br.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();
			}

			bw.write(
				String.valueOf(
					new Main().resolve(
						c, n, info
					)
				)
			);
			bw.flush();
		}
	}

	int resolve(int c, int n, int[][] info) {
		int[] dp=new int[c+1];
		java.util.Arrays.fill(dp, (int)1e9);
		dp[0]=0;
		for(int i=1; i<=c; i++) {
			for(int[] in: info) {
				int pay=in[0];
				int ppl=in[1];
				int idx=Math.max(0, i-ppl);
				dp[i]=Math.min(dp[i], dp[idx]+pay);
			}
		}
		return dp[c];
	}
}
