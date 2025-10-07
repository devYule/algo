import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			bw.write(
				String.valueOf(
					new Main().resolve(
						Long.parseLong(br.readLine())
					)
				)
			);
			bw.flush();
		}
	}
	int resolve(long n) {
		int[] dp=new int[10_000_001];

		dp[2]=1;
		dp[3]=1;
		long limit=Math.min(dp.length-1, n);
		for(int i=4; i<=limit; i++) {
			dp[i]=dp[i-1]+1;
			if(i%3==0) dp[i]=Math.min(dp[i], dp[i/3]+1);
			if(i%2==0) dp[i]=Math.min(dp[i], dp[i/2]+1);
		}

		if(n<dp.length) return dp[(int)n];

		Map<Long, Integer> vis=new HashMap<>();
		PriorityQueue<long[]> q=new PriorityQueue<>((a, b) -> {
			if(a[0]==b[0]) return Long.compare(a[1], b[1]);
			return Long.compare(a[0], b[0]);
		});
		q.add(new long[] {0, n});
		int best=(int)1e9;
		while(!q.isEmpty()) {
			long[] curs=q.poll();
			if(curs[1]<dp.length) {
				best=Math.min(best, (int)curs[0]+dp[(int)curs[1]]);
				continue;
			}

			long candi=curs[0]+1;

			if(curs[1]%3==0) {
				if(vis.get(curs[1]/3)==null || vis.get(curs[1]/3)>candi) {
					vis.put(curs[1]/3, (int)(curs[0]+1));
					q.add(new long[] {candi, curs[1]/3});
				}
			}
			if(curs[1]%2==0) {
				if(vis.get(curs[1]/2)==null || vis.get(curs[1]/2)>candi) {
					vis.put(curs[1]/2, (int)(curs[0]+1));
					q.add(new long[] {candi, curs[1]/2});
				}
			}
			if(curs[1]%3!=0) {
				long div3=curs[1]%3;
				if(vis.get(curs[1]-div3)==null || vis.get(curs[1]-div3)>curs[0]+div3) {
					vis.put(curs[1]-div3, (int)(curs[0]+div3));
					q.add(new long[] {curs[0]+div3, curs[1]-div3});
				}
			}
			if(curs[1]%2!=0) {
				long div2=curs[1]%2;
				if(vis.get(curs[1]-div2)==null || vis.get(curs[1]-div2)>curs[0]+div2) {
					vis.put(curs[1]-div2, (int)(curs[0]+div2));
					q.add(new long[] {curs[0]+div2, curs[1]-div2});
				}
			}
		}
		return best;
	}

}
