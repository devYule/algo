import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			int round=Integer.parseInt(br.readLine());
			for(int i=0; i<round; i++) {

				if(i!=0) bw.write("\n");
				bw.write(
					String.valueOf(
						new Main().resolve(
							Integer.parseInt(br.readLine()),
							Arrays.stream(br.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray()
						)
					)
				);
			}
			bw.flush();
		}
	}

	int resolve(int n, int[] numb) {
		int[][] dp=new int[n][n];
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				if(i==j) continue;
				dp[i][j]=(int)1e9;
			}
		}
		int[] sumArr=new int[n];
		sumArr[0]=numb[0];
		for(int i=1; i<n; i++) sumArr[i]=sumArr[i-1]+numb[i];

		for(int gap=1; gap<n; gap++) {
			for(int left=0; left+gap<n; left++) {
				int right=left+gap;
				int sum=sumArr[right]-(left==0 ? 0 : sumArr[left-1]);
				for(int cut=left; cut+1<=right; cut++) {
					dp[left][right]=Math.min(dp[left][right], dp[left][cut]+dp[cut+1][right]+sum);
				}
			}
		}
		return dp[0][n-1];
	}
}
