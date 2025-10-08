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
		int[] dp=new int[n];
		dp[0]=1;
		dp[1]=1;
		for(int i=2; i<n; i++) dp[i]=dp[i-1]+dp[i-2];

		List<Integer> a=new ArrayList<>();
		List<Integer> b=new ArrayList<>();
		while(n>2) {
			a.add(n);
			b.add(n-1);
			b.add(n-2);
			n-=3;
		}
		if(n==2) {
			a.add(1);
			b.add(2);
		}
		a.sort(Comparator.naturalOrder());
		b.sort(Comparator.naturalOrder());
		return new StringBuilder().append(a.size()).append("\n")
			.append(a.stream().map(String::valueOf).collect(java.util.stream.Collectors.joining(" "))).append("\n")
			.append(b.size()).append("\n")
			.append(b.stream().map(String::valueOf).collect(java.util.stream.Collectors.joining(" ")))
			.toString();
	}

}
