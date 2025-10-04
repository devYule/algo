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

	final int MOD=1_000_000_000;
	int resolve(int n) {
		int[][] dp=new int[n+1][10];
		for(int i=1; i<=9; i++) dp[1][i]=1;
		for(int i=2; i<=n; i++) {
			for(int j=0; j<10; j++) {
				if(j>0) dp[i][j]=dp[i-1][j-1];
				if(j<9) dp[i][j]=(dp[i][j]+dp[i-1][j+1]%MOD)%MOD;
			}
		}

		int ret=0;
		for(int i=0; i<10; i++) {
			ret=(ret%MOD+dp[n][i]%MOD)%MOD;
		}
		return ret;
	}

}
