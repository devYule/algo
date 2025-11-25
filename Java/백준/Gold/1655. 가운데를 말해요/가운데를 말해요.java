import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			int n=Integer.parseInt(br.readLine());
			int[] nums=new int[n];
			for(int i=0; i<n; i++) nums[i]=Integer.parseInt(br.readLine());

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

	String resolve(int n, int[] nums) {
		int FIX=10000;
		for(int i=0; i<n; i++) nums[i]+=FIX;

		StringBuilder sb=new StringBuilder();
		int count=0;
		Seg seg=new Seg();
		for(int num: nums) {
			seg.update(num, 1);
			if(count!=0) sb.append("\n");
			count++;
			if(count==1) sb.append(String.valueOf(num-FIX));
			else if(count==2) sb.append(String.valueOf(Math.min(nums[0], num)-FIX));
			else {
				int target=count/2+1;
				int a=bin(target, seg);
				if(count%2==0) {
					int b=bin(target-1, seg);
					sb.append(String.valueOf(Math.min(a, b)-FIX));
				} else sb.append(String.valueOf(a-FIX));
			}
		}
		return sb.toString();
	}

	int bin(int target, Seg seg) {
		int lo=0, hi=20000;
		while(lo<=hi) {
			int mid=(lo+hi)>>>1;
			int find=seg.query(mid);
			if(find>=target) hi=mid-1;
			else lo=mid+1;
		}
		return lo;
	}

	class Seg {
		int n=20001;
		int[] tree=new int[n*4];

		void update(int idx, int value) {
			update(idx, value, 0, n-1, 1);
		}

		int update(int idx, int value, int l, int r, int node) {
			if(l>idx || r<idx) return tree[node];
			if(l==r) return tree[node]+=value;
			int mid=(l+r)>>>1;
			return tree[node]=update(idx, value, l, mid, node*2)+update(idx, value, mid+1, r, node*2+1);
		}

		int query(int t) {
			return query(0, t, 0, n-1, 1);
		}

		int query(int bl, int br, int l, int r, int node) {
			if(l>br || r<bl) return 0;
			if(l>=bl && r<=br) return tree[node];
			int mid=(l+r)>>>1;
			return query(bl, br, l, mid, node*2)+query(bl, br, mid+1, r, node*2+1);
		}

	}

}
