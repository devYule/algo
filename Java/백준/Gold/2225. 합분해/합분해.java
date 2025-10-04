import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			StringTokenizer st=new StringTokenizer(br.readLine());

			bw.write(
				String.valueOf(
					new Main().resolve(
						Integer.parseInt(st.nextToken()),
						Integer.parseInt(st.nextToken())
					)
				)
			);
			bw.flush();
		}
	}

	final int MOD=1_000_000_000;
	int resolve(int n, int k) {
		int[] dp=new int[n+1];
		Arrays.fill(dp, 1);

		for(int i=2; i<=k; i++) {
			for(int j=0; j<=n; j++) {
				if(j==0) dp[j]=1;
				else dp[j]=(dp[j-1]+dp[j])%MOD;
			}
		}
		return dp[n];
	}

}
