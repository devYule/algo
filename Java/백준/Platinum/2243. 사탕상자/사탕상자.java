import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			int n=Integer.parseInt(br.readLine());
			int[][] queries=new int[n][3];
			for(int i=0; i<n; i++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				int lim=queries[i][0]=Integer.parseInt(st.nextToken());

				for(int j=0; j<lim; j++) {
					queries[i][j+1]=Integer.parseInt(st.nextToken());
				}
			}

			bw.write(
				String.valueOf(
					new Main().resolve(
						n, queries
					)
				)
			);
			bw.flush();
		}
	}

	String resolve(int n, int[][] queries) {
		Fenwick fw=new Fenwick();
		StringBuilder sb=new StringBuilder();
		boolean ln=false;
		for(int[] q: queries) {
			if(q[0]==2) {
				fw.update(q[1], q[2]);
			} else {
				if(ln) sb.append("\n");
				ln=true;
				int target=q[1];
				int lo=1, hi=1_000_000;
				while(lo<=hi) {
					int mid=(lo+hi)>>>1;
					int find=fw.query(mid);
					if(find>=target) hi=mid-1;
					else lo=mid+1;
				}
				sb.append(lo);
				fw.update(lo, -1);
			}
		}
		return sb.toString();
	}

	class Fenwick {
		int n=1_000_001;
		int tree[]=new int[1_000_001];
		
		void update(int idx, int value) {
			while(idx<n) {
				tree[idx]+=value;
				idx+=idx&(-idx);
			}
		}

		int query(int idx) {
			int ret=0;
			while(idx>0) {
				ret+=tree[idx];
				idx-=idx&(-idx);
			}
			return ret;
		}


	}

}
