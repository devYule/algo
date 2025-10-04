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
		int[][] dp=new int[k+1][n+1];
		for(int i=0; i<=n; i++) dp[1][i]=1;
		for(int i=2; i<=k; i++) {
			for(int j=0; j<=n; j++) {
				for(int l=j; l>=0; l--) {
					dp[i][j]=(dp[i][j]+dp[i-1][j-l]%MOD)%MOD;
				}
			}
		}
		return dp[k][n];
	}

}
