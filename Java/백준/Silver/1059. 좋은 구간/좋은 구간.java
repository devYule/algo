import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			int l=Integer.parseInt(br.readLine());

			int[] nums=Arrays.stream(br.readLine().split("\\s"))
				.mapToInt(Integer::parseInt).toArray();
			int n=Integer.parseInt(br.readLine());

			bw.write(
				String.valueOf(
					new Main().resolve(
						l, nums, n
					)
				)
			);
			bw.flush();
		}
	}

	int resolve(int l, int[] nums, int n) {
		Arrays.sort(nums);
		int ret=0;
		int lo=1;
		int hi=-1;
		for(int i=0; i<nums.length; i++) {
			if(nums[i]==n) return 0;
			if(nums[i]<n) lo=nums[i]+1;
			if(nums[i]>n) {
				hi=nums[i];
				break;
			}
		}
		for(int i=lo; i<hi; i++) {
			for(int j=i+1; j<hi; j++) {
				if(i<=n && j>=n) {
					ret++;
				}
			}
		}

		return ret;
	}

}
