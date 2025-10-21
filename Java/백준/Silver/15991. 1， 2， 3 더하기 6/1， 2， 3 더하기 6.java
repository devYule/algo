import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			int T=Integer.parseInt(br.readLine());
			int[] cases=new int[T];
			for(int i=0; i<T; i++) cases[i]=Integer.parseInt(br.readLine());

			bw.write(
				String.valueOf(
					new Main().resolve(
						T, cases
					)
				)
			);
			bw.flush();
		}
	}

	

	String resolve(int T, int[] cases) {
		final int MOD=1_000_000_009;
		int limit=Arrays.stream(cases).max().orElse(0);
		int[] dp=new int[Math.max(4, limit+1)];
		dp[0]=1;
		dp[1]=1;
		dp[2]=2;
		dp[3]=2;

		for(int i=4; i<=limit; i++) {
			for(int j=1; j<=3; j++) {
				if(i-j*2>=0) {
					dp[i]=(dp[i]+dp[i-j*2]%MOD)%MOD;
				}
			}
		}
		StringBuilder sb=new StringBuilder();
		for(int i=0; i<T; i++) {
			if(i!=0) sb.append("\n");
			sb.append(dp[cases[i]]);
		}
		return sb.toString();
	}
}
