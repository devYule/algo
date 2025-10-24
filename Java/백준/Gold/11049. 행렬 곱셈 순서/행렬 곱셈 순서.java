import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			int n=Integer.parseInt(br.readLine());

			int[][] sq=new int[n][2];
			for(int i=0; i<n; i++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				sq[i][0]=Integer.parseInt(st.nextToken());
				sq[i][1]=Integer.parseInt(st.nextToken());
			}

			bw.write(
				String.valueOf(
					new Main().resolve(
						n, sq
					)
				)
			);
			bw.flush();
		}
	}

	int resolve(int n, int[][] sq) {
		int[][] dp=new int[n][n];

		for(int l=1; l<n; l++) {
			for(int i=0; i+l<n; i++) {
				int lo=i;
				int hi=i+l;
				dp[lo][hi]=(int)1e9;
				for(int j=lo; j<hi; j++) {
					dp[lo][hi]=Math.min(dp[lo][hi], dp[lo][j]+dp[j+1][hi]+(sq[lo][0]*sq[j][1]*sq[hi][1]));
				}
			}
		}
		return dp[0][n-1];
	}

}
