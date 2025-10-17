import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			bw.write(
				String.valueOf(
					new Main().resolve(
						Integer.parseInt(br.readLine())
					)
				)
			);
			bw.flush();
		}
	}

	String resolve(int n) {
		if(n==1) return 0 + "\n" + 1;
		if(n<=3) return 1 + "\n" + n + " " + 1;
		int[] dp=new int[n+1];
		dp[1]=0;
		dp[2]=1;
		dp[3]=1;

		for(int i=4; i<=n; i++) {
			dp[i]=dp[i-1]+1;
			if(i%3==0) dp[i]=Math.min(dp[i], dp[i/3]+1);
			if(i%2==0) dp[i]=Math.min(dp[i], dp[i/2]+1);
		}

		int next=n;
		List<Integer> ret=new ArrayList<>();
		while(next>0) {
			ret.add(next);
			if(next==1) break;
			int m1=dp[next-1];
			int d2=n;
			int d3=n;
			if(next%2==0) d2=dp[next/2];
			if(next%3==0) d3=dp[next/3];

			int min=m1;
			int minNext=next-1;
			if(d2<=min) {
				min=d2;
				minNext=next/2;
			}
			if(d3<=min) {
				min=d3;
				minNext=next/3;
			}
			next=minNext;
		}

		return dp[n]+"\n"+ret.stream().map(String::valueOf).collect(java.util.stream.Collectors.joining(" "));
	}

}
