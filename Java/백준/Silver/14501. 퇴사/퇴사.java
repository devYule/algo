import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			int n=Integer.parseInt(br.readLine());

			int[][] job=new int[n][2];
			for(int i=0; i<n; i++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				job[i][0]=Integer.parseInt(st.nextToken());
				job[i][1]=Integer.parseInt(st.nextToken());
			}

			bw.write(
				String.valueOf(
					new Main().resolve(
						n, job
					)
				)
			);
			bw.flush();
		}
	}

	int n, jobs[][];
	int[] memo;
	int resolve(int n, int[][] jobs) {
		this.n=n;
		this.jobs=jobs;
		this.memo=new int[n];
		Arrays.fill(memo, -1);
		return find(0);
	}

	int find(int day) {
		if(day==n) return 0;
		if(memo[day]!=-1) return memo[day];

		int[] job=jobs[day];

		int ret=find(day+1);
		if(day+job[0]<=n) ret=Math.max(ret, find(day+job[0])+job[1]);
		return memo[day]=ret;
	}

}
