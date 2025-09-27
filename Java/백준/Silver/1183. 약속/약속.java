import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			int n=Integer.parseInt(br.readLine());
			int[][] nums=new int[n][2];
			for(int i=0; i<n; i++) {
				String[] spl=br.readLine().split("\\s");
				nums[i][0]=Integer.parseInt(spl[0]);
				nums[i][1]=Integer.parseInt(spl[1]);
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

	int[][] nums;
	int resolve(int n, int[][] nums) {
		this.nums=nums;

		int best=0;
		long bGap=Long.MAX_VALUE;
		for(int[] num: nums) {
			int candi=num[1]-num[0];
			long itSum=calc(candi);
			if(itSum<bGap) {
				best=candi;
				bGap=itSum;
			}
		}
		
		int min=(int)1e9+1;
		int max=-(int)1e9;
		for(int[] num: nums) {
			int candi=num[1]-num[0];
			if(calc(candi)==bGap) {
				min=Math.min(min, candi);
				max=Math.max(max, candi);
			}
		}
		return min==max ? 1 : Math.abs(max-min)+1;
	}

	long calc(int dist) {
		long sum=0;
		for(int[] num: nums) {
			sum+=Math.abs((num[0]+dist)-num[1]);
		}
		return sum;
	}
}
