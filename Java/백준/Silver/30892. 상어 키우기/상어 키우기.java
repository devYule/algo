import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			StringTokenizer st=new StringTokenizer(br.readLine());
			int n=Integer.parseInt(st.nextToken());
			int k=Integer.parseInt(st.nextToken());
			int t=Integer.parseInt(st.nextToken());



			bw.write(
				String.valueOf(
					new Main().resolve(
						n, k, t, Arrays.stream(br.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray()
					)
				)
			);
			bw.flush();
		}
	}

	long resolve(int n, int k, int t, int[] list) {
		long sum=t;
		TreeMap<Long, Integer> map=new TreeMap<>();
		for(int l: list) {
			map.computeIfAbsent((long) l, (__)-> 0);
			map.put((long) l, map.get((long) l)+1);
		}

		for(int i=0; i<k; i++) {
			Map.Entry<Long, Integer> eat=map.lowerEntry(sum);
			if(eat==null) break;
			if(eat.getValue()==1) map.remove(eat.getKey());
			else map.put(eat.getKey(), eat.getValue()-1);
			sum+=eat.getKey();
		}
		return sum;
	}

}
