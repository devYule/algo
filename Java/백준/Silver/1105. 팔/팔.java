import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			String[] nm=br.readLine().split("\\s");


			bw.write(
				String.valueOf(
					new Main().resolve(
						Long.parseLong(nm[0]),
						Long.parseLong(nm[1])
					)
				)
			);
			bw.flush();
		}
	}

	long max;
	int resolve(long n, long m) {
		this.max=m;
		long tm=m;
		int mcnt=0;
		while(tm>0) {
			if(tm%10==8) mcnt++;
			tm/=10;
		}
		return Math.min(mcnt, find(n));
	}
	int find(long num) {
		if(num>max) return (int)1e9;
		String n=String.valueOf(num);
		long mul=1;

		int ret=(int)1e9;
		int cnt8=0;
		for(int i=n.length()-1; i>=0; i--) {
			if(n.charAt(i)=='8') {
				cnt8++;
				ret=Math.min(ret, Math.min(find((num/mul+1)*mul), find(num+(mul*2))));
			}
			mul*=10;
		}
		return Math.min(ret, cnt8);
	}

}
