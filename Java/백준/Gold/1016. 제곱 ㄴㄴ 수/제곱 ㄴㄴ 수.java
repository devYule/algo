import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			String[] split=br.readLine().split("\\s+");
			bw.write(
				String.valueOf(
					new Main().resolve(Long.parseLong(split[0]), Long.parseLong(split[1]))
				)
			);
			bw.flush();
		}
	}

	int resolve(long min, long max) {
		int gap=(int)(max-min+1);
		boolean[] checked=new boolean[gap+1];
		int sqrt=(int)Math.sqrt(max);
		int canDiv=0;
		for(int i=2; i<=sqrt; i++) {
			long t=(i*(long)i);
			t=(min/t+(min%t==0 ? 0 : 1))*t;
			for(long j=t; j<=max; j+=(i*(long)i)) {
				if(j>=min && j<=max && !checked[(int)(j-min)]) { checked[(int)(j-min)]=true; canDiv++; }
			}
		}
		return gap-canDiv;
	}
}