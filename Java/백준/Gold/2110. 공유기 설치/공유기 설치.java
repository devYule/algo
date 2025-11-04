import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			StringTokenizer st=new StringTokenizer(br.readLine());
			int n=Integer.parseInt(st.nextToken());
			int m=Integer.parseInt(st.nextToken());
			int[] loc=new int[n];
			for(int i=0; i<n; i++) loc[i]=Integer.parseInt(br.readLine());

			bw.write(
				String.valueOf(
					new Main().resolve(
						n, m, loc
					)
				)
			);
			bw.flush();
		}
	}

	int resolve(int n, int m, int[] loc) {
		Arrays.sort(loc);

		int lo=1, hi=loc[n-1];
		while(lo<=hi) {
			int mid=(lo+hi)>>>1;
			if(check(mid, loc)<m) hi=mid-1;
			else lo=mid+1;
		}
		return hi;
	}

	int check(int dist, int[] loc) {
		int cnt=0;
		int idx=0;
		while(idx<loc.length) {
			cnt++;
			int cur=loc[idx];
			int next=cur+dist;
			int nextIdx=idx+1;
			while(nextIdx<loc.length && next>loc[nextIdx]) nextIdx++;
			idx=nextIdx;
		}
		return cnt;
	}

}
