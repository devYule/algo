import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			bw.write(
				String.valueOf(
					new Main().resolve(
						Integer.parseInt(br.readLine()), Arrays.stream(br.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray()
					)
				)
			);
			bw.flush();
		}
	}

	/*
		1개가 보일 면을 결정하고,
		1개가 보일면이 x일 때, 2개가 보이는 면이 y
		2개가 보이는 면이 y일 때, 3개가 보이는 면이 z
		이때 최소값.

		all: n^3
		2: 4*(n-1)+4*(n-2)
		3: 4
		1: 4*(n-2)*(n-1)+(n-2)^2
	*/

	long ret;
	int n, nums[];
	long resolve(int n, int[] nums) {
		if(n==1) {
			int ret=0;
			int max=0;
			for(int num: nums) {
				ret+=num;
				max=Math.max(max, num);
			}
			return (long) ret-max;
		}
		this.n=n;
		this.nums=nums;
		this.ret=Long.MAX_VALUE;

		find(new ArrayList<>(), 0);
		return ret;
	}

	void find(List<Integer> selected, int state) {
		if(selected.size()==3) {
			long one=one(n, nums[selected.get(0)]);
			long two=two(n, nums[selected.get(0)])+two(n, nums[selected.get(1)]);
			long three=three(n, nums[selected.get(0)])+three(n, nums[selected.get(1)])+three(n, nums[selected.get(2)]);
			ret=Math.min(ret, one+two+three);
			return;
		}

		for(int i=0; i<6; i++) {
			int j=5-i;
			if((state&1<<i)!=0 || (state&1<<j)!=0) continue;
			selected.add(i);
			find(selected, state|1<<i);
			selected.remove(Integer.valueOf(i));
		}
	}

	long two(int n, int face) {
		return (4L*(n-1L)+4L*(n-2L))*face;
	}

	long three(int n, int face) {
		return 4L*face;
	}

	long one(int n, int face) {
		return (4L*(n-2L)*(n-1L)+(n-2L)*(n-2L))*face;
	}
}