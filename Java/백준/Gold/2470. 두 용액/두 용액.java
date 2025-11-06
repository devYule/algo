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
		int ba=-1;
		int bb=-1;
		int zeroGap=Integer.MAX_VALUE;
		for(int i=0; i<n; i++) {
			int cur=nums[i];
			int target=-cur;
			int cUpper=upper(target, nums);
			int cLower=lower(target, nums);

			if(cUpper!=cur) {
				int ugap=Math.abs(cur+cUpper);
				if(ugap<zeroGap) {
					ba=cur;
					bb=cUpper;
					zeroGap=ugap;
				}
			}
			if(cLower!=cur) {
				int lgap=Math.abs(cur+cLower);
				if(lgap<zeroGap) {
					ba=cur;
					bb=cLower;
					zeroGap=lgap;
				}
			}

		}
		return Math.min(ba, bb) + " " + Math.max(ba, bb);
	}

	int upper(int target, int[] arr) {
		int lo=0, hi=arr.length-1;
		while(lo<=hi) {
			int mid=(lo+hi)>>>1;
			if(arr[mid]>=target) hi=mid-1;
			else lo=mid+1;
		}
		if(lo>=arr.length) return -target;
		return arr[lo];
	}

	int lower(int target, int[] arr) {
		int lo=0, hi=arr.length-1;
		while(lo<=hi) {
			int mid=(lo+hi)>>>1;
			if(arr[mid]<=target) lo=mid+1;
			else hi=mid-1;
		}
		if(hi<0) return -target;
		return arr[hi];
	}

}
