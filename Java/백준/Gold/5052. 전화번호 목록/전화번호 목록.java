import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			int T=Integer.parseInt(br.readLine());

			for(int r=0; r<T; r++) {
				if(r!=0) bw.write("\n");

				int n=Integer.parseInt(br.readLine());
				String[] tk=new String[n];
				for(int i=0; i<n; i++) tk[i]=br.readLine();

				bw.write(
					String.valueOf(
						new Main().resolve(
							n, tk
						)
					)
				);
			}
			bw.flush();
		}
	}

	String POS="YES", NEG="NO";
	String resolve(int n, String[] tk) {
		Trie root=new Trie();
		for(String t: tk) {
			if(!root.add(t, 0)) return NEG;
		}
		return POS;
	}

	class Trie {
		Trie[] children=new Trie[10];
		boolean terminal;

		boolean add(String s, int idx) {
			if(idx==s.length()) return terminal=true;
			int cur=toNum(s.charAt(idx));
			if(idx==s.length()-1 && children[cur]!=null) return false;
			if(children[cur]==null) children[cur]=new Trie();
			Trie c=children[cur];
			if(c.terminal) return false;

			return c.add(s, idx+1);
		}

		int toNum(char c) {
			return c-'0';
		}
	}

}
