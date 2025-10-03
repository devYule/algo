import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			StringTokenizer st=new StringTokenizer(br.readLine());
			int n=Integer.parseInt(st.nextToken());
			int m=Integer.parseInt(st.nextToken());
			int[][] ts=new int[n][3];
			for(int i=0; i<n; i++) {
				st=new StringTokenizer(br.readLine());
				ts[i][0]=Integer.parseInt(st.nextToken());
				ts[i][1]=Integer.parseInt(st.nextToken());
				ts[i][2]=Integer.parseInt(st.nextToken());
			}

			bw.write(
				String.valueOf(
					new Main().resolve(
						n, m, ts
					)
				)
			);
			bw.flush();
		}
	}

	int resolve(int n, int m, int[][] ts) {
		int[] dp=new int[m+1];
		for(int i=0; i<n; i++) {
			int[] t=ts[i];
			int w=t[0];
			int v=t[1];
			int cnt=t[2];
			int p=1;
			while(cnt>0) {
				int get=Math.min(cnt, p);
				for(int j=m; j>=w*get; j--) {
					dp[j]=Math.max(dp[j], dp[j-w*get]+v*get);
				}
				cnt-=get;
				p<<=1;
			}
		}
		return dp[m];
	}

}
