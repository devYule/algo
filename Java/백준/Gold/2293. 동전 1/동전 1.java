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

	int resolve(int n, int k, int[] nums) {
		int[] dp=new int[k+1];
		dp[0]=1;
		for(int i=0; i<n; i++) {
			int num=nums[i];
			for(int j=0; j<=k; j++) {
				if(j>=num) dp[j]+=dp[j-num];
			}
		}
		return dp[k];
	}
}
