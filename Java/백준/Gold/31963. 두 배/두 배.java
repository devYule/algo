import java.math.*;
import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			int n=Integer.parseInt(br.readLine());
			int[] nums=Arrays.stream(br.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();

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

	long resolve(int n, int[] nums) {

		long ret=0;
		BigInteger prev=BigInteger.valueOf(nums[0]);
		for(int i=1; i<n; i++) {
			BigInteger cur=BigInteger.valueOf(nums[i]);
			if(cur.compareTo(prev)<0) {
				int bitGap=prev.bitLength()-cur.bitLength();
				if(cur.shiftLeft(bitGap).compareTo(prev)<0) bitGap++;
				ret+=bitGap;
				prev=cur.shiftLeft(bitGap);
			} else prev=cur;
		}

		return ret;
	}

}
