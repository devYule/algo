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
		Fenwick fw=new Fenwick(n);

		for(int i=1; i<=n; i++) {
			fw.add(i, nums[i-1]);
			if(i+1<=n) fw.add(i+1, -nums[i-1]);
		}

		List<Long> ret=new ArrayList<>();
		for(int[] q: query) {
			if(q[0]==1) {
				int from=q[1];
				int to=q[2];
				int value=q[3];
				fw.add(from, value);
				if(to+1<=n) fw.add(to+1, -value);
			} else {
				int idx=q[1];
				ret.add(fw.get(idx));
			}
		}
		return ret.stream().map(String::valueOf).collect(java.util.stream.Collectors.joining("\n"));
	}

	static class Fenwick {
		long[] tree;
		int n;
		Fenwick(int n) {
			this.n=n;
			tree=new long[n+1];
		}

		void add(int i, int value) {
			for(; i<=n; i+=i&-i) tree[i]+=value;
		}
		long get(int i) {
			long ret=0;
			for(; i>0; i-=i&-i) ret+=tree[i];
			return ret;
		}
	}

}
