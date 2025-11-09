import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			StringTokenizer st=new StringTokenizer(br.readLine());

			int n=Integer.parseInt(st.nextToken());
			int m=Integer.parseInt(st.nextToken());

			int[][] wv=new int[n][2];
			for(int i=0; i<n; i++) {
				st=new StringTokenizer(br.readLine());
				wv[i][0]=Integer.parseInt(st.nextToken());
				wv[i][1]=Integer.parseInt(st.nextToken());
			}

			int[] packs=new int[m];
			for(int i=0; i<m; i++) packs[i]=Integer.parseInt(br.readLine());

			bw.write(
				String.valueOf(
					new Main().resolve(
						n, m, wv, packs
					)
				)
			);
			bw.flush();
		}
	}

	long resolve(int n, int m, int[][] wv, int[] packs) {
		Arrays.sort(packs);
		Arrays.sort(wv, (a, b) -> a[0]-b[0]);
		int[] sortedValue=new int[n];
		for(int i=0; i<n; i++) {
			sortedValue[i]=wv[i][1];
		}

		Seg seg=new Seg(sortedValue);

		long ret=0;
		boolean[] used=new boolean[n];
		for(int p: packs) {
			int lo=0, hi=n-1;
			while(lo<=hi) {
				int mid=(lo+hi)>>>1;
				if(wv[mid][0]<=p) lo=mid+1;
				else hi=mid-1;
			}
			if(hi<0) continue;
			int idx=seg.query(0, hi);
			if(used[idx]) continue;
			used[idx]=true;
			ret+=sortedValue[idx];
			seg.update(idx, -1);
		}

		return ret;
	}

	class Seg {
		int o[], tree[], n;
		Seg(int[] o) {
			this.o=o;
			this.n=o.length;
			this.tree=new int[4*n];
			init(0, n-1, 1);
		}

		int init(int l, int r, int node) {
			if(l==r) return tree[node]=l;
			int mid=(l+r)>>>1;
			int lr=init(l, mid, node*2);
			int rr=init(mid+1, r, node*2+1);
			if(o[lr]>o[rr]) return tree[node]=lr;
			return tree[node]=rr;
		}

		void update(int idx, int value) {
			update(idx, value, 0, n-1, 1);
		}

		int update(int idx, int value, int l, int r, int node) {
			if(r<idx || l>idx) return tree[node];
			if(l==r) {
				o[l]=value;
				return tree[node]=l;
			}
			int mid=(l+r)>>>1;
			int lr=update(idx, value, l, mid, node*2);
			int rr=update(idx, value, mid+1, r, node*2+1);

			return tree[node]=o[lr]>o[rr] ? lr : rr;
		}

		int query(int l, int r) {
			return query(l, r, 0, n-1, 1);
		}

		int query(int bl, int br, int l, int r, int node) {
			if(l>br || r<bl) return -1;
			if(l>=bl && r<=br) return tree[node];
			int mid=(l+r)>>>1;
			int lr=query(bl, br, l, mid, node*2);
			int rr=query(bl, br, mid+1, r, node*2+1);
			if(lr==-1) return rr;
			if(rr==-1) return lr;
			return o[lr]>o[rr] ? lr : rr;
		}
	}


}
