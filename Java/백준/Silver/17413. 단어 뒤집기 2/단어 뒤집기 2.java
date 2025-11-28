import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			bw.write(
				String.valueOf(
					new Main().resolve(
						br.readLine()
					)
				)
			);
			bw.flush();
		}
	}

	String resolve(String S) {
		int idx=0;
		boolean open=false;
		int start=-1;
		StringBuilder ret=new StringBuilder();
		while(idx<S.length()) {
			if(start==-1) start=idx;
			if(!open) {
				if(S.charAt(idx)==' ') {
					ret.append(reverse(S.substring(start, idx)));
					ret.append(" ");
					start=-1;
				} else if(S.charAt(idx)=='<') {
					if(start!=idx) ret.append(reverse(S.substring(start, idx)));
					ret.append("<");
					open=true;
				}
			} else {
				ret.append(String.valueOf(S.charAt(idx)));
				if(S.charAt(idx)=='>') {
					start=-1;
					open=false;
				}
			}
			idx++;
		}
		if(start!=-1) ret.append(reverse(S.substring(start, S.length())));
		return ret.toString();
	}

	String reverse(String word) {
		char[] c=new char[word.length()];
		for(int i=word.length()-1; i>=0; i--) {
			c[word.length()-i-1]=word.charAt(i);
		}
		return new String(c);
	}

}
