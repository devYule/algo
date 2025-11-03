import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			bw.write(
				String.valueOf(
					new Main().resolve(
						br.readLine().toCharArray(), br.readLine().toCharArray()
					)
				)
			);
			bw.flush();
		}
	}

	String resolve(char[] a, char[] b) {

		int[] pi=pi(b);

		List<Integer> ret=kmp(a, b, pi);

		return ret.size() + "\n" + ret.stream().map(String::valueOf).collect(java.util.stream.Collectors.joining(" "));
	}

	List<Integer> kmp(char[] a, char[] b, int[] pi) {
		int n=a.length, m=b.length;
		List<Integer> ret=new ArrayList<>();

		int j=0;
		for(int i=0; i<n; i++) {
			while(j>0 && a[i]!=b[j]) j=pi[j-1];
			if(a[i]==b[j]) {
				j++;
				if(j==m) {
					ret.add(i-j+2);
					j=pi[j-1];
				}
			}
		}
		return ret;
	}

	int[] pi(char[] a) {
		int n=a.length;
		int[] pi=new int[n];
		int j=0;

		for(int i=1; i<n; i++) {
			while(j>0 && a[i]!=a[j]) j=pi[j-1];
			if(a[i]==a[j]) pi[i]=++j;
		}
		return pi;
	}

}
