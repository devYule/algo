import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			StringTokenizer st=new StringTokenizer(br.readLine());

			bw.write(
				String.valueOf(
					new Main().resolve(
						Long.parseLong(st.nextToken()),
						Integer.parseInt(st.nextToken()),
						Integer.parseInt(st.nextToken())
					)
				)
			);
			bw.flush();
		}
	}

	Map<Long, Long> memo;
	long resolve(long n, int p, int q) {
		memo=new HashMap<>();
		return find(n, p, q);
	}

	long find(long x, int p, int q) {
		if(x==0) return 1L;
		if(x==1) return 2L;
		if(memo.get(x)!=null) return memo.get(x);
		memo.put(x, find(x/p, p, q)+find(x/q, p, q));
		return memo.get(x);
	}

}
