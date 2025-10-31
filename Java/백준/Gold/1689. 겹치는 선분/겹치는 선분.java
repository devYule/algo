import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			int n=Integer.parseInt(br.readLine());
			int[][] p=new int[n][2];
			for(int i=0; i<n; i++) {
				StringTokenizer st=new StringTokenizer(br.readLine());

				p[i][0]=Integer.parseInt(st.nextToken());
				p[i][1]=Integer.parseInt(st.nextToken());
			}

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

	int resolve(int n, int[][] p) {
		Arrays.sort(p, (a, b) -> a[0]==b[0] ? a[1]-b[1] : a[0]-b[0]);

		PriorityQueue<Integer> q=new PriorityQueue<>();
		int ret=1;
		for(int i=0; i<n; i++) {
			int[] c=p[i];
			while(!q.isEmpty() && q.peek()<=c[0]) q.poll();
			q.add(c[1]);
			ret=Math.max(ret, q.size());
		}
		return ret;
	}
}
