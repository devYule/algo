import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			int n=Integer.parseInt(br.readLine());
			int[] nw=Arrays.stream(br.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();
			int s=Integer.parseInt(br.readLine());
			int[] sw=Arrays.stream(br.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();

			bw.write(
				String.valueOf(
					new Main().resolve(
						n, nw, s, sw
					)
				)
			);
			bw.flush();
		}
	}

	String resolve(int n, int[] nw, int s, int[] sw) {
		int sum=Arrays.stream(nw).sum();
		boolean[][] dp=new boolean[n+1][sum+1];
		dp[0][0]=true;

		for(int i=1; i<=n; i++) {
			int w=nw[i-1];
			dp[i][0]=true;
			for(int j=0; j<=sum; j++) {
				if(dp[i-1][j]) {
					dp[i][j]=true;
					dp[i][Math.abs(j-w)]=true;
					if(w+j<=sum) dp[i][w+j]=true;
				}
			}
		}

		return Arrays.stream(sw).boxed().map(it->it<=sum && dp[n][it] ? "Y" : "N").collect(java.util.stream.Collectors.joining(" "));
	}

}
