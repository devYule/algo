import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			int round=Integer.parseInt(br.readLine());

			for(int i=0; i<round; i++) {
				if(i!=0) bw.write("\n");
				bw.write(
					String.valueOf(
						new Main().resolve(
							Integer.parseInt(br.readLine()),
							Arrays.stream(br.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray(),
							Integer.parseInt(br.readLine())
						)
					)
				);
			}
			bw.flush();
		}
	}

	int resolve(int n, int[] coin, int target) {
		int[] dp=new int[target+1];
		dp[0]=1;
		for(int c: coin) {
			for(int i=c; i<=target; i++) {
				dp[i]+=dp[i-c];
			}
		}
		return dp[target];
	}

}
