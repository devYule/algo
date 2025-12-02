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
		int costMax=Arrays.stream(cost).sum();
		int[] dp=new int[costMax+1];
		dp[0]=0;
		for(int i=0; i<n; i++) {
			int f=free[i];
			int c=cost[i];
			for(int j=costMax; j>=c; j--) {
				dp[j]=Math.max(dp[j], dp[j-c]+f);
			}
		}
		for(int i=0; i<=costMax; i++) {
			if(dp[i]>=m) return i;
		}
		return 0;
	}

}
