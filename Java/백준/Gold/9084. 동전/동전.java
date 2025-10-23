import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			int T=Integer.parseInt(br.readLine());

			for(int r=0; r<T; r++) {
				if(r!=0) bw.write("\n");

				int n=Integer.parseInt(br.readLine());

				int[] coin=new int[n];
				StringTokenizer st=new StringTokenizer(br.readLine());
				for(int i=0; i<n; i++) {
					coin[i]=Integer.parseInt(st.nextToken());
				}

				int target=Integer.parseInt(br.readLine());


				bw.write(
					String.valueOf(
						new Main().resolve(
							n, coin, target
						)
					)
				);
			}
			bw.flush();
		}
	}

	int resolve(int n, int[] coin, int target) {
		int[] dp=new int[target+1];
		dp[0]=1;
		for(int c: coin) {
			for(int i=c; i<=target; i++) {
				dp[i]+=dp[i-c];
			}
		}
		return dp[target];
	}

}
