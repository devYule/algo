import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			int n=Integer.parseInt(br.readLine());
			int[] stone=new int[n];
			StringTokenizer st=new StringTokenizer(br.readLine());
			for(int i=0; i<n; i++) stone[i]=Integer.parseInt(st.nextToken());

			int q=Integer.parseInt(br.readLine());
			int[] Q=new int[q];
			st=new StringTokenizer(br.readLine());
			for(int i=0; i<q; i++) Q[i]=Integer.parseInt(st.nextToken());

			bw.write(
				String.valueOf(
					new Main().resolve(
						n, stone, q, Q
					)
				)
			);
			bw.flush();
		}
	}

	String resolve(int n, int[] stone, int q, int[] Q) {
		int sum=Arrays.stream(stone).sum();
		boolean[] dp=new boolean[sum+1];
		dp[0]=true;

		for(int s: stone) {
			boolean[] newDp=new boolean[sum+1];
			for(int i=0; i<=sum; i++) {
				if(dp[i]) {
					if(i+s<=sum) newDp[i+s]=true;
					if(Math.abs(i-s)>=0) newDp[Math.abs(i-s)]=true;
					newDp[i]=true;
				}
			}
			dp=newDp;
		}

		StringBuilder sb=new StringBuilder();
		for(int i=0; i<q; i++) {
			if(i!=0) sb.append(" ");
			sb.append(Q[i]<=sum && dp[Q[i]] ? "Y" : "N");
		}
		return sb.toString();
	}
}
