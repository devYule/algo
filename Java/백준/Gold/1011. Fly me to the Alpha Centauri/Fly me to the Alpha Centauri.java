import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {
			int R=Integer.parseInt(br.readLine());
			for(int i=0; i<R; i++) {
				String[] split=br.readLine().split("\\s+");
				bw.write(String.valueOf(new Main().resolve(Integer.parseInt(split[0]), Integer.parseInt(split[1]))));
				if(i<R-1) bw.write("\n");
			}
			bw.flush();
		}
	}

	long resolve(int from, int to) {
		to-=from;
		from=0;
		if(to-from<=3) return to-from;
		long i=1;
		while(true) {
			long p=(i+1)*(i+1);
			if(p==to) return (i+1)*2-1;
			if(p>to) {
				long rest=to-i*i;
				return (2*i-1)+rest/i+(rest%i==0 ? 0 : 1);
			}
			i++;
		}
	}
}