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
						Long.parseLong(split[1]),
						Long.parseLong(split[2]),
						Long.parseLong(split[3]),
						Long.parseLong(split[4]),
						Long.parseLong(split[5]),
						Long.parseLong(split[6]),
						Long.parseLong(split[7])
					)
				)
			);
			bw.flush();
		}
	}

	long resolve(long w, long h, long f, long c, long x1, long y1, long x2, long y2) {
		long overwrap=Math.min(f, Math.abs(w-f));
		long hOverCnt=c+1;
		long dupPaint=(x2-x1)*(y2-y1);
		if(overwrap>0 && w!=f) {
			if(x2<=overwrap) dupPaint*=2;
			else if(x1<overwrap) dupPaint+=(overwrap-x1)*(y2-y1);
		}
		return w*h-dupPaint*hOverCnt;
	}
}
