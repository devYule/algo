import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			StringTokenizer st=new StringTokenizer(br.readLine());
			int n=Integer.parseInt(st.nextToken());
			int m=Integer.parseInt(st.nextToken());
			int[] nums=new int[n];
			for(int i=0; i<n; i++) nums[i]=Integer.parseInt(br.readLine());

			bw.write(
				String.valueOf(
					new Main().resolve(
						n, m, nums
					)
				)
			);
			bw.flush();
		}
	}

	int resolve(int n, int m, int[] nums) {
		int[][] dp=new int[n][m+1];
		int[] sum=new int[n];
		sum[0]=nums[0];
		for(int i=1; i<n; i++) sum[i]=sum[i-1]+nums[i];
		for(int[] d: dp) Arrays.fill(d, Integer.MIN_VALUE);
		
		for(int i=0; i<n; i++) dp[i][0]=0;

		for(int i=0; i<n; i++) {
			for(int j=1; j<=m; j++) {
				if(i>0) dp[i][j]=dp[i-1][j];
				for(int k=0; k<=i; k++) {
					if(j==1) dp[i][j]=Math.max(dp[i][j], sum[i]-(k==0 ? 0 : sum[k-1]));
					else if(k>=2 && dp[k-2][j-1]!=Integer.MIN_VALUE) {
						dp[i][j]=Math.max(dp[i][j], dp[k-2][j-1]+(sum[i]-(k==0 ? 0 : sum[k-1])));
					}
				}
			}
		}
		return dp[n-1][m];
	}
}
