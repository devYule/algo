import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			int round=Integer.parseInt(br.readLine());
			for(int i=0; i<round; i++) {

				if(i!=0) bw.write("\n");
				bw.write(
					String.valueOf(
						new Main().resolve(
							Integer.parseInt(br.readLine()),
							Arrays.stream(br.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray()
						)
					)
				);
			}
			bw.flush();
		}
	}

	int n, numb[], memo[][], sum[];
	int resolve(int n, int[] numb) {
		this.n=n;
		this.numb=numb;
		this.memo=new int[n][n];
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				if(i==j) memo[i][j]=0;
				else memo[i][j]=-1;
			}
		}
		this.sum=new int[n];
		sum[0]=numb[0];
		for(int i=1; i<n; i++) sum[i]=sum[i-1]+numb[i];

		int ret=getMin(0, n-1);
		return ret;
	}

	int sum(int l, int r) {
		return l==0 ? sum[r] : sum[r]-sum[l-1];
	}

	int getMin(int l, int r) {
		if(memo[l][r]!=-1) return memo[l][r];
		int ret=(int)1e9;
		for(int i=l; i<r; i++) {
			int lr=getMin(l, i);
			int rr=getMin(i+1, r);
			ret=Math.min(ret, lr+rr);
		}
		return memo[l][r]=ret+sum(l, r);
	}

}
