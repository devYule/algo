import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			int n=Integer.parseInt(br.readLine());
			int[][] cost=new int[n][3];
			for(int i=0; i<n; i++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				cost[i][0]=Integer.parseInt(st.nextToken());
				cost[i][1]=Integer.parseInt(st.nextToken());
				cost[i][2]=Integer.parseInt(st.nextToken());
			}

			bw.write(
				String.valueOf(
					new Main().resolve(
						n, cost
					)
				)
			);
			bw.flush();
		}
	}

	int resolve(int n, int[][] color) {
		int[][] dp=new int[3][3];
		for(int i=0; i<3; i++) Arrays.fill(dp[i], (int)1e9);
		for(int i=0; i<3; i++) {
			for(int j=0; j<3; j++) {
				if(i==j) dp[i][j]=(int)1e9;
				else dp[i][j]=Math.min(dp[i][j], color[0][i]+color[1][j]);
			}
		}

		for(int i=2; i<n-1; i++) {
			int[][] newDp=new int[3][3];
			for(int j=0; j<3; j++) Arrays.fill(newDp[j], (int)1e9);
			for(int f=0; f<3; f++) {
				for(int j=0; j<3; j++) {
					for(int k=0; k<3; k++) {
						if(j==k) continue;
						newDp[f][j]=Math.min(newDp[f][j], dp[f][k]+color[i][j]);
					}
				}
			}
			dp=newDp;
		}

		int ret=(int)1e9;
		for(int i=0; i<3; i++) {
			for(int j=0; j<3; j++) {
				for(int k=0; k<3; k++) {
					if(i==k || j==k) continue;
					ret=Math.min(ret, dp[i][j]+color[n-1][k]);
				}
			}
		}
		return ret;
	}

}
