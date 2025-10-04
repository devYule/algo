import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			int n=Integer.parseInt(br.readLine());
			int[][] each=new int[n][2];
			for(int i=0; i<n-1; i++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				each[i][0]=Integer.parseInt(st.nextToken());
				each[i][1]=Integer.parseInt(st.nextToken());
			}
			int k=Integer.parseInt(br.readLine());

			bw.write(
				String.valueOf(
					new Main().resolve(
						n, k, each
					)
				)
			);
			bw.flush();
		}
	}

	int resolve(int n, int k, int[][] each) {
		int[][] dp=new int[n][2];
		for(int i=0; i<n; i++) Arrays.fill(dp[i], (int)1e9);
		dp[0][0]=0;
		int last=n-1;
		for(int i=0; i<n-1; i++) {
			int[] jump=each[i];
			if(i+1<=last) dp[i+1][0]=Math.min(dp[i+1][0], dp[i][0]+jump[0]);
			if(i+2<=last) dp[i+2][0]=Math.min(dp[i+2][0], dp[i][0]+jump[1]);
			if(i+3<=last) dp[i+3][1]=Math.min(dp[i+3][1], dp[i][0]+k);
			if(dp[i][1]<(int)1e9) {
				if(i+1<=last) dp[i+1][1]=Math.min(dp[i+1][1], dp[i][1]+jump[0]);
				if(i+2<=last) dp[i+2][1]=Math.min(dp[i+2][1], dp[i][1]+jump[1]);
			}
		}

		return Math.min(dp[n-1][0], dp[n-1][1]);
	}

}
