import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			int n=Integer.parseInt(br.readLine());

			int[][] time=new int[n][2];

			for(int i=0; i<n; i++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				time[i][0]=Integer.parseInt(st.nextToken());
				time[i][1]=Integer.parseInt(st.nextToken());
			}

			bw.write(
				String.valueOf(
					new Main().resolve(
						n, time
					)
				)
			);
			bw.flush();
		}
	}

	int resolve(int n, int time[][]) {
		List<int[]> event=new ArrayList<>();
		for(int t[]: time) {
			event.add(new int[] {t[0], 1});
			event.add(new int[] {t[1], -1});
		}

		event.sort((a, b)-> a[0]==b[0] ? a[1]-b[1] : a[0]-b[0]);

		int ret=0;
		int sum=0;
		for(int e[]: event) {
			sum+=e[1];
			ret=Math.max(ret, sum);
		}
		return ret;
	}

}
