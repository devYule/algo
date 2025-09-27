import java.math.*;
import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			String[] s=br.readLine().split("\\s");

			bw.write(
				String.valueOf(
					new Main().resolve(
						Integer.parseInt(s[0]),
						Integer.parseInt(s[1]),
						Integer.parseInt(s[2]),
						Integer.parseInt(s[3])
					)
				)
			);
			bw.flush();
		}
	}

	double resolve(int n, int l, int w, int h) {
		double hi=Math.max(l, Math.max(w, h)), lo=0;
		for(int i=0; i<100; i++) {
			double mid=(hi+lo)/2d;
			if(can(mid, l, w, h, n)) {
				lo=mid;
			} else hi=mid;
		}
		return lo;
	}

	boolean can(double size, int l, int w, int h, int cnt) {
		return BigInteger.valueOf((long)(l/size))
			.multiply(BigInteger.valueOf((long)(w/size)))
			.multiply(BigInteger.valueOf((long)(h/size))).compareTo(BigInteger.valueOf(cnt))>=0;
	}

}
