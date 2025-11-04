import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			StringTokenizer st=new StringTokenizer(br.readLine());
			int n=Integer.parseInt(st.nextToken());
			int m=Integer.parseInt(st.nextToken());

			int[] nums=new int[n];
			st=new StringTokenizer(br.readLine());
			for(int i=0; i<n; i++) {
				nums[i]=Integer.parseInt(st.nextToken());
			}

			bw.write(
				String.valueOf(
					new Main().resolve(
						n, m, nums
					)
				)
			);
			bw.flush();
		}
	}

	long resolve(int n, int m, int[] nums) {
		int[] darr=new int[m];
		long sum=0;
		for(int num: nums) {
			sum+=num;
			darr[(int)(sum%m)]++;
		}

		long ret=darr[0];
		for(int i=0; i<m; i++) {
			if(darr[i]>1) ret+=darr[i]*(darr[i]-1L)/2;
		}
		return ret;
	}

}
