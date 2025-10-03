import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			StringTokenizer st=new StringTokenizer(br.readLine());
			int n=Integer.parseInt(st.nextToken());
			int m=Integer.parseInt(st.nextToken());
			int[][] things=new int[n][2];
			for(int i=0; i<n; i++) {
				st=new StringTokenizer(br.readLine());
				things[i][0]=Integer.parseInt(st.nextToken());
				things[i][1]=Integer.parseInt(st.nextToken());
			}

			bw.write(
				String.valueOf(
					new Main().resolve(
						n, m, things
					)
				)
			);
			bw.flush();
		}
	}

	int resolve(int n, int m, int[][] ts) {
		int[] dp=new int[m+1];
		for(int i=0; i<n; i++) {
			for(int j=m; j>=ts[i][0]; j--) {
				dp[j]=Math.max(dp[j], dp[j-ts[i][0]]+ts[i][1]);
			}
		}
		return dp[m];
	}

}
