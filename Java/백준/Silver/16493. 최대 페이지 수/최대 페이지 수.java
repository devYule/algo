import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			StringTokenizer st=new StringTokenizer(br.readLine());
			int n=Integer.parseInt(st.nextToken());
			int m=Integer.parseInt(st.nextToken());

			int[][] info=new int[m][2];
			for(int i=0; i<m; i++) {
				st=new StringTokenizer(br.readLine());
				info[i][0]=Integer.parseInt(st.nextToken());
				info[i][1]=Integer.parseInt(st.nextToken());
			}

			bw.write(
				String.valueOf(
					new Main().resolve(
						n, m, info
					)
				)
			);
			bw.flush();
		}
	}

	int m, info[][], memo[][];
	int resolve(int n, int m, int[][] info) {
		this.m=m; this.info=info;
		this.memo=new int[m][n+1];
		for(int i=0; i<m; i++) Arrays.fill(memo[i], -1);
		return find(0, n);
	}

	int find(int idx, int restDay) {
		if(idx==m || restDay==0) return 0;
		if(memo[idx][restDay]!=-1) return memo[idx][restDay];

		int ret=find(idx+1, restDay);
		if(restDay>=info[idx][0]) {
			ret=Math.max(ret, find(idx+1, restDay-info[idx][0])+info[idx][1]);
		}
		return memo[idx][restDay]=ret;

	}
}
