import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			bw.write(
				String.valueOf(
					new Main().resolve(
						br.readLine()
					)
				)
			);
			bw.flush();
		}
	}

	int resolve(String pw) {
		if(pw.startsWith("0")) return 0;
		final int MOD=1000000;
		int[] dp=new int[pw.length()+1];
		dp[0]=dp[1]=1;

		for(int i=2; i<=pw.length(); i++) {
			if(pw.charAt(i-1)-'0'>0) dp[i]=dp[i-1]%MOD;
			int with=(pw.charAt(i-2)-'0')*10+(pw.charAt(i-1)-'0');
			if(with==0) return 0;
			if(with>=10 && with<=26) {
				dp[i]=(dp[i]+dp[i-2]%MOD)%MOD;
			}
		}
		return dp[pw.length()];
	}

}
