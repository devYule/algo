import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			int n=Integer.parseInt(br.readLine());
			int[] numb=new int[n];
			StringTokenizer st=new StringTokenizer(br.readLine());
			for(int i=0; i<n; i++) numb[i]=Integer.parseInt(st.nextToken());

			int q=Integer.parseInt(br.readLine());
			int[][] Q=new int[q][2];
			for(int i=0; i<q; i++) {
				st=new StringTokenizer(br.readLine());
				Q[i][0]=Integer.parseInt(st.nextToken());
				Q[i][1]=Integer.parseInt(st.nextToken());
			}

			bw.write(
				String.valueOf(
					new Main().resolve(
						n, numb, q, Q
					)
				)
			);
			bw.flush();
		}
	}

	String resolve(int n, int[] numb, int q, int[][] Q) {
		boolean[][] dp=new boolean[n][n];

		for(int i=0; i<n; i++) dp[i][i]=true;

		for(int i=0; i+1<n; i++) {
			if(numb[i]==numb[i+1]) dp[i][i+1]=true;
		}

		for(int size=2; size<n; size++) {
			for(int left=0; left+size<n; left++) {
				int right=left+size;
				if(numb[left]==numb[right] && dp[left+1][right-1]) dp[left][right]=true;
			}
		}

		StringBuilder sb=new StringBuilder();
		for(int i=0; i<Q.length; i++) {
			if(i!=0) sb.append("\n");
			sb.append(dp[Q[i][0]-1][Q[i][1]-1] ? 1 : 0);
		}
		return sb.toString();
	}

}
