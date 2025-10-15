import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			bw.write(
				String.valueOf(
					new Main().resolve(
						Integer.parseInt(br.readLine()),
						Arrays.stream(br.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray()
					)
				)
			);
			bw.flush();
		}
	}

	int resolve(int n, int[] arr) {
		int[] dp=new int[n+1];
		int ret=0;
		for(int i=1; i<=n; i++) {
			int cur=arr[i-1];
			dp[i]=cur;
			for(int j=0; j<i-1; j++) {
				if(arr[j]<cur) dp[i]=Math.max(dp[i], dp[j+1]+cur);
			}
			ret=Math.max(ret, dp[i]);
		}
		return ret;
	}

}
