import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			StringTokenizer st=new StringTokenizer(br.readLine());
			int n=Integer.parseInt(st.nextToken());
			int l=Integer.parseInt(st.nextToken());
			int[] num=new int[n];
			st=new StringTokenizer(br.readLine());
			for(int i=0; i<n; i++) {
				num[i]=Integer.parseInt(st.nextToken());
			}

			bw.write(
				String.valueOf(
					new Main().resolve(
						n, l, num
					)
				)
			);
			bw.flush();
		}
	}

	String resolve(int n, int l, int[] num) {
		ArrayDeque<int[]> q=new ArrayDeque<>();
		StringBuilder sb=new StringBuilder();
		for(int i=0; i<n; i++) {
			if(i!=0) sb.append(" ");
			int t=num[i];
			if(!q.isEmpty() && q.peekFirst()[0]<i+1-l) q.removeFirst();
			while(!q.isEmpty() && q.peekLast()[1]>t) q.removeLast();
			q.add(new int[] {i, t});
			sb.append(q.peekFirst()[1]);
		}
		return sb.toString();
	}

}
