import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			int n=Integer.parseInt(br.readLine());
			int[][] E=new int[n][n];
			for(int i=0; i<n; i++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				for(int j=0; j<n; j++) {
					E[i][j]=Integer.parseInt(st.nextToken());
				}
			}

			bw.write(
				String.valueOf(
					new Main().resolve(
						n, E
					)
				)
			);
			bw.flush();
		}
	}

	int resolve(int n, int[][] E) {
		boolean[][] ex=new boolean[n][n];
		for(int k=0; k<n; k++) {
			for(int i=0; i<n; i++) {
				if(i==k) continue;
				for(int j=0; j<n; j++) {
					if(j==k) continue;
					if(E[i][j]==E[i][k]+E[k][j]) {
						ex[i][j]=true;
					} else if(E[i][j]>E[i][k]+E[k][j]) return -1;
				}
			}
		}
		int ret=0;
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				if(!ex[i][j]) ret+=E[i][j];
			}
		}
		return ret/2;
	}
}
