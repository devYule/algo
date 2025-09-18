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
						a[0], a[1]
					)
				)
			);
			bw.flush();
		}
	}

	long resolve(int x, int y) {
		int z=calc(x, y);
		long lo=0, hi=x;
		while(lo<=hi) {
			long mid=(lo+hi)>>>1;
			long newx=x+mid;
			long newy=y+mid;
			if(decide(newx, newy, z)) hi=mid-1;
			else lo=mid+1;
		}
		return hi<0 || lo>x ? -1 : lo;
	}

	boolean decide(long x, long y, int z) {
		int newz=calc(x, y);
		return newz!=z;
	}

	int calc(long x, long y) {
		return (int)(100.0d/x*y);
	}
	int calc(int x, int y) {
		return (int)(100.0d/x*y);
	}

}
