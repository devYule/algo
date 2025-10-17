import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			StringTokenizer st=new StringTokenizer(br.readLine());
			int n=Integer.parseInt(st.nextToken());
			int k=Integer.parseInt(st.nextToken());
			int[] step=new int[n];
			st=new StringTokenizer(br.readLine());
			for(int i=0; i<n; i++) step[i]=Integer.parseInt(st.nextToken());

			bw.write(
				String.valueOf(
					new Main().resolve(
						n, k, step
					)
				)
			);
			bw.flush();
		}
	}

	final String POS="YES", NEG="NO";
	String resolve(int n, int k, int[] step) {
		boolean[] dp=new boolean[n];
		dp[0]=true;
		for(int i=1; i<n; i++) {
			for(int j=0; j<i; j++) {
				if(dp[j] && ((i-j)*(1+Math.abs(step[i]-step[j])))<=k) dp[i]=true;
			}
		}
		return dp[n-1] ? POS : NEG;
	}

}
