import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			StringTokenizer st=new StringTokenizer(br.readLine());
			int n=Integer.parseInt(st.nextToken());
			int m=Integer.parseInt(st.nextToken());

			bw.write(
				String.valueOf(
					new Main().resolve(
						n, m, Arrays.stream(br.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray()
					)
				)
			);
			bw.flush();
		}
	}

	int resolve(int n, int m, int[] scores) {
		Arrays.sort(scores);

		int lo=0;
		int hi=n-1;
		int ret=0;
		while(lo<hi) {
			if(scores[lo]+scores[hi]>=m) {
				ret++; lo++; hi--;
			} else lo++;
		}
		return ret;
	}

}
