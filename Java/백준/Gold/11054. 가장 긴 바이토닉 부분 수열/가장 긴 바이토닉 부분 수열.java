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

	int resolve(int n, int[] arr) {
		int[] dpl=new int[n];
		int[] dpr=new int[n];
		int ret=0;
		for(int i=0; i<n; i++) {
			int cur=arr[i];
			for(int j=i-1; j>=0; j--) {
				if(arr[j]<cur) dpl[i]=Math.max(dpl[i], dpl[j]+1);
			}
		}
		for(int i=n-1; i>=0; i--) {
			int cur=arr[i];
			for(int j=i+1; j<n; j++) {
				if(arr[j]<cur) dpr[i]=Math.max(dpr[i], dpr[j]+1);
			}
			ret=Math.max(ret, dpl[i]+dpr[i]+1);
		}
		return ret;
	}
}
