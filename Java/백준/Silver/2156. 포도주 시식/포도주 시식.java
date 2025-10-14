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
		int[] dp=new int[n+1];
		if(n<=2) return Arrays.stream(wine).sum();
		
		dp[1]=wine[0];
		dp[2]=wine[0]+wine[1];

		int ret=0;
		for(int i=3; i<=n; i++) {
			ret=Math.max(ret, dp[i]=Math.max(
				dp[i-1], Math.max(
					wine[i-1]+dp[i-2],
					wine[i-1]+wine[i-2]+dp[i-3]
				)
			));
		}
		return ret;
	}

}
