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

	String resolve(String a, String b) {
		int n=a.length(), m=b.length();
		int[][] dp=new int[n+1][m+1];
		for(int i=1; i<=n; i++) {
			for(int j=1; j<=m; j++) {
				int dist=0;
				if(a.charAt(i-1)==b.charAt(j-1)) dist=1;
				dp[i][j]=Math.max(dp[i-1][j-1]+dist, Math.max(dp[i-1][j], dp[i][j-1]));
			}
		}

		if(dp[n][m]==0) return "0";

		StringBuilder sb=new StringBuilder();
		int ai=n, bi=m;
		int cnt=dp[n][m];
		while(cnt>0) {
			if(a.charAt(ai-1)==b.charAt(bi-1)) {
				ai--; bi--;
				sb.append(String.valueOf(a.charAt(ai)));
				cnt--;
			} else {
				if(dp[ai][bi]==dp[ai-1][bi-1]) { ai--; bi--; }
				else if(dp[ai][bi]==dp[ai-1][bi]) ai--;
				else bi--;
			}
		}
		sb.reverse();

		return dp[n][m] + "\n" + sb;
	}
}
