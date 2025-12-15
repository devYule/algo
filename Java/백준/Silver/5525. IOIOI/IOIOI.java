import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			int n=Integer.parseInt(br.readLine());
			int sn=Integer.parseInt(br.readLine());
			String s=br.readLine();
			bw.write(
				String.valueOf(
					new Main().resolve(
						n, sn, s
					)
				)
			);
			bw.flush();
		}
	}

	int resolve(int n, int sn, String s) {
		StringBuilder sb=new StringBuilder();
		sb.append("I");
		for(int i=0; i<n; i++) {
			sb.append("OI");
		}

		String t=sb.toString();

		int[] pi=pi(t);

		return kmp(s, t, pi);
	}

	int kmp(String a, String b, int[] pi) {
		int n=a.length();
		int m=b.length();
		int j=0;
		int ret=0;
		for(int i=0; i<n; i++) {
			while(j>0 && a.charAt(i)!=b.charAt(j)) j=pi[j-1];
			if(a.charAt(i)==b.charAt(j)) {
				j++;
				if(j==m) {
					ret++;
					j=pi[j-1];
				}
			}
		}
		return ret;
	}

	int[] pi(String s) {
		int n=s.length();
		int j=0;
		int[] pi=new int[n];
		for(int i=1; i<n; i++) {
			while(j>0 && s.charAt(i)!=s.charAt(j)) j=pi[j-1];

			if(s.charAt(i)==s.charAt(j)) {
				pi[i]=++j;
			}
		}
		return pi;
	}

}
