import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			StringTokenizer st=new StringTokenizer(br.readLine());

			int n=Integer.parseInt(st.nextToken());
			int s=Integer.parseInt(st.nextToken());
			int m=Integer.parseInt(st.nextToken());


			bw.write(
				String.valueOf(
					new Main().resolve(
						n, s, m, Arrays.stream(br.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray()
					)
				)
			);
			bw.flush();
		}
	}

	int resolve(int n, int start, int limit, int[] v) {
		boolean[][] dp=new boolean[n+1][limit+1];
		dp[0][start]=true;
		for(int i=1; i<=n; i++) {
			int cur=v[i-1];
			for(int vol=0; vol<=limit; vol++) {
				if(dp[i-1][vol]) {
					if(vol+cur<=limit) dp[i][vol+cur]=true;
					if(vol-cur>=0) dp[i][vol-cur]=true;
				}
			}
		}

		for(int i=limit; i>=0; i--) {
			if(dp[n][i]) return i;
		}
		return -1;
	}

}
