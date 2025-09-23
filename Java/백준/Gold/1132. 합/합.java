import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			int n=Integer.parseInt(br.readLine());
			char[][] numb=new char[n][];
			for(int i=0; i<n; i++) numb[i]=br.readLine().toCharArray();

			bw.write(
				String.valueOf(
					new Main().resolve(
						n, numb
					)
				)
			);
			bw.flush();
		}
	}

	boolean[] exst, cannotZero;
	long[] weight;
	char[][] numb;
	long[][] memo;
	List<Integer> use;
	long resolve(int n, char[][] numb) {
		this.memo=new long[1<<10][10];
		this.numb=numb;
		this.exst=new boolean[10];
		this.use=new ArrayList<>();
		this.weight=new long[10];
		this.cannotZero=new boolean[10];
		for(int i=0; i<1<<10; i++) Arrays.fill(memo[i], -1);
		for(char[] num: numb) {
			long p=1;
			for(int i=num.length-1; i>=0; i--) {
				if(i==0) cannotZero[num[i]-'A']=true;
				weight[num[i]-'A']+=p;
				exst[num[i]-'A']=true;
				p*=10;
			}
		}
		for(int i=0; i<exst.length; i++) {
			if(exst[i]) use.add(i);
		}

		return find(0, 0);
	}

	long find(int mask, int idx) {
		if(use.size()==idx) return 0;
		if(memo[mask][idx]!=-1) return memo[mask][idx];
		long ret=Long.MIN_VALUE;
		for(int i=9; i>=0; i--) {
			if((mask&1<<i)!=0 || (i==0 && cannotZero[use.get(idx)])) continue;
			long curDist=weight[use.get(idx)]*i;
			ret=Math.max(ret, find(mask|1<<i, idx+1)+curDist);
		}
		return memo[mask][idx]=ret;
	}

}
