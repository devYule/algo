import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			List<Integer> p=new ArrayList<>();
			int next=-1;
			while((next=Integer.parseInt(br.readLine()))!=0) p.add(next);

			bw.write(
				String.valueOf(
					new Main().resolve(
						p
					)
				)
			);
			bw.flush();
		}
	}

	String resolve(List<Integer> each) {
		int max=each.stream().max(Comparator.naturalOrder()).orElse(0);
		long[][] dp=new long[max+1][max+1];
		for(int i=0; i<=max; i++) dp[0][i]=1l;

		for(int i=1; i<=max; i++) {
			for(int j=0; j<max; j++) {
				dp[i][j]=dp[i-1][j+1];
				if(j>0) dp[i][j]+=dp[i][j-1];
			}
		}
		List<Long> ret=new ArrayList<>();
		for(int e: each) {
			ret.add(dp[e][0]);
		}

		return ret.stream().map(String::valueOf).collect(java.util.stream.Collectors.joining("\n"));
	}

}
