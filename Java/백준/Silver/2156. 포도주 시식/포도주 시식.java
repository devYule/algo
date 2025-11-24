import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			int n=Integer.parseInt(br.readLine());
			int[] wine=new int[n];
			for(int i=0; i<n; i++) wine[i]=Integer.parseInt(br.readLine());

			bw.write(
				String.valueOf(
					new Main().resolve(
						n, wine
					)
				)
			);
			bw.flush();
		}
	}

	int resolve(int n, int[] wine) {
		if(n<=2) {
			int r=0;
			for(int i=0; i<n; i++) r+=wine[i];
			return r;
		}
		int[] dp=new int[n+1];
		dp[1]=wine[0];
		dp[2]=wine[0]+wine[1];
		for(int i=3; i<=n; i++) {
			int wi=i-1;
			dp[i]=dp[i-1];
			dp[i]=Math.max(
				dp[i],
				Math.max(
					wine[wi]+wine[wi-1]+dp[i-3],
					wine[wi]+dp[i-2]
				)
			);
		}
		return dp[n];
	}

}
