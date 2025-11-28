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
			for(int i=0; i<n; i++) nums[i]=Integer.parseInt(st.nextToken());

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

	int resolve(int n, int m, int[] nums) {
		int l=0, r=0;
		int sum=0;
		int srt=n+1;
		while(l<n) {
			while(r<n && sum<m) sum+=nums[r++];
			if(sum>=m) srt=Math.min(srt, r-l);
			sum-=nums[l++];
		}
		return srt==n+1 ? 0 : srt;
	}

}
