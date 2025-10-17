import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			StringTokenizer st=new StringTokenizer(br.readLine());
			int T=Integer.parseInt(st.nextToken());
			int W=Integer.parseInt(st.nextToken());
			int[] info=new int[T];
			for(int i=0; i<T; i++) info[i]=Integer.parseInt(br.readLine());

			bw.write(
				String.valueOf(
					new Main().resolve(
						T, W, info
					)
				)
			);
			bw.flush();
		}
	}

	int resolve(int T, int W, int[] info) {
		int[] dp=new int[W+1];
		for(int i=0; i<T; i++) {
			int t=info[i];
			for(int j=Math.min(i+1, W); j>=1; j--) {
				int curNotMv=t==1+j%2 ? 1 : 0;
				int prevNotMv=curNotMv==0 ? 1 : 0;
				dp[j]=Math.max(dp[j]+curNotMv, Math.max(dp[j-1]+curNotMv, dp[j-1]+prevNotMv));
			}
			if(t==1) dp[0]++;
		}
		return dp[W];
	}

}
