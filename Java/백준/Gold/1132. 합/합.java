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

	boolean[] exst;
	int exstCnt;
	char[][] numb;
	long resolve(int n, char[][] numb) {
		this.numb=numb;
		this.exst=new boolean[10];
		for(char[] num: numb) {
			for(int i=0; i<num.length; i++) {
				if(exst[num[i]-'A']) continue;
				exst[num[i]-'A']=true;
				exstCnt++;
			}
		}
		return find(0, 0, new int[10]);
	}

	long find(int mask, int intChar, int[] alp) {
		if(Integer.bitCount(mask)==exstCnt || intChar>('J'-'A')) {
			long ret=0;
			for(char[] num: numb) {
				long loc=0;
				for(int i=0; i<num.length; i++) {
					if(i==0 && alp[num[i]-'A']==0) return -1;
					loc=loc*10+alp[num[i]-'A'];
				}
				ret+=loc;
			}
			return ret;
		}

		if(!exst[intChar]) return find(mask, intChar+1, alp);

		long ret=0;
		for(int i=9; i>=0; i--) {
			if((mask&1<<i)!=0) continue;
			alp[intChar]=i;
			ret=Math.max(ret, find(mask|1<<i, intChar+1, alp));
			alp[intChar]=0;
		}
		return ret;
	}

}
