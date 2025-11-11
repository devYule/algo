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
		int[] lrdp=new int[n];
		int[] rldp=new int[n];

		for(int i=0; i<n; i++) {
			lrdp[i]=1;
			for(int j=0; j<i; j++) {
				if(nums[i]>nums[j]) lrdp[i]=Math.max(lrdp[i], lrdp[j]+1);
			}
		}

		for(int i=n-1; i>=0; i--) {
			rldp[i]=1;
			for(int j=i+1; j<n; j++) {
				if(nums[i]>nums[j]) rldp[i]=Math.max(rldp[i], rldp[j]+1);
			}
		}

		int ret=0;
		for(int i=0; i<n; i++) {
			ret=Math.max(ret, lrdp[i]+rldp[i]);
		}
		return ret-1;
	}

}
