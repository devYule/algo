import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			int n=Integer.parseInt(br.readLine());
			int[] nums=new int[n];
			StringTokenizer st=new StringTokenizer(br.readLine());
			for(int i=0; i<n; i++) nums[i]=Integer.parseInt(st.nextToken());

			int m=Integer.parseInt(br.readLine());
			int[][] query=new int[m][];
			for(int i=0; i<m; i++) {
				st=new StringTokenizer(br.readLine());
				int flag=Integer.parseInt(st.nextToken());
				if(flag==1) {
					query[i]=new int[4];
					query[i][0]=flag;
					query[i][1]=Integer.parseInt(st.nextToken());
					query[i][2]=Integer.parseInt(st.nextToken());
					query[i][3]=Integer.parseInt(st.nextToken());
				} else {
					query[i]=new int[2];
					query[i][0]=flag;
					query[i][1]=Integer.parseInt(st.nextToken());
				}
			}

			bw.write(
				String.valueOf(
					new Main().resolve(
						n, m, nums, query
					)
				)
			);
			bw.flush();
		}
	}

	String resolve(int n, int m, int[] nums, int[][] query) {
		Segment seg=new Segment(nums);

		List<Long> ret=new ArrayList<>();
		for(int[] q: query) {
			if(q[0]==1) {
				seg.update(q[1]-1, q[2]-1, q[3]);
			} else {
				ret.add(seg.get(q[1]-1));
			}
		}
		return ret.stream().map(String::valueOf).collect(java.util.stream.Collectors.joining("\n"));
	}

	static class Segment {
		long[] lazy;
		int[] o;
		int n;
		Segment(int[] o) {
			this.o=o;
			this.n=o.length;
			int tl=0;
			int p=0;
			while(true) {
				int pow=(int)Math.pow(2, p);
				tl+=n;
				if(pow>n) break;
				p++;
			}
			this.lazy=new long[tl];
		}

		void update(int l, int r, int value) {
			update(l, r, value, 0, n-1, 1);
		}

		void update(int bl, int br, int value, int l, int r, int nd) {
			if(r<bl || l>br) return;
			if(l>=bl && r<=br) {
				lazy[nd]+=value;
				return;
			}

			int mid=(l+r)>>>1;
			update(bl, br, value, l, mid, nd*2);
			update(bl, br, value, mid+1, r, nd*2+1);
		}

		long get(int idx) {
			return get(idx, 0, n-1, 1, 0L);
		}

		long get(int idx, int l, int r, int nd, long acc) {
			acc+=lazy[nd];
			if(l==r) return o[idx]+acc;
			int mid=(l+r)>>>1;
			if(idx<=mid) return get(idx, l, mid, nd*2, acc);
			else return get(idx, mid+1, r, nd*2+1, acc);
		}
	}

}
