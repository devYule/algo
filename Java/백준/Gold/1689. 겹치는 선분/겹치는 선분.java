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
		List<int[]> evt=new ArrayList<>();
		for(int[] c: p) {
			evt.add(new int[] {c[0], 1});
			evt.add(new int[] {c[1], -1});
		}

		evt.sort((a, b) -> a[0]==b[0] ? a[1]-b[1] : a[0]-b[0]);

		int ret=0;
		int cnt=0;
		for(int[] e: evt) {
			cnt+=e[1];
			ret=Math.max(ret, cnt);
		}

		return ret;
	}
}
