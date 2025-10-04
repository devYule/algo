import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			int n=Integer.parseInt(br.readLine());
			int[][] jobs=new int[n][2];
			for(int i=0; i<n; i++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				jobs[i][0]=Integer.parseInt(st.nextToken());
				jobs[i][1]=Integer.parseInt(st.nextToken());
			}

			bw.write(
				String.valueOf(
					new Main().resolve(
						n, jobs
					)
				)
			);
			bw.flush();
		}
	}

	long resolve(int n, int[][] jobs) {
		long[] dp=new long[n+1];
		for(int i=n-1; i>=0; i--) {
			dp[i]=dp[i+1];
			if(i+jobs[i][0]<dp.length) dp[i]=Math.max(dp[i], dp[i+jobs[i][0]]+jobs[i][1]);
		}
		return dp[0];
	}

}
