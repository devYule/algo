import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			int n=Integer.parseInt(br.readLine());
			int[][] use=new int[n][2];
			for(int i=0; i<n; i++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				use[i][0]=Integer.parseInt(st.nextToken());
				use[i][1]=Integer.parseInt(st.nextToken());
			}
			int q=Integer.parseInt(br.readLine());
			int[] cases=Arrays.stream(br.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();

			bw.write(
				String.valueOf(
					new Main().resolve(
						n, q, use, cases
					)
				)
			);
			bw.flush();
		}
	}

	String resolve(int n, int q, int[][] use, int[] Q) {
		
		int[] times=new int[1_000_002];
		for(int[] u: use) {
			times[u[0]]++;
			times[u[1]+1]--;
		}
		for(int i=1; i<times.length; i++) times[i]+=times[i-1];

		List<Integer> ret=new ArrayList<>();
		for(int qs: Q) {
			ret.add(times[qs]);
		}

		return ret.stream().map(String::valueOf).collect(java.util.stream.Collectors.joining("\n"));
	}

}
