import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			int n=Integer.parseInt(br.readLine());
			int[] nums=new int[n];
			StringTokenizer st=new StringTokenizer(br.readLine());
			for(int i=0; i<n; i++) {
				nums[i]=Integer.parseInt(st.nextToken());
			}

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
		int[] cnt=new int[n];

		int[] tails=new int[n];
		int ti=1;
		tails[0]=nums[0];
		cnt[0]++;
		for(int i=1; i<n; i++) {
			if(nums[i]>tails[ti-1]) {
				tails[ti++]=nums[i];
				cnt[i]+=ti;
			} else {
				int loc=binsearch(0, ti-1, tails, nums[i]);
				cnt[i]+=loc+1;
				tails[loc]=nums[i];
			}
		}

		tails=new int[n];
		ti=1;
		tails[0]=nums[n-1];
		cnt[n-1]++;
		for(int i=n-2; i>=0; i--) {
			if(nums[i]>tails[ti-1]) {
				tails[ti++]=nums[i];
				cnt[i]+=ti;
			} else {
				int loc=binsearch(0, ti-1, tails, nums[i]);
				tails[loc]=nums[i];
				cnt[i]+=loc+1;
			}
		}

		int ret=0;
		for(int i=0; i<n; i++) {
			ret=Math.max(ret, cnt[i]);
		}
		return ret-1;
	}

	int binsearch(int l, int h, int[] tails, int target) {
		while(l<=h) {
			int mid=(l+h) >>> 1;
			if(tails[mid]>=target) h=mid-1;
			else l=mid+1;
		}
		return l;
	}

}
