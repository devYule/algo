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

		nextIdx.sort((a, b)-> out[a]-out[b]);

		int lastLocation=0;
		int time=0;
		for(int i=0; i<n; i++) {
			int idx=nextIdx.get(i);
			time+=Math.abs(lastLocation-dist[idx]);
			if(time<out[idx]) time+=out[idx]-time;
			lastLocation=dist[idx];
		}
		return time+lastLocation;

	}

}
