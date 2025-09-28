import java.math.*;
import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			String[] split=br.readLine().split("\\s");
			long[] nums=Arrays.stream(br.readLine().split("\\s")).mapToLong(Long::parseLong).toArray();


			bw.write(
				String.valueOf(
					new Main().resolve(
						Long.parseLong(split[0]),
						Integer.parseInt(split[1]),
						Integer.parseInt(split[2]),
						nums
					)
				)
			);
			bw.flush();
		}
	}

	String resolve(long l, int n, int k, long[] nums) {

		StringBuilder sb=new StringBuilder();
		PriorityQueue<long[]> q=new PriorityQueue<>((a, b)-> Long.compare(a[0], b[0]));
		Set<Long> vis=new HashSet<>();
		for(long num: nums) q.add(new long[] {0l, num});
		List<Long> ret=new ArrayList<>();

		while(!q.isEmpty()) {
			long[] curs=q.poll();

			long cost=curs[0];
			long cur=curs[1];

			if(vis.contains(cur)) continue;
			vis.add(cur);

			ret.add(cost);
			if(ret.size()==k) break;

			if(cur+1<=l && !vis.contains(cur+1)) {
				q.add(new long[] {cost+1, cur+1});
			}

			if(cur-1>=0 && !vis.contains(cur-1)) {
				q.add(new long[] {cost+1, cur-1});
			}
		}

		return ret.stream().map(String::valueOf).collect(java.util.stream.Collectors.joining("\n"));
	}
}
