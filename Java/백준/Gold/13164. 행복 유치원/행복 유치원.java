import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			StringTokenizer st=new StringTokenizer(br.readLine());

			int n=Integer.parseInt(st.nextToken());
			int m=Integer.parseInt(st.nextToken());
			int[] nums=new int[n];
			st=new StringTokenizer(br.readLine());
			for(int i=0; i<n; i++) {
				nums[i]=Integer.parseInt(st.nextToken());
			}

			bw.write(
				String.valueOf(
					new Main().resolve(
						n, m, nums
					)
				)
			);
			bw.flush();
		}
	}

	int resolve(int n, int m, int[] nums) {
		List<Integer> gap=new ArrayList<>();
		for(int i=1; i<n; i++) {
			gap.add(nums[i]-nums[i-1]);
		}

		gap.sort(Comparator.naturalOrder());

		for(int i=1; i<m; i++) gap.remove(gap.size()-1);

		return gap.stream().mapToInt(it->it).sum();


	}

}
