import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			StringTokenizer st=new StringTokenizer(br.readLine());

			int n=Integer.parseInt(st.nextToken());
			long m=Long.parseLong(st.nextToken());

			int[][] mat=new int[n][n];
			for(int i=0; i<n; i++) {
				st=new StringTokenizer(br.readLine());
				for(int j=0; j<n; j++) {
					mat[i][j]=Integer.parseInt(st.nextToken());
				}
			}

			bw.write(
				String.valueOf(
					new Main().resolve(
						n, m, mat
					)
				)
			);
			bw.flush();
		}
	}

	final int MOD=1000;
	int n;

	String resolve(int n, long m, int[][] mat) {
		this.n=n;
		int[][] ret=pow(m, mat);
		

		StringBuilder sb=new StringBuilder();
		for(int i=0; i<n; i++) {
			if(i!=0) sb.append("\n");
			for(int j=0; j<n; j++) {
				if(j!=0) sb.append(" ");
				sb.append(ret[i][j]);
			}
		}
		return sb.toString();
	}

	int[][] pow(long p, int[][] mat) {
		if(p==1) {
			int[][] ret=new int[n][n];
			for(int i=0; i<n; i++) {
				for(int j=0; j<n; j++) {
					ret[i][j]=mat[i][j]%MOD;
				}
			}
			return ret;
		}

		int[][] half=pow(p/2L, mat);
		int[][] ret=mul(half, half);
		if(p%2==1) {
			ret=mul(ret, mat);
		}
		return ret;
	}

	int[][] mul(int[][] A, int[][] B) {
		int[][] ret=new int[n][n];
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				for(int k=0; k<n; k++) {
					ret[i][j]=(ret[i][j]+((A[i][k]*B[k][j])%MOD))%MOD;
				}
			}
		}
		return ret;
	}

}
