import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			String[] split=br.readLine().split("\\s");
			int n=Integer.parseInt(split[0]);
			int m=Integer.parseInt(split[1]);
			int[] w=Arrays.stream(br.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();

			bw.write(
				String.valueOf(
					new Main().resolve(
						n, m, w
					)
				)
			);
			bw.flush();
		}
	}

	int resolve(int n, int m, int[] w) {
		final int MAX=((int)1e9)+1;
		Arrays.sort(w);
		int ret=0;
		for(int i=0; i<n; i++) {
			if(w[i]>m) continue;
			for(int j=n-1; j>i; j--) {
				if(w[j]>m) continue;
				if(w[i]+w[j]<=m) {
					w[i]=MAX;
					w[j]=MAX;
					ret++;
					break;
				}
			}
		}
		return ret;
	}

}
