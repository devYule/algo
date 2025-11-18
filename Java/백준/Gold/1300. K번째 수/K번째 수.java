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
						Integer.parseInt(br.readLine())
					)
				)
			);
			bw.flush();
		}
	}

	int resolve(int n, int m) {
		int lo=1, hi=m;

		while(lo<=hi) {
			int mid=(lo+hi)>>>1;
			long order=0;
			for(int i=1; i<=n; i++) {
				order+=Math.min(mid/i, n);
			}
			if(order>=m) hi=mid-1;
			else lo=mid+1;
		}
		return lo;
	}

}
