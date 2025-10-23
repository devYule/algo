import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			int n=Integer.parseInt(br.readLine());

			int[][] map=new int[n][n];
			for(int i=0; i<n; i++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				for(int j=0; j<n; j++) {
					map[i][j]=Integer.parseInt(st.nextToken());
				}
			}

			bw.write(
				String.valueOf(
					new Main().resolve(
						n, map
					)
				)
			);
			bw.flush();
		}
	}

	long resolve(int n, int[][] map) {
		long[][] dp=new long[n][n];
		dp[0][0]=1;
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				int jump=map[i][j];
				if(jump==0) continue;
				long add=dp[i][j];
				if(i+jump<n) dp[i+jump][j]+=add;
				if(j+jump<n) dp[i][j+jump]+=add;
			}
		}
		return dp[n-1][n-1];
	}

}
