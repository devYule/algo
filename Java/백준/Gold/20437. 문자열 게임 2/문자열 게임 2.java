import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			int R=Integer.parseInt(br.readLine());

			for(int r=0; r<R; r++) {
				if(r!=0) bw.write("\n");

				bw.write(
					String.valueOf(
						new Main().resolve(
							br.readLine(),
							Integer.parseInt(br.readLine())
						)
					)
				);
			}
			bw.flush();
		}
	}

	String resolve(String s, int k) {
		int[] chars=new int[26];
		for(char c: s.toCharArray()) {
			chars[c-'a']++;
		}

		int retmin=s.length();
		int retmax=-1;
		for(int i=0; i<26; i++) {
			if(chars[i]<k) continue;

			char c=(char)(i+'a');
			int min=s.length();
			int max=-1;
			int cnt=0;
			int right=0;
			for(int left=0; left<s.length(); left++) {
				while(right<s.length() && cnt<k) {
					char rc=s.charAt(right++);
					if(rc==c) cnt++;
				}
				if(cnt==k && s.charAt(left)==c && s.charAt(right-1)==c) {
					min=Math.min(min, right-left);
					max=Math.max(max, right-left);
				}
				if(s.charAt(left)==c) cnt--;
			}
			retmin=Math.min(retmin, min);
			retmax=Math.max(retmax, max);
		}
		if(retmax==-1) return "-1";
		return retmin + " " + retmax;
	}

}
