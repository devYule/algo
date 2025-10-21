import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			StringTokenizer st=new StringTokenizer(br.readLine());
			int n=Integer.parseInt(st.nextToken());
			int m=Integer.parseInt(st.nextToken());

			int[][] s=new int[n][m];
			for(int i=0; i<n; i++) {
				st=new StringTokenizer(br.readLine());
				for(int j=0; j<m; j++) {
					s[i][j]=Integer.parseInt(st.nextToken());
				}
			}

			bw.write(
				String.valueOf(
					new Main().resolve(
						n, m, s
					)
				)
			);
			bw.flush();
		}
	}

	int resolve(int n, int m, int[][] s) {
		int[][] sum=new int[n][m];
		sum[0][0]=s[0][0];
		for(int i=1; i<m; i++) sum[0][i]=sum[0][i-1]+s[0][i];
		for(int i=1; i<n; i++) sum[i][0]=sum[i-1][0]+s[i][0];

		for(int i=1; i<n; i++) {
			for(int j=1; j<m; j++) {
				sum[i][j]=(s[i][j]+sum[i-1][j]+sum[i][j-1])-sum[i-1][j-1];
			}
		}

		int[][] best=new int[n][m];
		best[0][0]=sum[0][0];
		for(int i=1; i<m; i++) best[0][i]=Math.max(sum[0][i], s[0][i]);
		for(int i=1; i<n; i++) best[i][0]=Math.max(sum[i][0], s[i][0]);

		for(int i=1; i<n; i++) {
			for(int j=1; j<m; j++) {
				best[i][j]=sum[i][j];
				if(i>0) best[i][j]=Math.max(best[i][j], best[i-1][j]);
				if(j>0) best[i][j]=Math.max(best[i][j], best[i][j-1]);
				if(i>0 && j>0) best[i][j]=Math.max(best[i][j], best[i-1][j-1]);
				for(int k=0; k<i; k++) {
					for(int l=0; l<j; l++) {
						if(l==0) best[i][j]=Math.max(best[i][j], sum[i][j]-sum[k][j]);
						if(k==0) best[i][j]=Math.max(best[i][j], sum[i][j]-sum[i][l]);
						best[i][j]=Math.max(best[i][j], (sum[i][j]-sum[k][j]-sum[i][l])+sum[k][l]);
					}
				}
			}
		}

		return best[n-1][m-1];
	}
}
