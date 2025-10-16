import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			StringTokenizer st=new StringTokenizer(br.readLine());
			int n=Integer.parseInt(st.nextToken());
			int m=Integer.parseInt(st.nextToken());
			int[][] map=new int[n][m];
			for(int i=0; i<n; i++) {
				st=new StringTokenizer(br.readLine());
				for(int j=0; j<m; j++) {
					map[i][j]=Integer.parseInt(st.nextToken());
				}
			}

			bw.write(
				String.valueOf(
					new Main().resolve(
						n, m, map
					)
				)
			);
			bw.flush();
		}
	}

	int resolve(int n, int m, int[][] map) {
		int[][] dp=new int[m][3];
		for(int i=0; i<m; i++) Arrays.fill(dp[i], map[0][i]);

		for(int i=1; i<n; i++) {
			int[][] newDp=new int[m][3];
			for(int j=0; j<m; j++) {
				int cur=map[i][j];
				for(int k=-1; k<=1; k++) {
					newDp[j][k+1]=(int)1e9;
					if(j+k<0 || j+k>=m) continue;
					for(int l=-1; l<=1; l++) {
						if(k==l || j+k+l<0 || j+k+l>=m) continue;
						newDp[j][k+1]=Math.min(newDp[j][k+1], dp[j+k][l+1]+cur);
					}
				}
			}
			dp=newDp;
		}
		int ret=(int)1e9;
		for(int i=0; i<m; i++) {
			for(int j=0; j<3; j++) {
				ret=Math.min(ret, dp[i][j]);
			}
		}
		return ret;
	}
}
