import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			int n=Integer.parseInt(br.readLine());
			String[] mmb=new String[n];
			for(int i=0; i<n; i++) {
				mmb[i]=br.readLine();
			}
			String[] wmb=new String[n];
			for(int i=0; i<n; i++) {
				wmb[i]=br.readLine();
			}

			bw.write(
				String.valueOf(
					new Main().resolve(
						n, mmb, wmb
					)
				)
			);
			bw.flush();
		}
	}

	int n;
	String[] mb, wb;
	int[][] memo;
	int resolve(int n, String[] mb, String[] wb) {
		this.n=n;
		this.mb=mb; this.wb=wb;
		this.memo=new int[1<<n][1<<n];
		for(int i=0; i<memo.length; i++) Arrays.fill(memo[i], -1);
		return find(0, 0);
	}

	int find(int mstate, int wstate) {
		if(Integer.bitCount(mstate)==n) return 0;
		if(memo[mstate][wstate]!=-1) return memo[mstate][wstate];
		int ret=0;
		for(int i=0; i<n; i++) {
			if((mstate&1<<i)!=0) continue;
			for(int j=0; j<n; j++) {
				if((wstate&1<<j)!=0) continue;
				int gap=0;
				for(int k=0; k<4; k++) {
					if(mb[i].charAt(k)!=wb[j].charAt(k)) gap++;
				}
				ret=Math.max(ret, find(mstate|1<<i, wstate|1<<j)+gap);
			}
		}
		return memo[mstate][wstate]=ret;
	}

}
