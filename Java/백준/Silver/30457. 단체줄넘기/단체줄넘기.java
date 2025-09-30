import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			int n=Integer.parseInt(br.readLine());
			int[] p=Arrays.stream(br.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();

			bw.write(
				String.valueOf(
					new Main().resolve(
						n, p
					)
				)
			);
			bw.flush();
		}
	}

	int resolve(int n, int[] p) {
		int[] cnt=new int[1+Arrays.stream(p).max().orElseThrow()];
		int ret=0;
		for(int i=0; i<n; i++) {
			if(cnt[p[i]]==2) continue;
			ret++;
			cnt[p[i]]++;
		}
		return ret;
	}

}
