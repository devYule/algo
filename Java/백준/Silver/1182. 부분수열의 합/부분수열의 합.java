import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			String[] spl=br.readLine().split("\\s");
			int n=Integer.parseInt(spl[0]);
			int s=Integer.parseInt(spl[1]);
			int[] nums=Arrays.stream(br.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();

			bw.write(
				String.valueOf(
					new Main().resolve(
						n, s, nums
					)
				)
			);
			bw.flush();
		}
	}

	int n, s, nums[];
	int resolve(int n, int s, int[] nums) {
		this.n=n; this.s=s; this.nums=nums;
		return find(0, 0, 0);
	}

	int find(int idx, long sum, int sel) {
		int ret=sel!=0 && sum==s ? 1 : 0;
		for(int i=idx; i<n; i++) {
			ret+=find(i+1, sum+nums[i], sel|1<<i);
		}
		return ret;
	}

}
