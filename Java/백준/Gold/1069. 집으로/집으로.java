import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			String[] split=br.readLine().split("\\s");

			bw.write(
				String.valueOf(
					new Main().resolve(
						Integer.parseInt(split[1]),
						Integer.parseInt(split[0]),
						Integer.parseInt(split[2]),
						Integer.parseInt(split[3])
					)
				)
			);
			bw.flush();
		}
	}

	// d초에 t칸 vs 1초에 1칸
	double resolve(int y, int x, int d, int t) {
		double dist=Math.hypot(y, x);
		double ret=dist;
		int k=(int)(dist/d);
		for(int i=1; i<=Math.max(1, k); i++) ret=Math.min(ret, t*i+(double)Math.abs(dist-d*i));
		if(d>dist) ret=Math.min(ret, t*2);
		else ret=Math.min(ret, t*(k+1));

		return ret;
	}
}
