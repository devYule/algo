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

	String resolve(int n, int[] nums) {
		Arrays.sort(nums);
		int[] rets=new int[3];
		long ret=Long.MAX_VALUE;
		for(int i=0; i<n-1; i++) {
			for(int j=i+1; j<n; j++) {
				long two=nums[i]+nums[j];
				int lo=j+1, hi=n-1;
				long best=Long.MAX_VALUE;
				int bm=-1;
				while(lo<=hi) {
					int mid=(lo+hi)>>>1;
					long three=two+nums[mid];
					if(Math.abs(three)<best) {
						best=Math.abs(three);
						bm=mid;
					}
					if(three>0) hi=mid-1; 
					else if(three<0) lo=mid+1;
					else break;
				}
				if(ret>best) {
					ret=best;
					rets[0]=nums[i];
					rets[1]=nums[j];
					rets[2]=nums[bm];
				}
			}
		}
		Arrays.sort(rets);
		return rets[0] + " " + rets[1] + " " + rets[2];
	}

}
