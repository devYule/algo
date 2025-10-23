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
		int[] incDp=new int[n];
		int[] decDp=new int[n];
		for(int i=0; i<n; i++) { incDp[i]=1; decDp[i]=1; }

		for(int i=0; i<n; i++) {
			for(int j=0; j<i; j++) {
				if(nums[i]>nums[j]) incDp[i]=Math.max(incDp[i], incDp[j]+1);
			}
		}

		for(int i=n-1; i>=0; i--) {
			for(int j=i+1; j<n; j++) {
				if(nums[i]>nums[j]) decDp[i]=Math.max(decDp[i], decDp[j]+1);
			}
		}

		int ret=0;
		for(int i=0; i<n; i++) {
			ret=Math.max(ret, incDp[i]+decDp[i]-1);
		}
		return ret;
	}

}
