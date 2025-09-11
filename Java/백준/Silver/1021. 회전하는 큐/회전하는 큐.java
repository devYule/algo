import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			String[] split=br.readLine().split("\\s+");

			int n=Integer.parseInt(split[0]);
			int m=Integer.parseInt(split[1]);

			bw.write(
				String.valueOf(
					new Main().resolve(n, m, Arrays.stream(br.readLine().split("\\s+"))
						.mapToInt(a->Integer.parseInt(a)).toArray())
				)
			);
			bw.flush();
		}
	}

	int resolve(int n, int m, int[] orders) {
		// 1 2 3 4 5 6 7 8 9 10
		int ret=0;
		Deque<Integer> q=new LinkedList<>();
		for(int i=1; i<=n; i++) q.add(i);
		List<Integer> removed=new ArrayList<>();
		for(int o: orders) {
			while(q.peek()!=o) { removed.add(q.pollFirst()); }
			int leftCnt=removed.size();
			int leftSize=removed.size();
			int rightSize=q.size();
			q.poll();
			if(leftSize>=rightSize) ret+=rightSize;
			else ret+=leftSize;
			for(int r: removed) q.addLast(r);
			removed.clear();
		}
		return ret;
	}

}