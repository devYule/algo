import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			String[] split=br.readLine().split("\\s");
			int n=Integer.parseInt(split[0]);
			int m=Integer.parseInt(split[1]);
			int q=Integer.parseInt(split[2]);
			int[][] queries=new int[q][];
			for(int i=0; i<q; i++) {
				queries[i]=Arrays.stream(br.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();
			}

			bw.write(
				String.valueOf(
					new Main().resolve(
						n, m, q, queries
					)
				)
			);
			bw.flush();
		}
	}

	String resolve(int n, int m, int q, int[][] Q) {
		long[][] map=new long[n][m];
		for(int[] qr: Q) {
			int type=qr[0];
			int nth=qr[1]-1;
			int value=qr[2];

			// row
			if(type==1) {
				map[nth][0]+=value;
				if(nth+1<n) map[nth+1][0]-=value;
			}
			else {
				map[0][nth]+=value;
				if(nth+1<m) map[0][nth+1]-=value;
			}
		}

		for(int i=0; i<n; i++) {
			for(int j=1; j<m; j++) {
				map[i][j]+=map[i][j-1];
			}
		}
		for(int i=1; i<n; i++) {
			for(int j=0; j<m; j++) {
				map[i][j]+=map[i-1][j];
			}
		}
		StringBuilder sb=new StringBuilder();
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				sb.append(map[i][j]);
				if(j<m-1) sb.append(" ");
			}
			if(i<n-1) sb.append("\n");
		}
		return sb.toString();
	}

}
