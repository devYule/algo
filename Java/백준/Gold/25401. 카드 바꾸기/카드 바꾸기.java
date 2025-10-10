import java.io.*;
import java.util.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			bw.write(String.valueOf(new Main().resolve(
				Integer.parseInt(br.readLine()),
				Arrays.stream(br.readLine().split("\\s")).mapToLong(Long::parseLong).toArray()
			)));
			bw.flush();
		}
	}

	int resolve(int n, long[] nums) {
		int max=0;
		for(int i=0; i<n; i++) {
			Map<Long, Integer> gaps=new HashMap<>();
			for(int j=i+1; j<n; j++) {
				long gap=nums[j]-nums[i];
				int dist=j-i;
				if(gap%dist!=0) continue;
				long fg=gap/dist;
				gaps.computeIfAbsent(fg, key->0);
				gaps.put(fg, gaps.get(fg)+1);
				max=Math.max(max, gaps.get(fg));
			}
		}
		return n-1-max;
	}
}
