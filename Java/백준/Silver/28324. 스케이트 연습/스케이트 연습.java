import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			int n=Integer.parseInt(br.readLine());
			int[] limit=Arrays.stream(br.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();

			bw.write(
				String.valueOf(
					new Main().resolve(
						n, limit
					)
				)
			);
			bw.flush();
		}
	}

	long resolve(int n, int[] limit) {
		long ret=0;
		int prev=0;
		for(int i=n-1; i>=0; i--) {
			int can=Math.min(limit[i], prev+1);
			ret+=can;
			prev=can;
		}
		return ret;
	}

}
