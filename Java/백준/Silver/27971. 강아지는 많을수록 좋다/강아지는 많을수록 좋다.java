import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			StringTokenizer st=new StringTokenizer(br.readLine());

			int n=Integer.parseInt(st.nextToken());
			int m=Integer.parseInt(st.nextToken());
			int A=Integer.parseInt(st.nextToken());
			int B=Integer.parseInt(st.nextToken());
			int[][] closed=new int[m][2];
			for(int i=0; i<m; i++) {
				st=new StringTokenizer(br.readLine());
				closed[i][0]=Integer.parseInt(st.nextToken());
				closed[i][1]=Integer.parseInt(st.nextToken());
			}

			bw.write(
				String.valueOf(
					new Main().resolve(
						n, m, A, B, closed
					)
				)
			);
			bw.flush();
		}
	}

	int resolve(int n, int m, int A, int B, int[][] closed) {
		int[] dp=new int[n+1];
		Arrays.fill(dp, (int)1e9);
		dp[0]=0;

		for(int i=1; i<=n; i++) {
			boolean pos=true;
			for(int[] c: closed) if(i>=c[0] && i<=c[1]) { pos=false; break; }
			if(!pos) continue;
			if(i>=A) dp[i]=dp[i-A]+1;
			if(i>=B) dp[i]=Math.min(dp[i], dp[i-B]+1);
		}
		return dp[n]>=(int)1e9 ? -1 : dp[n];
	}

}
