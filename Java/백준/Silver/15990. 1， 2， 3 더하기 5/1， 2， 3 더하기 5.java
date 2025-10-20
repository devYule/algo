import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			int n=Integer.parseInt(br.readLine());
			int[] cases=new int[n];
			for(int i=0; i<n; i++) cases[i]=Integer.parseInt(br.readLine());

			bw.write(
				String.valueOf(
					new Main().resolve(
						n, cases
					)
				)
			);
			bw.flush();
		}
	}

	String resolve(int n, int[] cases) {
		final int MOD=1_000_000_009;
		int max=Arrays.stream(cases).max().orElse(0);
		int[][] dp=new int[max+1][4];
		dp[1][1]=1;
		dp[2][2]=1;
		dp[3][1]=1;
		dp[3][2]=1;
		dp[3][3]=1;

		for(int i=4; i<=max; i++) {
			for(int j=1; j<=3; j++) {
				for(int k=1; k<=3; k++) {
					if(j==k || i-j<0 || i<j) continue;
					dp[i][j]=(dp[i][j]+(dp[i-j][k]%MOD))%MOD;
				}
			}
		}
		dp[0][1]=dp[0][2]=dp[0][3]=0;
		StringBuilder sb=new StringBuilder();
		for(int i=0; i<n; i++) {
			if(i!=0) sb.append("\n");
			sb.append(String.valueOf(((dp[cases[i]][1]+dp[cases[i]][2])%MOD+dp[cases[i]][3])%MOD));
		}
		return sb.toString();
	}

}
