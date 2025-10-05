import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			int n=Integer.parseInt(br.readLine());
			int[] nums=new int[n];
			for(int i=0; i<n; i++) nums[i]=Integer.parseInt(br.readLine());

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

	String resolve(int n, int[] nums) {
		int max=Arrays.stream(nums).max().orElse(0);
		int[][] dp=new int[max+1][4];
		for(int i=1; i<=max; i++) dp[i][1]=1;


		dp[2][2]=1;
		dp[3][2]=1;
		dp[3][3]=1;

		for(int i=4; i<=max; i++) {
			for(int j=2; j<=3; j++) {
				for(int k=1; k<=j; k++) {
					dp[i][j]+=dp[i-j][k];
				}
			}
		}
		List<Integer> ret=new ArrayList<>();
		for(int num: nums) ret.add(dp[num][1]+dp[num][2]+dp[num][3]);

		return ret.stream().map(String::valueOf).collect(java.util.stream.Collectors.joining("\n"));

	}

}
