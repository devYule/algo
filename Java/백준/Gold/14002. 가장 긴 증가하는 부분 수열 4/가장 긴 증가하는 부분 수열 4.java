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

	String resolve(int n, int[] nums) {
		int[] dp=new int[n];
		Arrays.fill(dp, 1);
		for(int i=0; i<n; i++) {
			for(int j=i+1; j<n; j++) {
				if(nums[i]<nums[j]) {
					dp[j]=Math.max(dp[j], dp[i]+1);
				}
			}
		}

		List<Integer> ret=new ArrayList<>();
		int max=Arrays.stream(dp).max().orElseThrow();
		int rm=max;

		for(int i=n-1; i>=0; i--) {
			if(dp[i]==max) {
				ret.add(nums[i]);
				max--;
			}
		}
		Collections.reverse(ret);

		return rm + "\n" + ret.stream().map(String::valueOf).collect(java.util.stream.Collectors.joining(" "));
	}

}
