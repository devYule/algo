import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			int n=Integer.parseInt(br.readLine());
			int[] nums=Arrays.stream(br.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();
			int s=Integer.parseInt(br.readLine());

			bw.write(
				String.valueOf(
					new Main().resolve(
						n, nums, s
					)
				)
			);
			bw.flush();
		}
	}

	String resolve(int n, int[] nums, int s) {
		for(int i=0; i<n; i++) {
			int max=-1;
			int maxi=-1;
			for(int j=1; i+j<n; j++) {
				if(j>s) break;
				if(max<nums[i+j]) { max=nums[i+j]; maxi=i+j; }
			}
			if(nums[i]>max) continue;
			for(int j=maxi; j>i; j--) {
				int tmp=nums[j];
				nums[j]=nums[j-1];
				nums[j-1]=tmp;
			}
			s-=maxi-i;
			if(s==0) break;
		}
		return Arrays.toString(nums).replaceAll("[\\[\\],]", "");
	}

}
