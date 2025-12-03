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

	int resolve(int n, int[] nums) {
		Arrays.sort(nums);
		long max=Arrays.stream(nums).max().orElse(0);
		List<Long> sum=new ArrayList<>();
		for(int i=0; i<n; i++) {
			for(int j=i; j<n; j++) {
				long r=nums[i]+(long)nums[j];
				if(r>max) continue;
				sum.add(r);
			}
		}

		sum.sort(Comparator.naturalOrder());

		for(int i=n-1; i>=0; i--) {
			for(int j=i; j>=0; j--) {
				long target=nums[i]-nums[j];
				int lo=0, hi=sum.size()-1;
				while(lo<=hi) {
					int mid=(lo+hi)>>>1;
					long candi=sum.get(mid);
					if(candi>target) hi=mid-1;
					else if(candi<target) lo=mid+1;
					else return nums[i];
				}
			}
		}
		return -1;
	}
}
