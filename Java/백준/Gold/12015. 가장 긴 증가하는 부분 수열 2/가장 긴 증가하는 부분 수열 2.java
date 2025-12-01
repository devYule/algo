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
		int[] tails=new int[n];
		int ti=1;
		tails[0]=nums[0];
		for(int i=1; i<n; i++) {
			int cur=nums[i];
			if(cur>tails[ti-1]) {
				tails[ti++]=cur;
			} else {
				int loc=tails(0, ti-1, tails, cur);
				tails[loc]=cur;
			}
		}
		return ti;
	}

	int tails(int lo, int hi, int[] tails, int target) {
		while(lo<=hi) {
			int mid=(lo+hi)>>>1;
			if(tails[mid]>=target) hi=mid-1;
			else lo=mid+1;
		}
		return lo;
	}

}
