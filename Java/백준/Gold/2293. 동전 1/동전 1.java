import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			StringTokenizer st=new StringTokenizer(br.readLine());
			int n=Integer.parseInt(st.nextToken());
			int k=Integer.parseInt(st.nextToken());

			int[] coin=new int[n];
			for(int i=0; i<n; i++) coin[i]=Integer.parseInt(br.readLine());

			bw.write(
				String.valueOf(
					new Main().resolve(
						n, k, coin
					)
				)
			);
			bw.flush();
		}
	}

	int resolve(int n, int k, int[] coin) {
		int[] dp=new int[k+1];
		dp[0]=1;
		for(int c: coin) {
			for(int i=1; i<=k; i++) {
				if(i-c>=0) dp[i]+=dp[i-c];
			}
		}

		return dp[k];
	}

}
