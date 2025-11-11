import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			int n=Integer.parseInt(br.readLine());

			int[] nums=new int[n];
			StringTokenizer st=new StringTokenizer(br.readLine());

			for(int i=0; i<n; i++) {
				nums[i]=Integer.parseInt(st.nextToken());
			}

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

	long ret=0;
	long resolve(int n, int[] nums) {
		mergeSort(0, n-1, nums);
		return ret;
	}

	void mergeSort(int l, int r, int[] arr) {
		if(l==r) return;

		int mid=(l+r)>>>1;

		mergeSort(l, mid, arr);
		mergeSort(mid+1, r, arr);

		int lo=l;
		int hi=mid+1;
		int[] tmp=new int[r-l+1];
		int ti=0;
		while(lo<=mid || hi<=r) {
			if(lo<=mid && (hi>r || arr[lo]<=arr[hi])) tmp[ti++]=arr[lo++];
			else {
				tmp[ti++]=arr[hi++];
				ret+=mid-lo+1L;
			}
		}

		for(int i=0; i<ti; i++) {
			arr[l+i]=tmp[i];
		}
	}
}
