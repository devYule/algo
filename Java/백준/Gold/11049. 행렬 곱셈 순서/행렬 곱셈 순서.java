import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			int n=Integer.parseInt(br.readLine());
			int[][] met=new int[n][2];
			for(int i=0; i<n; i++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				met[i][0]=Integer.parseInt(st.nextToken());
				met[i][1]=Integer.parseInt(st.nextToken());
			}

			bw.write(
				String.valueOf(
					new Main().resolve(
						n, met
					)
				)
			);
			bw.flush();
		}
	}

	int resolve(int n, int[][] metrix) {
		int[][] dp=new int[n][n];
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				if(i==j) continue;
				dp[i][j]=(int)1e9;
			}
		}

		for(int gap=1; gap<n; gap++) {
			for(int left=0; left+gap<n; left++) {
				int right=left+gap;
				for(int cut=left; cut<right; cut++) {
					dp[left][right]=Math.min(
						dp[left][right],
						dp[left][cut]+dp[cut+1][right]+(
								metrix[left][0]*metrix[cut][1]*metrix[right][1]
						)
					);
				}
			}
		}
		return dp[0][n-1];
	}

}
