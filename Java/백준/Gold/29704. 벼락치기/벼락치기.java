import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			StringTokenizer st=new StringTokenizer(br.readLine());
			int n=Integer.parseInt(st.nextToken());
			int m=Integer.parseInt(st.nextToken());
			int[][] each=new int[n][2];
			for(int i=0; i<n; i++) {
				st=new StringTokenizer(br.readLine());
				each[i][0]=Integer.parseInt(st.nextToken());
				each[i][1]=Integer.parseInt(st.nextToken());
			}

			bw.write(
				String.valueOf(
					new Main().resolve(
						n, m, each
					)
				)
			);
			bw.flush();
		}
	}

	int resolve(int n, int t, int[][] each) {
		int[] dp=new int[t+1];
		int sum=Arrays.stream(each).mapToInt(e->e[1]).sum();

		for(int i=0; i<n; i++) {
			int day=each[i][0];
			for(int j=t; j>=day; j--) {
				dp[j]=Math.max(dp[j], dp[j-day]+each[i][1]);
			}
		}
		
		return sum-dp[t];
	}
}
