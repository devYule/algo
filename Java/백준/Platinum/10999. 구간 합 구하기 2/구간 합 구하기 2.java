import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			StringTokenizer st=new StringTokenizer(br.readLine());

			int n=Integer.parseInt(st.nextToken());
			int m=Integer.parseInt(st.nextToken());
			int k=Integer.parseInt(st.nextToken());

			long[] nums=new long[n];
			for(int i=0; i<n; i++) nums[i]=Long.parseLong(br.readLine());

			long[][] query=new long[m+k][];
			for(int i=0; i<m+k; i++) {
				st=new StringTokenizer(br.readLine());
				long flag=Long.parseLong(st.nextToken());
				if(flag==1) {
					query[i]=new long[4];
					query[i][0]=flag;
					query[i][1]=Long.parseLong(st.nextToken());
					query[i][2]=Long.parseLong(st.nextToken());
					query[i][3]=Long.parseLong(st.nextToken());
				} else {
					query[i]=new long[3];
					query[i][0]=flag;
					query[i][1]=Long.parseLong(st.nextToken());
					query[i][2]=Long.parseLong(st.nextToken());
				}
			}

			bw.write(
				String.valueOf(
					new Main().resolve(
						n, m, k, nums, query
					)
				)
			);
			bw.flush();
		}
	}

	String resolve(int n, int m, int k, long[] nums, long[][] query) {
		Segment seg=new Segment(nums);
		List<Long> ret=new ArrayList<>();
		for(long[] q: query) {
			if(q[0]==1) {
				seg.update((int)q[1]-1, (int)q[2]-1, q[3]);
			} else {
				ret.add(seg.query((int)q[1]-1, (int)q[2]-1));
			}
		}
		return ret.stream().map(String::valueOf).collect(java.util.stream.Collectors.joining("\n"));
	}

	static class Segment {
		long[] tree, lazy, o;
		int n;
		Segment(long[] o) {
			this.o=o;
			this.n=o.length;
			this.tree=new long[n*4];
			this.lazy=new long[n*4];

			init(0, n-1, 1);
		}

		long init(int l, int r, int node) {
			if(l==r) return tree[node]=o[l];
			int mid=(l+r)>>>1;
			long lr=init(l, mid, node*2);
			long rr=init(mid+1, r, node*2+1);
			return tree[node]=lr+rr;
		}

		void update(int l, int r, long value) {
			update(l, r, value, 0, n-1, 1);
		}

		long update(int bl, int br, long value, int l, int r, int node) {
			push(l, r, node);
			if(l>br || r<bl) return tree[node];
			if(l>=bl && r<=br) {
				lazy[node]+=value;
				push(l, r, node);
				return tree[node];
			}
			int mid=(l+r)>>>1;
			long lr=update(bl, br, value, l, mid, node*2);
			long rr=update(bl, br, value, mid+1, r, node*2+1);
			return tree[node]=lr+rr;
		}

		long query(int l, int r) {
			return query(l, r, 0, n-1, 1);
		}

		long query(int bl, int br, int l, int r, int node) {
			if(l>br || r<bl) return 0L;
			push(l, r, node);
			if(l>=bl && r<=br) return tree[node];
			int mid=(l+r)>>>1;
			long lr=query(bl, br, l, mid, node*2);
			long rr=query(bl, br, mid+1, r, node*2+1);
			return lr+rr;
		}

		void push(int l, int r, int node) {
			if(lazy[node]==0) return;
			tree[node]+=(r-l+1)*lazy[node];
			if(l!=r) {
				lazy[node*2]+=lazy[node];
				lazy[node*2+1]+=lazy[node];
			}
			lazy[node]=0;
		}

	}

}
