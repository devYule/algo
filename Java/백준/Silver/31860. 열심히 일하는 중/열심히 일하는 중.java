import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			String[] split=br.readLine().split("\\s");
			int n=Integer.parseInt(split[0]);
			int m=Integer.parseInt(split[1]);
			int k=Integer.parseInt(split[2]);
			int[] work=new int[n];
			for(int i=0; i<n; i++) {
				work[i]=Integer.parseInt(br.readLine());
			}

			bw.write(
				String.valueOf(
					new Main().resolve(
						n, m, k, work
					)
				)
			);
			bw.flush();
		}
	}

	String resolve(int n, int m, int k, int[] work) {
		PriorityQueue<Integer> q=new PriorityQueue<>((a, b) -> b-a);

		for(int w: work) {
			if(w<=k) continue;
			q.add(w);
		}

		int prev=0;
		int round=0;
		List<Integer> prevs=new ArrayList<>();

		while(!q.isEmpty()) {
			int cur=q.poll();
			int newVal=cur-m;
			if(newVal>k) q.add(newVal);
			prev=prev/2+cur;
			prevs.add(prev);
			round++;
		}
		return round + "\n" + prevs.stream().map(String::valueOf).collect(java.util.stream.Collectors.joining("\n"));
	}

}
