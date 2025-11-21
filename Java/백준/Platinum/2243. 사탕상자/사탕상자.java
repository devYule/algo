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
		Seg seg=new Seg();
		StringBuilder sb=new StringBuilder();
		boolean ln=false;
		for(int[] q: queries) {
			if(q[0]==2) {
				seg.update(q[1], q[2]);
			} else {
				if(ln) sb.append("\n");
				ln=true;
				int target=q[1];
				int lo=1, hi=1_000_000;
				while(lo<=hi) {
					int mid=(lo+hi)>>>1;
					int find=seg.query(mid);
					if(find>=target) hi=mid-1;
					else lo=mid+1;
				}
				sb.append(lo);
				seg.update(lo, -1);
			}
		}
		return sb.toString();
	}

	class Seg {
		int n=1_000_001;
		int tree[]=new int[4_000_004];
		
		void update(int idx, int acc) {
			update(idx, acc, 0, n-1, 1);
		}

		int update(int idx, int value, int l, int r, int node) {
			if(l>idx || r<idx) return tree[node];
			if(l==r) return tree[node]+=value;
			int mid=(l+r)>>>1;
			return tree[node]=update(idx, value, l, mid, node*2)+update(idx, value, mid+1, r, node*2+1);
		}

		int query(int r) {
			return query(1, r, 0, n-1, 1);
		}

		int query(int bl, int br, int l, int r, int node) {
			if(l>br || r<bl) return 0;
			if(l>=bl && r<=br) return tree[node];
			int mid=(l+r)>>>1;
			return query(bl, br, l, mid, node*2)+query(bl, br, mid+1, r, node*2+1);
		}

	}

}
