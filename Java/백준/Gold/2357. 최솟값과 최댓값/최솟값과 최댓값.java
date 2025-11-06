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

			for(int i=0; i<n; i++) nums[i]=Integer.parseInt(br.readLine());

			int[][] q=new int[m][2];
			for(int i=0; i<m; i++) {
				st=new StringTokenizer(br.readLine());
				q[i][0]=Integer.parseInt(st.nextToken());
				q[i][1]=Integer.parseInt(st.nextToken());
			}

			bw.write(
				String.valueOf(
					new Main().resolve(
						n, m, nums, q
					)
				)
			);
			bw.flush();
		}
	}

	String resolve(int n, int m, int[] nums, int[][] qr) {
		MinSeg min=new MinSeg(nums);
		MaxSeg max=new MaxSeg(nums);

		StringBuilder sb=new StringBuilder();
		for(int[] q: qr) {
			sb.append(min.query(q[0]-1, q[1]-1) + " " + max.query(q[0]-1, q[1]-1) + "\n");
		}
		return sb.toString().trim();
	}

	class MinSeg {
		int o[], tree[], n;

		MinSeg(int[] o) {
			this.n=o.length;
			this.o=o;
			this.tree=new int[n*4];
			init(0, n-1, 1);
		}

		int init(int l, int r, int node) {
			if(l==r) return tree[node]=o[l];
			int mid=(l+r)>>>1;
			return tree[node]=Math.min(init(l, mid, node*2), init(mid+1, r, node*2+1));
		}

		int query(int l, int r) {
			return query(l, r, 0, n-1, 1);
		}

		int query(int bl, int br, int l, int r, int node) {
			if(l>br || r<bl) return (int)1e9;
			if(l>=bl && r<=br) return tree[node];
			int mid=(l+r)>>>1;
			return Math.min(query(bl, br, l, mid, node*2), query(bl, br, mid+1, r, node*2+1));
		}

	}

	class MaxSeg {
		int o[], tree[], n;

		MaxSeg(int[] o) {
			this.n=o.length;
			this.o=o;
			this.tree=new int[n*4];
			init(0, n-1, 1);
		}

		int init(int l, int r, int node) {
			if(l==r) return tree[node]=o[l];
			int mid=(l+r)>>>1;
			return tree[node]=Math.max(init(l, mid, node*2), init(mid+1, r, node*2+1));
		}

		int query(int l, int r) {
			return query(l, r, 0, n-1, 1);
		}

		int query(int bl, int br, int l, int r, int node) {
			if(l>br || r<bl) return -1;
			if(l>=bl && r<=br) return tree[node];
			int mid=(l+r)>>>1;
			return Math.max(query(bl, br, l, mid, node*2), query(bl, br, mid+1, r, node*2+1));
		}
	}

}
