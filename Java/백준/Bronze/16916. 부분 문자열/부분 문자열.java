import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			bw.write(
				String.valueOf(
					new Main().resolve(
						br.readLine(), br.readLine()
					)
				)
			);
			bw.flush();
		}
	}

	int POS=1, NEG=0;
	int resolve(String a, String b) {
		int[] pi=pi(b);

		return kmp(a, b, pi);
	}

	int[] pi(String a) {
		int n=a.length();
		int[] pi=new int[n];
		int j=0;
		for(int i=1; i<n; i++) {
			while(j>0 && a.charAt(i)!=a.charAt(j)) j=pi[j-1];

			if(a.charAt(i)==a.charAt(j)) {
				pi[i]=++j;
			}
		}
		return pi;
	}

	int kmp(String a, String b, int[] pi) {
		int n=a.length(), m=b.length();

		int j=0;
		for(int i=0; i<n; i++) {
			while(j>0 && a.charAt(i)!=b.charAt(j)) j=pi[j-1];

			if(a.charAt(i)==b.charAt(j)) {
				j++;
				if(j==m) {
					return POS;
				}
			}
		}
		return NEG;
	}

}
