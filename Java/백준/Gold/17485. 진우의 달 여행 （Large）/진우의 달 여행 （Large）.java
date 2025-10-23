import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			StringTokenizer st=new StringTokenizer(br.readLine());

			int n=Integer.parseInt(st.nextToken());
			int m=Integer.parseInt(st.nextToken());

			int[][] cost=new int[n][m];
			for(int i=0; i<n; i++) {
				st=new StringTokenizer(br.readLine());
				for(int j=0; j<m; j++) {
					cost[i][j]=Integer.parseInt(st.nextToken());
				}
			}

			bw.write(
				String.valueOf(
					new Main().resolve(
						n, m, cost
					)
				)
			);
			bw.flush();
		}
	}

	int resolve(int n, int m, int[][] cost) {
		int[][] dp=new int[m][3];

		for(int i=0; i<m; i++) {
			for(int j=0; j<3; j++) {
				dp[i][j]=cost[0][i];
			}
		}

		for(int i=1; i<n; i++) {
			int[][] newDp=new int[m][3];
			for(int j=0; j<m; j++) Arrays.fill(newDp[j], (int)1e9);

			for(int j=0; j<m; j++) {
				if(j!=0) newDp[j][0]=Math.min(dp[j-1][1], dp[j-1][2])+cost[i][j];
				newDp[j][1]=Math.min(dp[j][0], dp[j][2])+cost[i][j];
				if(j!=m-1) newDp[j][2]=Math.min(dp[j+1][0], dp[j+1][1])+cost[i][j];
			}
			dp=newDp;
		}
		int ret=(int)1e9;
		for(int i=0; i<m; i++) {
			for(int j=0; j<3; j++) {
				ret=Math.min(ret, dp[i][j]);
			}
		}
		return ret;
	}
}
