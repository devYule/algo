import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			boolean flag=false;
			while(true) {
				String token=br.readLine();
				if(".".equals(token)) break;
				if(flag) bw.write("\n");
				flag=true;

				bw.write(
					String.valueOf(
						new Main().resolve(
							token.toCharArray()
						)
					)
				);

			}
			
			bw.flush();
		}
	}

	int resolve(char[] s) {
		int n=s.length;
		int j=0;
		int max=0;
		int[] pi=new int[n];
		for(int i=1; i<n; i++) {
			while(j>0 && s[i]!=s[j]) j=pi[j-1];
			if(s[i]==s[j]) {
				pi[i]=++j;
				max=Math.max(max, pi[i]);
			}
		}

		if(max==0) return 1;
		int jump=n-max;

		int oi=0;
		int ti=jump;
		while(ti<n) {
			for(int i=0; i<jump; i++) {
				if(ti>=n) return 1;
				if(s[i]!=s[ti++]) return 1;
			}
		}
		return n/jump;
	}


}
