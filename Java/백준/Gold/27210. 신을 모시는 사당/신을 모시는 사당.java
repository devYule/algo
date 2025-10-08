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

	int resolve(int n, int[] each) {

		int[] each2=Arrays.copyOf(each, each.length);
		for(int i=0; i<n; i++) {
			if(each[i]==2) {
				each[i]=-1;
				each2[i]=1;
			} else each2[i]=-1;
		}

		int[] dp=new int[n];
		int[] dp2=new int[n];
		dp[0]=each[0];
		dp2[0]=each2[0];
		int ret=dp[0];
		int ret2=dp2[0];
		for(int i=1; i<n; i++) {
			if(dp[i-1]+each[i]<each[i]) dp[i]=each[i];
			else dp[i]=dp[i-1]+each[i];
			if(dp2[i-1]+each2[i]<each2[i]) dp2[i]=each2[i];
			else dp2[i]=dp2[i-1]+each2[i];
			ret=Math.max(ret, Math.max(dp[i], dp2[i]));
		}
		return ret;
	}
}
