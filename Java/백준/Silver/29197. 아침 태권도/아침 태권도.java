import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			int n=Integer.parseInt(br.readLine());
			int[][] each=new int[n][2];
			for(int i=0; i<n; i++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				each[i][0]=Integer.parseInt(st.nextToken());
				each[i][1]=Integer.parseInt(st.nextToken());
			}

			bw.write(
				String.valueOf(
					new Main().resolve(
						n, each
					)
				)
			);
			bw.flush();
		}
	}

	int resolve(int n, int[][] each) {
		Set<String> set=new HashSet<>();
		for(int[] e: each) {
			int gcd=gcd(e[0], e[1]);
			set.add((e[0]/gcd) + "|" + (e[1]/gcd));
		}
		return set.size();
	}

	int gcd(int a, int b) {
		a=Math.abs(a);
		b=Math.abs(b);
		while(b!=0) {
			int tmp=a%b;
			a=b;
			b=tmp;
		}
		return a;
	}

}
