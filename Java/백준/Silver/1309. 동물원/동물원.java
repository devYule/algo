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

	final int MOD=9901;
	int resolve(int n) {
		int[] dp=new int[n+1];
		dp[0]=1;
		dp[1]=3;
		for(int i=2; i<=n; i++) {
			dp[i]=(((dp[i-1]*2)%MOD)+dp[i-2]%MOD)%MOD;
		}
		return dp[n];
	}

}
