import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			int n=Integer.parseInt(br.readLine());
			int[] build=new int[n];
			for(int i=0; i<n; i++) build[i]=Integer.parseInt(br.readLine());

			bw.write(
				String.valueOf(
					new Main().resolve(
						n, build
					)
				)
			);
			bw.flush();
		}
	}

	long resolve(int n, int[] build) {
		Stack<Integer> st=new Stack<>();
		long[] ret=new long[n];
		for(int i=n-1; i>=0; i--) {
			while(!st.isEmpty() && build[st.peek()]<build[i]) ret[i]+=ret[st.pop()]+1;
			st.add(i);
		}
		return Arrays.stream(ret).sum();
	}

}
