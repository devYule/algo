import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {
			int R=Integer.parseInt(br.readLine());
			for(int i=0; i<R; i++) {
				String[] split=br.readLine().split("\\s+");
				bw.write(String.valueOf(new Main().resolve(Integer.parseInt(split[0]), Integer.parseInt(split[1]))));
				if(i<R-1) bw.write("\n");
			}
			bw.flush();
		}
	}

	int a, b;
	long[][] memo;
	long resolve(int aCnt, int bCnt) {
		this.a=aCnt; this.b=bCnt;
		this.memo=new long[aCnt][bCnt];
		for(int i=0; i<aCnt; i++) Arrays.fill(memo[i], -1);
		return find(0, 0);
	}

	long find(int l, int rfrom) {
		if(l==a) return 1;
		if(rfrom>=b) return 0;
		if(memo[l][rfrom]!=-1) return memo[l][rfrom];
		long ret=0;
		for(int i=rfrom; i<b; i++) {
			ret+=find(l+1, i+1);
		}
		return memo[l][rfrom]=ret;
	}
}