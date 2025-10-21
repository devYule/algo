import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			StringTokenizer st=new StringTokenizer(br.readLine());
			int n=Integer.parseInt(st.nextToken());
			int m=Integer.parseInt(st.nextToken());

			int[][] s=new int[n][m];
			for(int i=0; i<n; i++) {
				st=new StringTokenizer(br.readLine());
				for(int j=0; j<m; j++) {
					s[i][j]=Integer.parseInt(st.nextToken());
				}
			}

			bw.write(
				String.valueOf(
					new Main().resolve(
						n, m, s
					)
				)
			);
			bw.flush();
		}
	}

	int resolve(int n, int m, int[][] s) {
		int ret=Integer.MIN_VALUE;
		for(int i=0; i<n; i++) {
			int[] sum=new int[m];
			for(int j=i; j<n; j++) {
				for(int k=0; k<m; k++) {
					sum[k]+=s[j][k];
				}
				int[] dp=new int[m];
				dp[0]=sum[0];
				ret=Math.max(ret, dp[0]);
				for(int k=1; k<m; k++) {
					dp[k]=Math.max(dp[k-1]+sum[k], sum[k]);
					ret=Math.max(ret, dp[k]);
				}
			}
		}
		return ret;
	}
}
