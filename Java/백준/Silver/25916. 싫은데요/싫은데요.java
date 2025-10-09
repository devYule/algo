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

	int resolve(int n, int m, int[] hole) {
		int right=0;

		int ret=0;
		int sum=0;
		for(int left=0; left<n; left++) {
			while(right<n && sum+hole[right]<=m) sum+=hole[right++];
			ret=Math.max(ret, sum);
			sum-=hole[left];
			if(left==right) { right++; sum=0; }
		}
		return ret;
	}

}
