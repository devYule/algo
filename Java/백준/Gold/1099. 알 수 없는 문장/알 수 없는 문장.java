import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			String o=br.readLine();
			int n=Integer.parseInt(br.readLine());
			String[] words=new String[n];
			for(int i=0; i<n; i++) {
				words[i]=br.readLine();
			}

			bw.write(
				String.valueOf(
					new Main().resolve(
						o, n, words
					)
				)
			);
			bw.flush();
		}
	}

	String[] words;
	String orin;
	int[] memo;
	int[] alp;
	int resolve(String orin, int n, String[] words) {
		this.memo=new int[orin.length()];
		this.words=words;
		this.orin=orin;
		this.alp=new int[26];
		Arrays.fill(memo, -1);
		int ret=find(0);
		return ret==(int)1e9 ? -1 : ret;
	}

	int find(int oi) {
		if(oi>orin.length()) return (int)1e9;
		if(oi==orin.length()) return 0;
		if(memo[oi]!=-1) return memo[oi];

		int ret=(int)1e9;

		for(String w: words) {
			if(oi+w.length()>orin.length()) continue;
			for(int i=0; i<w.length(); i++) {
				alp[orin.charAt(oi+i)-'a']++;
				alp[w.charAt(i)-'a']--;
			}
			boolean can=true;
			for(int i=0; i<26; i++) {
				if(can && alp[i]!=0) can=false;
				alp[i]=0;
			}

			if(can) {
				int nextDist=0;
				for(int i=0; i<w.length(); i++) {
					if(w.charAt(i)!=orin.charAt(oi+i)) nextDist++;
				}
				ret=Math.min(ret, find(oi+w.length())+nextDist);
			}
		}
		return memo[oi]=ret;
	}

}
