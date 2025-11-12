import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			int T=Integer.parseInt(br.readLine());

			int n=Integer.parseInt(br.readLine());
			int[] N=new int[n];
			StringTokenizer st=new StringTokenizer(br.readLine());
			for(int i=0; i<n; i++) N[i]=Integer.parseInt(st.nextToken());
			int m=Integer.parseInt(br.readLine());
			int[] M=new int[m];
			st=new StringTokenizer(br.readLine());
			for(int i=0; i<m; i++) M[i]=Integer.parseInt(st.nextToken());

			bw.write(
				String.valueOf(
					new Main().resolve(
						T, n, m, N, M
					)
				)
			);
			bw.flush();
		}
	}

	long resolve(int T, int n, int m, int[] N, int[] M) {
		List<Long> nsum=new ArrayList<>();
		List<Long> msum=new ArrayList<>();

		for(int i=0; i<n; i++) {
			long sum=N[i];
			nsum.add(sum);
			for(int j=i+1; j<n; j++) {
				nsum.add(sum+=N[j]);
			}
		}

		for(int i=0; i<m; i++) {
			long sum=M[i];
			msum.add(sum);
			for(int j=i+1; j<m; j++) {
				msum.add(sum+=M[j]);
			}
		}

		msum.sort(Comparator.naturalOrder());

		long ret=0;
		for(long ns: nsum) {
			int lo=0, hi=msum.size()-1;

			while(lo<=hi) {
				int mid=(lo+hi)>>>1;
				if(ns+msum.get(mid)>T) hi=mid-1;
				else lo=mid+1;
			}
			int right=hi;
			lo=0; hi=msum.size()-1;
			while(lo<=hi) {
				int mid=(lo+hi)>>>1;
				if(ns+msum.get(mid)>=T) hi=mid-1;
				else lo=mid+1;
			}
			int left=lo;
			ret+=right-left+1;
		}
		return ret;
	}
}
