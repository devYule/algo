import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			int n=Integer.parseInt(br.readLine());
			int[] T=new int[n];
			int[] P=new int[n];

			for(int i=0; i<n; i++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				T[i]=Integer.parseInt(st.nextToken());
				P[i]=Integer.parseInt(st.nextToken());
			}

			bw.write(
				String.valueOf(
					new Main().resolve(
						n, T, P
					)
				)
			);
			bw.flush();
		}
	}

	int resolve(int n, int[] T, int[] P) {
		int[] dp=new int[n+1];
		for(int i=n-1; i>=0; i--) {
			if(i+T[i]-1<n) {
				dp[i]=dp[i+T[i]]+P[i];
			}
			dp[i]=Math.max(dp[i], dp[i+1]);
		}
		return dp[0];
	}

}
