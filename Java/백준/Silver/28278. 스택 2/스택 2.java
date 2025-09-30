import java.util.function.*;
import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			int n=Integer.parseInt(br.readLine());
			int[][] qs=new int[n][];
			for(int i=0; i<n; i++) {
				qs[i]=Arrays.stream(br.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();
			}

			bw.write(
				String.valueOf(
					new Main().resolve(
						n, qs
					)
				)
			);
			bw.flush();
		}
	}

	List<Function<Stack<Integer>, Integer>> str=new ArrayList<>();
	final int MIN=2;
	String resolve(int n, int[][] qs) {
		Stack<Integer> st=new Stack<Integer>();
		init();
		List<Integer> ret=new ArrayList<>();
		for(int[] q: qs) {
			if(q[0]==1) st.add(q[1]);
			else ret.add(str.get(q[0]-MIN).apply(st));
		}
		return ret.stream().map(String::valueOf).collect(java.util.stream.Collectors.joining("\n"));
	}

	void init() {
		str.add(
			st -> st.isEmpty() ? -1 : st.pop()
		);
		str.add(
			st -> st.size()
		);
		str.add(
			st -> st.isEmpty() ? 1 : 0
		);
		str.add(
			st -> st.isEmpty() ? -1 : st.peek()
		);
	}

}
