import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			int n=Integer.parseInt(br.readLine());
			int[] nums=new int[n];
			StringTokenizer st=new StringTokenizer(br.readLine());
			for(int i=0; i<n; i++) nums[i]=Integer.parseInt(st.nextToken());

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

		for(int i=1; i<n; i++) {
			for(int j=0; j<i; j++) {
				if(nums[i]>nums[j]) {
					dp[i]=Math.max(dp[i], dp[j]+nums[i]);
				}
			}
		}
		return Arrays.stream(dp).max().orElse(0);
	}

}
