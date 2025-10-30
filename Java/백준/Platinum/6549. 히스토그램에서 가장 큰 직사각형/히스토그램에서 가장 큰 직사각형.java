import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			int cnt=0;
			while(true) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				int n=Integer.parseInt(st.nextToken());
				if(n==0) break;
				int[] height=new int[n];
				for(int i=0; i<n; i++) height[i]=Integer.parseInt(st.nextToken());

				if(cnt++!=0) bw.write("\n");
				bw.write(
					String.valueOf(
						new Main().resolve(
							n, height
						)
					)
				);
			}
			bw.flush();
		}
	}

	long resolve(int n, int[] height) {
		int[] th=new int[n+1];
		System.arraycopy(height, 0, th, 0, height.length);
		height=th;
		Stack<Integer> st=new Stack<>();
		long ret=0;
		for(int i=0; i<=n; i++) {
			int r=1;
			while(!st.isEmpty() && height[st.peek()]>height[i]) {
				int mid=st.pop();
				int l=st.isEmpty() ? -1 : st.peek();
				ret=Math.max(ret, height[mid]*(i-l-1L));
			}
			st.add(i);
		}
		return ret;
	}
}
