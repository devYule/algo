import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			bw.write(
				String.valueOf(
					new Main().resolve(
						Integer.parseInt(br.readLine()),
						br.readLine()
					)
				)
			);
			bw.flush();
		}
	}

	int resolve(int n, String s) {
		return n-pi(n, s.toCharArray());
	}

	int pi(int n, char[] s) {
		int[] pi=new int[n];

		int j=0;
		for(int i=1; i<n; i++) {
			while(j>0 && s[i]!=s[j]) j=pi[j-1];
			if(s[i]==s[j]) pi[i]=++j;
		}
		return j;
	}

}
