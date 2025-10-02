import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			StringTokenizer st=new StringTokenizer(br.readLine());
			int k=Integer.parseInt(st.nextToken());
			int n=Integer.parseInt(st.nextToken());

			int[][] info=new int[n][2];
			for(int i=0; i<n; i++) {
				st=new StringTokenizer(br.readLine());
				info[i][0]=Integer.parseInt(st.nextToken());
				info[i][1]=Integer.parseInt(st.nextToken());
			}

			bw.write(
				String.valueOf(
					new Main().resolve(
						n, k, info
					)
				)
			);
			bw.flush();
		}
	}

	int n,m, info[][];
	long[][] memo;
	long resolve(int n, int k, int[][] info) {
		this.n=n; this.m=m; this.info=info;
		this.memo=new long[n][k+1];
		for(int i=0; i<n; i++) Arrays.fill(memo[i], -1);
		return find(0, k);
	}

	long find(int idx, int restTime) {
		if(idx==n || restTime==0) return 0l;
		if(memo[idx][restTime]!=-1) return memo[idx][restTime];

		long ret=find(idx+1, restTime);
		if(restTime>=info[idx][1]) {
			ret=Math.max(ret, find(idx+1, restTime-info[idx][1])+info[idx][0]);
		}
		return memo[idx][restTime]=ret;
	}

}
