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
		
		long ret=Long.MAX_VALUE;

		boolean[] cannot=new boolean[6];
		for(int i=0; i<6; i++) {
			cannot[i]=true;
			cannot[5-i]=true;
			for(int j=0; j<6; j++) {
				if(i==j || cannot[j] || cannot[5-j]) continue;
				cannot[j]=true;
				cannot[5-j]=true;
				for(int k=0; k<6; k++) {
					if(k==i || k==j || cannot[k] || cannot[5-k]) continue;
					long one=one(n, nums[i]);
					long two=two(n, nums[j])+two(n, nums[i]);
					long three=three(n, nums[k])+three(n, nums[j])+three(n, nums[i]);
					ret=Math.min(ret, one+two+three);
				}
				cannot[j]=false;
				cannot[5-j]=false;
			}
			cannot[i]=false;
			cannot[5-i]=false;
		}
		return ret;
	}

	long all(int n) {
		return (long)Math.pow(n, 3);
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