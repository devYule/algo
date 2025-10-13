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

	String resolve(int n, int[] work) {
		boolean[][] dp=new boolean[n+1][7];
		dp[0][0]=true;

		for(int i=0; i<n; i++) {
			int mod=work[i]%7;
			for(int cnt=n-1; cnt>=0; cnt--) {
				for(int j=0; j<7; j++) {
					if(dp[cnt][j]) {
						if((mod+j)%7==4) return "YES";
						dp[cnt+1][(mod+j)%7]=true;
					}
				}
			}
		}

		return "NO";
	}

}
