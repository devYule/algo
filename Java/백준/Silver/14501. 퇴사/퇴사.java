import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			int n=Integer.parseInt(br.readLine());

			int[][] job=new int[n][2];
			for(int i=0; i<n; i++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				job[i][0]=Integer.parseInt(st.nextToken());
				job[i][1]=Integer.parseInt(st.nextToken());
			}

			bw.write(
				String.valueOf(
					new Main().resolve(
						n, job
					)
				)
			);
			bw.flush();
		}
	}

	int resolve(int n, int[][] jobs) {
		int[] dp=new int[n+2];
		for(int i=n; i>=1; i--) {
			int[] job=jobs[i-1];
			dp[i]=dp[i+1];
			if(job[0]+i<=n+1) dp[i]=Math.max(dp[i], dp[i+job[0]]+job[1]);
		}
		return dp[1];
	}

}
