import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			int n=Integer.parseInt(br.readLine());
			String[] rcrd=new String[n];
			for(int i=0; i<n; i++) {
				rcrd[i]=br.readLine();
			}
			int m=Integer.parseInt(br.readLine());
			String[] words=new String[m];
			for(int i=0; i<m; i++) {
				words[i]=br.readLine();
			}

			bw.write(
				String.valueOf(
					new Main().resolve(
						n, m, rcrd, words
					)
				)
			);
			bw.flush();
		}
	}

	String resolve(int n, int m, String[] rcrd, String[] words) {
		Set<String> ns=new HashSet<>();
		char prev='-';
		char next='-';
		for(int i=0; i<n; i++) {
			if("?".equals(rcrd[i])) {
				if(i!=0) prev=rcrd[i-1].charAt(rcrd[i-1].length()-1);
				if(i!=n-1) next=rcrd[i+1].charAt(0);
				continue;
			}
			ns.add(rcrd[i]);
		}

		for(int i=0; i<m; i++) {
			if(prev!='-' && words[i].charAt(0)!=prev) continue;
			if(next!='-' && words[i].charAt(words[i].length()-1)!=next) continue;
			if(ns.contains(words[i])) continue;
			return words[i];
		}
		throw new RuntimeException();
	}

}
