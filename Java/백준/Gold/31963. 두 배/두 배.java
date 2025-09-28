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

		int ret=0;
		for(int i=1; i<n; i++) {
			if(nums[i]<nums[i-1]) {
				while(nums[i]<nums[i-1]) { nums[i]*=2; ret++; }
			}
		}

		return ret;
	}

}
