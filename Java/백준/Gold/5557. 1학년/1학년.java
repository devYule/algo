import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			bw.write(
				String.valueOf(
					new Main().resolve(
						Integer.parseInt(br.readLine()),
						Arrays.stream(br.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray()
					)
				)
			);
			bw.flush();
		}
	}

	long resolve(int n, int[] nums) {
		long[] dp=new long[21];
		dp[nums[0]]=1;

		for(int i=1; i<n-1; i++) {
			int num=nums[i];
			long[] newDp=new long[21];
			for(int j=0; j<21; j++) {
				if(dp[j]!=0) {
					if(j-num>=0) newDp[j-num]+=dp[j];
					if(j+num<=20) newDp[j+num]+=dp[j];
				}
			}
			dp=newDp;
		}
		return dp[nums[n-1]];
	}

}
