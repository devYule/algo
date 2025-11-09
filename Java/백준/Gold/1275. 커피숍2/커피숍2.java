import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			StringTokenizer st=new StringTokenizer(br.readLine());

			int n=Integer.parseInt(st.nextToken());
			int m=Integer.parseInt(st.nextToken());

			int[] nums=new int[n];
			st=new StringTokenizer(br.readLine());
			for(int i=0; i<n; i++) {
				nums[i]=Integer.parseInt(st.nextToken());
			}

			int[][] Q=new int[m][4];
			for(int i=0; i<m; i++) {
				st=new StringTokenizer(br.readLine());
				Q[i][0]=Integer.parseInt(st.nextToken());
				Q[i][1]=Integer.parseInt(st.nextToken());
				Q[i][2]=Integer.parseInt(st.nextToken());
				Q[i][3]=Integer.parseInt(st.nextToken());
			}

			bw.write(
				String.valueOf(
					new Main().resolve(
						n, m, nums, Q
					)
				)
			);
			bw.flush();
		}
	}

	String resolve(int n, int m, int[] nums, int[][] Q) {
		Seg seg=new Seg(nums);
		StringBuilder sb=new StringBuilder();
		for(int i=0; i<m; i++) { 
			if(i!=0) sb.append("\n");
			int[] q=Q[i];
			int x=Math.min(q[0], q[1]);
			int y=Math.max(q[0], q[1]);
			sb.append(seg.query(x, y));
			seg.update(q[2], q[3]);
		}

		return sb.toString();
	}


	class Seg {
		long tree[]; 
		int o[], n;
		Seg(int[] o) {
			this.o=o;
			this.n=o.length;
			this.tree=new long[4*n];
			init(0, n-1, 1);
		}

		long init(int l, int r, int node) {
			if(l==r) return tree[node]=o[l];
			int mid=(l+r)>>>1;
			return tree[node]=
				init(l, mid, node*2) +
				init(mid+1, r, node*2+1);
		}

		void update(int idx, int value) {
			update(idx-1, value, 0, n-1, 1);
		} 

		long update(int idx, int value, int l, int r, int node) {
			if(r<idx || l>idx) return tree[node];
			if(l==r) return tree[node]=value;
			int mid=(l+r)>>>1;
			return tree[node]=
				update(idx, value, l, mid, node*2) +
				update(idx, value, mid+1, r, node*2+1);
		}

		long query(int l, int r) {
			return query(l-1, r-1, 0, n-1, 1);
		}

		long query(int bl, int br, int l, int r, int node) {
			if(l>br || r<bl) return 0;
			if(l>=bl && r<=br) return tree[node];
			int mid=(l+r)>>>1;
			return query(bl, br, l, mid, node*2) +
				query(bl, br, mid+1, r, node*2+1);
		}
	}

}
