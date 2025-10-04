import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			int n=Integer.parseInt(br.readLine());
			int[] each=new int[n];
			for(int i=0; i<n; i++) each[i]=Integer.parseInt(br.readLine());


			bw.write(
				String.valueOf(
					new Main().resolve(
						n, each
					)
				)
			);
			bw.flush();
		}
	}

	int resolve(int n, int[] each) {
		int[] dp=new int[n];
		Arrays.fill(dp, 1);
		for(int i=0; i<n; i++) {
			for(int j=i+1; j<n; j++) {
				if(each[i]<each[j]) {
					dp[j]=Math.max(dp[j], dp[i]+1);
				}
			}
		}
		return n-Arrays.stream(dp).max().orElseThrow();
	}

}
