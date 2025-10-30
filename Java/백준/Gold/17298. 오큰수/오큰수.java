import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			int n=Integer.parseInt(br.readLine());
			int[] nums=new int[n];
			StringTokenizer st=new StringTokenizer(br.readLine());
			for(int i=0; i<n; i++) nums[i]=Integer.parseInt(st.nextToken());

			bw.write(
				String.valueOf(
					new Main().resolve(
						n, nums
					)
				)
			);
			bw.flush();
		}
	}

	String resolve(int n, int[] nums) {
		Stack<int[]> st=new Stack<>();
		int[] ret=new int[n];
		Arrays.fill(ret, -1);

		for(int i=0; i<n; i++) {
			int ri=i-1;
			while(!st.isEmpty() && st.peek()[1]<nums[i]) ret[st.pop()[0]]=nums[i];
			st.add(new int[] {i, nums[i]});
		}
		StringBuilder sb=new StringBuilder();
		for(int i=0; i<n; i++) {
			if(i!=0) sb.append(" ");
			sb.append(ret[i]);
		}

		return sb.toString();
	}

}
