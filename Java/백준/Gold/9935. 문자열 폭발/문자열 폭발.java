import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			bw.write(
				String.valueOf(
					new Main().resolve(
						br.readLine(),
						br.readLine()
					)
				)
			);
			bw.flush();
		}
	}

	String resolve(String base, String exp) {
		int[] pi=pi(exp);
		int n=base.length(), m=exp.length();
		int j=0;
		char[] st=new char[n];
		int[] stj=new int[n];
		int ist=0;
		for(int i=0; i<n; i++) {
			while(j>0 && base.charAt(i)!=exp.charAt(j)) j=pi[j-1];
			if(base.charAt(i)==exp.charAt(j)) {
				j++;
			}

			st[ist]=base.charAt(i);
			stj[ist++]=j;

			if(j==m) {
				ist-=m;
				j=ist==0 ? pi[j-1] : stj[ist-1];
			}
		}
		if(ist==0) return "FRULA";
		return new String(st, 0, ist);
	}


	int[] pi(String base) {
		int n=base.length();
		int j=0;
		int[] pi=new int[n];

		for(int i=1; i<n; i++) {
			while(j>0 && base.charAt(i)!=base.charAt(j)) j=pi[j-1];
			if(base.charAt(i)==base.charAt(j)) pi[i]=++j;
		}
		return pi;
	}

}
