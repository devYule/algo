import java.math.*;
import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {


			int n=Integer.parseInt(br.readLine());
			String[] words=new String[n];
			for(int i=0; i<n; i++) {
				words[i]=br.readLine();
			}
			int k=Integer.parseInt(br.readLine());
			bw.write(
				String.valueOf(
					new Main().resolve(
						n, words, k
					)
				)
			);
			bw.flush();
		}
	}

	String resolve(int n, String[] words, int k) {
		StringBuilder rmTokens=new StringBuilder();

		int order=0;
		
		BigInteger[] dist=new BigInteger[36];
		for(int i=0; i<36; i++) dist[i]=BigInteger.ZERO;
		for(String w: words) {
			for(int i=0; i<w.length(); i++) {
				int ordered=order(w.charAt(i));
				int gap=35-ordered;

				dist[ordered]=dist[ordered].add(BigInteger.valueOf(36).pow(w.length()-i-1).multiply(BigInteger.valueOf(gap)));
			}
		}

		List<Integer> idcs=new ArrayList<>();
		for(int i=0; i<36; i++) idcs.add(i);
		idcs.sort((a, b) -> dist[b].compareTo(dist[a]));

		for(int i=0; i<k; i++) {
			rmTokens.append(rorder(idcs.get(i)));
		}

		BigInteger tot=BigInteger.ZERO;
		for(String m: words) {
			if(rmTokens.length()>0) m=m.replaceAll("[" + rmTokens.toString() + "]", "Z");
			tot=tot.add(to10(m.toCharArray()));
		}
		
		return to36(tot);
	}

	BigInteger to10(char[] n) {
		BigInteger ret=BigInteger.ZERO;
		BigInteger base=BigInteger.valueOf(36);
		for(int i=0; i<n.length; i++) {
			ret=ret.multiply(base).add(BigInteger.valueOf(order(n[i])));
		}
		return ret;
	}

	String to36(BigInteger n) {
		if(n.signum()==0) return "0";
		StringBuilder sb=new StringBuilder();
		BigInteger dn=BigInteger.valueOf(36);
		while(n.signum()>0) {
			sb.append(String.valueOf(rorder(n.mod(dn).intValue())));
			n=n.divide(dn);
		}
		return sb.reverse().toString();
	}

	char rorder(int n) {
		return n>=0 && n<=9 ? (char)(n+'0') : (char)(n-10+'A');
	}
	int order(char c) {
		return c>='0' && c<='9' ? c-'0' : c-'A'+10;
	}
}