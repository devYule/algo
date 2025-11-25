import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			StringTokenizer st=new StringTokenizer(br.readLine());
			int n=Integer.parseInt(st.nextToken());
			int m=Integer.parseInt(st.nextToken());
			int[] tree=new int[n];
			st=new StringTokenizer(br.readLine());
			for(int i=0; i<n; i++) tree[i]=Integer.parseInt(st.nextToken());

			bw.write(
				String.valueOf(
					new Main().resolve(
						n, m, tree
					)
				)
			);
			bw.flush();
		}
	}

	int resolve(int n, int m, int[] tree) {
		int lo=0, hi=Arrays.stream(tree).max().orElse(0);

		int ret=0;
		while(lo<=hi) {
			int mid=(lo+hi)>>>1;

			long cut=0;
			for(int t: tree) {
				if(t>mid) cut+=t-mid;
				if(cut>m) break;
			}

			if(cut>=m) lo=mid+1;
			else hi=mid-1;
		}
		return hi;
	}


}
