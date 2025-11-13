import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			int T=Integer.parseInt(br.readLine());

			for(int r=0; r<T; r++) {
				if(r!=0) bw.write("\n");

				int n=Integer.parseInt(br.readLine());
				int[] card=new int[n];
				StringTokenizer st=new StringTokenizer(br.readLine());
				for(int i=0; i<n; i++) {
					card[i]=Integer.parseInt(st.nextToken());
				}

				bw.write(
					String.valueOf(
						new Main().resolve(
							n, card
						)
					)
				);
			}
			
			bw.flush();
		}
	}

	int n, card[], memo[][][];

	int resolve(int n, int[] card) {
		this.n=n; this.card=card;
		this.memo=new int[n][n][2];
		for(int i=0; i<n; i++) for(int j=0; j<n; j++) Arrays.fill(memo[i][j], -1);
		return find(0, n-1, 0);
	}

	int find(int l, int r, int turn) {
		if(l==r) return turn==0 ? card[l] : 0;
		if(memo[l][r][turn]!=-1) return memo[l][r][turn];

		if(turn==0) {
			return memo[l][r][0]=Math.max(
				find(l+1, r, (turn+1)%2)+card[l],
				find(l, r-1, (turn+1)%2)+card[r]
			);
		} else {
			return memo[l][r][1]=Math.min(
				find(l+1, r, (turn+1)%2),
				find(l, r-1, (turn+1)%2)
			);
		}

	}

}
