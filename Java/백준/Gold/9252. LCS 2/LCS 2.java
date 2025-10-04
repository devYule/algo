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
		int[][] parent=new int[n+1][m+1];
		for(int i=1; i<=n; i++) {
			for(int j=1; j<=m; j++) {
				if(a.charAt(i-1)==b.charAt(j-1)) {
					dp[i][j]=dp[i-1][j-1]+1;
					parent[i][j]=3;
				} else {
					if(dp[i-1][j]>dp[i][j-1]) {
						parent[i][j]=2;
						dp[i][j]=dp[i-1][j];
					} else {
						parent[i][j]=1;
						dp[i][j]=dp[i][j-1];
					}
				}
			}
		}

		if(dp[n][m]==0) return "0";

		StringBuilder sb=new StringBuilder();
		int i=n, j=m;
		int last=-1;
		while(i>=0 && j>=0) {
			if(parent[i][j]==3) {
				sb.append(a.charAt(i-1));
				i--;
				j--;
			} else if(parent[i][j]==2) i--;
			else j--;
		}
		return dp[n][m] + "\n" + sb.reverse().toString();
		
	}

}
