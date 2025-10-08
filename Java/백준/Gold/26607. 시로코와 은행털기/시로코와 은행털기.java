import java.math.*;
import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			StringTokenizer st=new StringTokenizer(br.readLine());
			int n=Integer.parseInt(st.nextToken());
			int k=Integer.parseInt(st.nextToken());
			int x=Integer.parseInt(st.nextToken());

			int[][] score=new int[n][2];
			for(int i=0; i<n; i++) {
				st=new StringTokenizer(br.readLine());
				score[i][0]=Integer.parseInt(st.nextToken());
				score[i][1]=Integer.parseInt(st.nextToken());
			}

			bw.write(
				String.valueOf(
					new Main().resolve(
						n, k, x, score
					)
				)
			);
			bw.flush();
		}
	}

	int resolve(int n, int k, int x, int[][] score) {
		final int max=k*x;

		boolean[][] dp=new boolean[k+1][max+1];
		dp[0][0]=true;
		for(int i=1; i<=n; i++) {
			int a=score[i-1][0];
			int limit=Math.min(i, k);
			for(int j=limit; j>=1; j--) {
				for(int l=a; l<=Math.min(max, j*x); l++) {
					if(dp[j-1][l-a]) dp[j][l]=true;
				}
			}
		}
		int ret=0;
		for(int i=0; i<=max; i++) {
			if(!dp[k][i]) continue;
			int b=max-i;
			if(ret<i*b) ret=i*b;
		}
		return ret;
	}
}
