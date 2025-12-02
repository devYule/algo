import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			StringTokenizer st=new StringTokenizer(br.readLine());
			int n=Integer.parseInt(st.nextToken());
			int m=Integer.parseInt(st.nextToken());
			int[] free=new int[n];
			int[] cost=new int[n];
			StringTokenizer stfree=new StringTokenizer(br.readLine());
			StringTokenizer stcost=new StringTokenizer(br.readLine());

			for(int i=0; i<n; i++) {
				free[i]=Integer.parseInt(stfree.nextToken());
				cost[i]=Integer.parseInt(stcost.nextToken());
			}

			bw.write(
				String.valueOf(
					new Main().resolve(
						n, m, free, cost
					)
				)
			);
			bw.flush();
		}
	}

	int resolve(int n, int m, int[] free, int[] cost) {
		int[] dp=new int[m+1];
		Arrays.fill(dp, (int)1e9);
		dp[0]=0;
		for(int i=0; i<n; i++) {
			int f=free[i];
			int c=cost[i];
			for(int j=m; j>=0; j--) {
				dp[j]=Math.min(dp[j], dp[Math.max(0, j-f)]+c);
			}
		}
		return dp[m];
	}

}
