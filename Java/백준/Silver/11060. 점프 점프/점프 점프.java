import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			bw.write(
				String.valueOf(
					new Main().resolve(
						Integer.parseInt(br.readLine()),
						Arrays.stream(br.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray()
					)
				)
			);
			bw.flush();
		}
	}

	int resolve(int n, int[] stone) {
		int[] dp=new int[n];
		Arrays.fill(dp, (int)1e9);
		dp[0]=0;
		for(int i=0; i<n; i++) {
			int jump=stone[i];
			for(int j=1; j<=jump; j++) {
				if(i+j>=n) break;
				dp[i+j]=Math.min(dp[i+j], dp[i]+1);
			}
		}
		return dp[n-1]==(int)1e9 ? -1 : dp[n-1];
	}

}
