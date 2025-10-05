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

	final int MOD=1_000_000_009;
	String resolve(int n, int[] nums) {
		int max=Arrays.stream(nums).max().orElse(0);
		int[][] dp=new int[max+1][4];

		dp[1][1]=1;
		dp[2][2]=1;
		dp[3][1]=1;
		dp[3][2]=1;
		dp[3][3]=1;

		for(int i=4; i<=max; i++) {
			for(int j=1; j<=3; j++) {
				for(int k=1; k<=3; k++) {
					if(j==k) continue;
					dp[i][j]=(dp[i][j]+dp[i-j][k])%MOD;
				}
			}
		}

		List<Integer> ret=new ArrayList<>();
		for(int num: nums) ret.add((int)((dp[num][1]+(long)dp[num][2]+dp[num][3])%MOD));

		return ret.stream().map(String::valueOf).collect(java.util.stream.Collectors.joining("\n"));
	}

}
