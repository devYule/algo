import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			int n=Integer.parseInt(br.readLine());
			int[] nums=Arrays.stream(br.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();


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

	int resolve(int n, int[] nums) {
		int[] dp=new int[n];
		for(int i=0; i<n; i++) dp[i]=nums[i];
		for(int i=0; i<n; i++) {
			for(int j=i+1; j<n; j++) {
				if(nums[i]<nums[j]) {
					dp[j]=Math.max(dp[j], dp[i]+nums[j]);
				}
			}
		}
		return Arrays.stream(dp).max().orElseThrow();
	}

}
