import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			StringTokenizer st=new StringTokenizer(br.readLine());
			int n=Integer.parseInt(st.nextToken());
			int k=Integer.parseInt(st.nextToken());

			int[][] walk=new int[n][2];
			int[][] bcl=new int[n][2];

			for(int i=0; i<n; i++) {
				st=new StringTokenizer(br.readLine());
				walk[i][0]=Integer.parseInt(st.nextToken());
				walk[i][1]=Integer.parseInt(st.nextToken());
				bcl[i][0]=Integer.parseInt(st.nextToken());
				bcl[i][1]=Integer.parseInt(st.nextToken());
			}

			bw.write(
				String.valueOf(
					new Main().resolve(
						n, k, walk, bcl
					)
				)
			);
			bw.flush();
		}
	}

	int resolve(int n, int k, int[][] walk, int[][] bcl) {
		int[] dp=new int[k+1];
		Arrays.fill(dp, -(int)1e9);
		dp[0]=0;
		for(int i=0; i<n; i++) {
			int[] next=new int[k+1];
			Arrays.fill(next, -(int)1e9);
			for(int j=0; j<=k; j++) {
				if(dp[j]==-(int)1e9) continue;
				if(j+walk[i][0]<=k) {
					next[j+walk[i][0]]=Math.max(next[j+walk[i][0]], dp[j]+walk[i][1]);
				}
				if(j+bcl[i][0]<=k) {
					next[j+bcl[i][0]]=Math.max(next[j+bcl[i][0]], dp[j]+bcl[i][1]);
				}
			}
			dp=next;
		}

		return Arrays.stream(dp).max().orElseThrow();
	}
	
}
