import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			bw.write(
				String.valueOf(
					new Main().resolve(
						Long.parseLong(br.readLine())
					)
				)
			);
			bw.flush();
		}
	}
	
	Map<Long, Integer> dp=new HashMap<>();
	int resolve(long n) {
		dp.put(0l, 0);
		dp.put(1l, 0);
		dp.put(2l, 1);
		dp.put(3l, 1);

		return find(n);
	}

	int find(long n) {
		if(dp.get(n)!=null) return dp.get(n);
		dp.put(n, (int)Math.min(find(n/2)+n%2, find(n/3)+n%3)+1);
		return dp.get(n);
	}

}
