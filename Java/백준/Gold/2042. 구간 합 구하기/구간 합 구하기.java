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

			long[] initn=new long[n];
			for(int i=0; i<n; i++) initn[i]=Long.parseLong(br.readLine());

			long[][] query=new long[m+k][3];
			for(int i=0; i<m+k; i++) {
				st=new StringTokenizer(br.readLine());
				query[i][0]=Long.parseLong(st.nextToken());
				query[i][1]=Long.parseLong(st.nextToken());
				query[i][2]=Long.parseLong(st.nextToken());
			}

			bw.write(
				String.valueOf(
					new Main().resolve(
						n, m, k, initn, query
					)
				)
			);
			bw.flush();
		}
	}


	String resolve(int n, int m, int k, long[] nums, long[][] query) {
		n++;
		Fenwick fw=new Fenwick(n);
		for(int i=0; i<nums.length; i++) fw.add(i+1, nums[i]);

		List<Long> ret=new ArrayList<>();
		for(long[] q: query) {
			long flag=q[0];
			if(flag==1L) {
				fw.set((int)q[1], q[2]);
			} else {
				ret.add(fw.range((int)q[1], (int)q[2]));
			}
		}
		return ret.stream().map(String::valueOf).collect(java.util.stream.Collectors.joining("\n"));
	}


	static class Fenwick {
		int n;
		long[] tree;
		Fenwick(int n) {
			this.n=n;
			this.tree=new long[n+1];
		}

		void add(int i, long value) {
			i++;
			for(; i<=n; i+=i&-i) tree[i]+=value;
		}

		long prefix(int i) {
			i++;
			long ret=0;
			for(; i>0; i-=i&-i) ret+=tree[i];
			return ret;
		}

		long range(int l, int r) {
			return prefix(r)-prefix(l-1);
		}

		void set(int i, long value) {
			long orin=range(i, i);
			long gap=value-orin;
			add(i, gap);
		}
	}
}
