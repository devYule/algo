import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			int n=Integer.parseInt(br.readLine());
			int[] s=Arrays.stream(br.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();
			int q=Integer.parseInt(br.readLine());
			int[] l=Arrays.stream(br.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();


			bw.write(
				String.valueOf(
					new Main().resolve(
						n, s, q, l
					)
				)
			);
			bw.flush();
		}
	}

	String resolve(int n, int[] A, int q, int[] L) {
		int max=Arrays.stream(L).max().orElseThrow();
		int[] dp=new int[max+1];
		for(int a: A) if(a<=max) dp[a]=1;

		for(int i=2; i<=max; i++) {
			int sqrt=(int) Math.sqrt(i);
			for(int j=1; j<=sqrt; j++) {
				if(i%j!=0) continue;
				dp[i]+=dp[j];
				if(j!=1 && j!=i/j) dp[i]+=dp[i/j];
			}
		}
		List<Integer> ret=new ArrayList<>();
		for(int l: L) ret.add(dp[l]);
		return ret.stream().map(String::valueOf).collect(java.util.stream.Collectors.joining(" "));
	}

}
