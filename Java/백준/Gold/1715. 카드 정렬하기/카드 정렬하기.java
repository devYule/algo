import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			int n=Integer.parseInt(br.readLine());
			int[] nums=new int[n];
			for(int i=0; i<n; i++) nums[i]=Integer.parseInt(br.readLine());
			bw.write(
				String.valueOf(
					new Main().resolve(
						n, nums
					)
				)
			);
			bw.flush();
		}
	}

	int resolve(int n, int[] nums) {
		if(n==1) return 0;
		PriorityQueue<Integer> q=new PriorityQueue<>();
		for(int num: nums) q.add(num);

		int acc=0;
		while(q.size()>=2) {
			int sum=q.poll()+q.poll();
			acc+=sum;
			q.add(sum);
		}
		return acc;
	}

}
