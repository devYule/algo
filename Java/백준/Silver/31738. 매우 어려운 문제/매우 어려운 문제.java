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
						Long.parseLong(split[0]),
						Integer.parseInt(split[1])
					)
				)
			);
			bw.flush();
		}
	}

	long resolve(long n, int m) {
		if(n>=m) return 0;
		long ret=1;
		for(int i=1; i<=n; i++) ret=((ret%m)*(i%m))%m;
		return ret;
	}

}
