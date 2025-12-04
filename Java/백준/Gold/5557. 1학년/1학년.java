import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			int n=Integer.parseInt(br.readLine());
			int[] nums=new int[n];
			StringTokenizer st=new StringTokenizer(br.readLine());
			for(int i=0; i<n; i++) {
				nums[i]=Integer.parseInt(st.nextToken());
			}

			bw.write(
				String.valueOf(
					new Main().resolve(
						n, nums
					)
				)
			);
			bw.flush();
		}
	}

	long[][] memo;
	int n, nums[];

	long resolve(int n, int[] nums) {

		long[][] dp=new long[n-1][21];
		dp[0][nums[0]]=1;
		for(int i=1; i<n-1; i++) {
			int cur=nums[i];
			for(int j=0; j<21; j++) {
				if(dp[i-1][j]==0) continue;
				if(j+cur<=20) dp[i][j+cur]+=dp[i-1][j];
				if(j-cur>=0) dp[i][j-cur]+=dp[i-1][j];
			}
		}
		return dp[n-2][nums[n-1]];
	}
}
