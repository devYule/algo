import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			bw.write(
				String.valueOf(
					new Main().resolve(
						Integer.parseInt(br.readLine()), 
						Arrays.stream(br.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray()
					)
				)
			);
			bw.flush();
		}
	}

	int resolve(int n, int[] block) {
		int sum=Arrays.stream(block).sum();
		int[] dp=new int[sum+1];
		Arrays.fill(dp, -1);
		dp[0]=0;
		for(int i=0; i<n; i++) {
			int[] newDp=dp.clone();
			int b=block[i];
			for(int j=0; j<=sum; j++) {
				if(dp[j]==-1) continue;
				int hi=dp[j];
				int lo=dp[j]-j;

				newDp[hi+b-lo]=Math.max(newDp[hi+b-lo], hi+b);
				
				if(lo+b>hi) newDp[lo+b-hi]=Math.max(newDp[lo+b-hi], lo+b);
				else newDp[hi-(lo+b)]=Math.max(newDp[hi-(lo+b)], hi);
			}
			dp=newDp;
		}
		return dp[0]==0 ? -1 : dp[0];
	}

}
