import java.util.*;
import java.util.function.BiFunction;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			int n=Integer.parseInt(br.readLine());
			int[][] cmd=new int[n][];
			for(int i=0; i<n; i++) {
				cmd[i]=Arrays.stream(br.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();
			}

			bw.write(
				String.valueOf(
					new Main().resolve(
						n, cmd
					)
				)
			);
			bw.flush();
		}
	}
	final int NONE=-((int)1e9);
	String resolve(int n, int[][] cmd) {
		List<BiFunction<int[], Deque<Integer>, Integer>> str=new ArrayList<>();
		init(str);
		List<Integer> ret=new ArrayList<>();
		Deque<Integer> dq=new ArrayDeque<>();
		for(int[] c: cmd) {
			int res=str.get(c[0]).apply(c, dq);
			if(res!=NONE) ret.add(res);
		}
		return ret.stream().map(String::valueOf).collect(java.util.stream.Collectors.joining("\n"));
	}

	void init(List<BiFunction<int[], Deque<Integer>, Integer>> f) {
		f.add(null);
		f.add((arr, dq) -> {
			dq.addFirst(arr[1]);
			return NONE;
		});

		f.add((arr, dq) -> {
			dq.addLast(arr[1]);
			return NONE;
		});

		f.add((arr, dq) -> dq.isEmpty() ? -1 : dq.pollFirst());
		f.add((arr, dq) -> dq.isEmpty() ? -1 : dq.pollLast());
		f.add((arr, dq) -> dq.size());
		f.add((arr, dq) -> dq.isEmpty() ? 1 : 0);
		f.add((arr, dq) -> dq.isEmpty() ? -1 : dq.peekFirst());
		f.add((arr, dq) -> dq.isEmpty() ? -1 : dq.peekLast());


	}



}
