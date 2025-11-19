import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			StringTokenizer st=new StringTokenizer(br.readLine());
			int n=Integer.parseInt(st.nextToken());
			int s=Integer.parseInt(st.nextToken());

			int[] nums=new int[n];
			st=new StringTokenizer(br.readLine());
			for(int i=0; i<n; i++) nums[i]=Integer.parseInt(st.nextToken());

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

	List<Integer> left=new ArrayList<>();
	List<Integer> right=new ArrayList<>();
	int[] nums;
	long resolve(int n, int s, int[] nums) {
		this.nums=nums;

		int center=n/2;
		dfs(0, center, 0, left);
		dfs(center+1, n-1, 0, right);

		right.sort(Comparator.naturalOrder());

		long ret=0;
		for(int l: left) {
			int gap=s-l;
			int lo=0, hi=right.size()-1;
			while(lo<=hi) {
				int mid=(lo+hi)>>>1;
				if(right.get(mid)>=gap) hi=mid-1;
				else lo=mid+1;
			}
			if(lo>=right.size() || right.get(lo)!=gap) continue;
			int rl=lo;

			lo=0; hi=right.size()-1;
			while(lo<=hi) {
				int mid=(lo+hi)>>>1;
				if(right.get(mid)<=gap) lo=mid+1;
				else hi=mid-1;
			}
			int rr=hi;

			ret+=rr-rl+1;
		}
		if(s==0) ret--;
		return ret;
	}

	void dfs(int idx, int lim, int sum, List<Integer> holder) {
		if(idx>lim) {
			holder.add(sum);
			return;
		}

		dfs(idx+1, lim, sum+nums[idx], holder);
		dfs(idx+1, lim, sum, holder);
	}

}
