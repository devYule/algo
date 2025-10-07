import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			StringTokenizer st=new StringTokenizer(br.readLine());

			bw.write(
				String.valueOf(
					new Main().resolve(
						Integer.parseInt(st.nextToken()),
						Integer.parseInt(st.nextToken())
					)
				)
			);
			bw.flush();
		}
	}

	String resolve(int n, int k) {
		int[][] dp=new int[n+1][2];
		for(int i=0; i<=n; i++) Arrays.fill(dp[i], k+1);
		dp[1][1]=1;
		for(int i=2; i<=n; i++) {
			dp[i][1]=Math.min(dp[i-1][1], dp[i-1][0])+1;
			int candi=i*2/3+(i*2%3==0 ? 0 : 1);
			if(candi+candi/2==i) dp[i][0]=Math.min(dp[candi][0], dp[candi][1])+1;
		}
		if(dp[n][0]<=k || dp[n][1]<=k) return "minigimbob";
		return "water";
	}
}
