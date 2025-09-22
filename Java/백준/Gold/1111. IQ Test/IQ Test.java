import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			bw.write(
				String.valueOf(
					new Main().resolve(
						Integer.parseInt(br.readLine()),
						Arrays.stream(br.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray()
					)
				)
			);
			bw.flush();
		}
	}

	String resolve(int n, int[] nums) {
		if(nums.length<2) return "A";
		boolean[] vis=new boolean[401];

		int ret=Integer.MAX_VALUE;
		Queue<Integer> q=new ArrayDeque<>();
		q.add(0);
		while(!q.isEmpty()) {
			int a=q.poll();
			boolean can=true;
			int b=nums[1]-(nums[0]*a);
			if(vis[a+200]) continue;
			vis[a+200]=true;
			for(int i=1; i<n; i++) {
				if(nums[i-1]*a+b!=nums[i]) { can=false; break; }
			}
			
			if(can) {
				if(ret!=Integer.MAX_VALUE && ret!=nums[n-1]*a+b) return "A";
				ret=nums[n-1]*a+b;
			} 
			if(a+1<=200 && !vis[a+1+200]) q.add(a+1);
			if(a-1>=-200 && !vis[a-1+200]) q.add(a-1);
		}
		return ret==Integer.MAX_VALUE ? "B" : String.valueOf(ret);
	}

}
