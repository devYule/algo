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
		int[] loci=new int[n];
		int ti=1;
		tails[0]=nums[0];
		for(int i=1; i<n; i++) {
			int cur=nums[i];
			if(tails[ti-1]<cur) {
				tails[ti]=cur;
				loci[i]=ti++;
			} else {
				int loc=loc(tails, cur, ti-1);
				tails[loc]=cur;
				loci[i]=loc;
			}
		}
		List<Integer> ret=new ArrayList<>();
		int li=ti-1;
		for(int i=n-1; i>=0; i--) {
			if(loci[i]==li) {
				li--;
				ret.add(nums[i]);
			}
		}
		Collections.reverse(ret);

		return ti + "\n" + ret.stream().map(String::valueOf).collect(java.util.stream.Collectors.joining(" "));
	}

	int loc(int[] tails, int target, int hi) {
		int lo=0;
		while(lo<=hi) {
			int mid=(lo+hi)>>>1;
			if(tails[mid]>=target) hi=mid-1;
			else lo=mid+1;
		}
		return lo;
	}

}
