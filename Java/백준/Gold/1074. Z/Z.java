import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			int[] a=Arrays.stream(br.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();

			bw.write(
				String.valueOf(
					new Main().resolve(
						a[0], a[1], a[2]
					)
				)
			);
			bw.flush();
		}
	}

	int resolve(int n, int r, int c) {
		return find((int)Math.pow(2, n), r, c);
	}

	int find(int n, int r, int c) {
		if(n==2) return r*n+c;
		int center=n/2-1;
		int prev=0;
		if(r>center && c>center) prev=3;
		else if(r>center) prev=2;
		else if(c>center) prev=1;
		int add=prev*((center+1)*(center+1));
		int newr=r;
		int newc=c;
		if(newr>center) newr%=center+1;
		if(newc>center) newc%=center+1;
		return find(n/2, newr, newc)+add;
	}

}
