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

	String resolve(int n, int[] nums) {
		int[] tails=new int[n];
		int[] tis=new int[n];
		Arrays.fill(tis, -1);
		int ti=1;
		tails[0]=nums[0];
		tis[0]=0;
		for(int i=1; i<n; i++) {
			int num=nums[i];
			if(num>tails[ti-1]) {
				tis[i]=ti;
				tails[ti++]=num;
			} else {
				int loc=tails(0, ti-1, num, tails);
				tails[loc]=num;
				tis[i]=loc;
			}
		}

		List<Integer> res=new ArrayList<>();
		int ret=ti--;
		for(int i=n-1; i>=0; i--) {
			if(tis[i]==ti) {
				res.add(nums[i]);
				ti--;
			}
		}
		Collections.reverse(res);

		return ret + "\n" + res.stream().map(String::valueOf).collect(java.util.stream.Collectors.joining(" "));
	}

	int tails(int lo, int hi, int target, int[] tails) {
		while(lo<=hi) {
			int mid=(lo+hi)>>>1;
			if(tails[mid]>=target) hi=mid-1;
			else lo=mid+1;
		}
		return lo;
	}

}
