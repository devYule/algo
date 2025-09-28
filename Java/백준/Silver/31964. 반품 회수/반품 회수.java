import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			int n=Integer.parseInt(br.readLine());
			int[] dist=Arrays.stream(br.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();
			int[] outTime=Arrays.stream(br.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();

			bw.write(
				String.valueOf(
					new Main().resolve(
						n, dist, outTime
					)
				)
			);
			bw.flush();
		}
	}

	int resolve(int n, int[] dist, int[] out) {
		List<Integer> nextIdx=new ArrayList<>();
		for(int i=0; i<n; i++) nextIdx.add(i);

		nextIdx.sort((a, b)-> dist[b]-dist[a]);

		int time=0;

		int lastIdx=nextIdx.get(0);
		time+=dist[lastIdx];
		if(out[lastIdx]>dist[lastIdx]) time+=out[lastIdx]-dist[lastIdx];
		int lastLoc=dist[lastIdx];

		for(int i=1; i<nextIdx.size(); i++) {
			int idx=nextIdx.get(i);
			time+=lastLoc-dist[idx];
			if(out[idx]>time) time+=out[idx]-time;
			lastLoc=dist[idx];
		}
		return time+lastLoc;

	}

}
