import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			int line=Integer.parseInt(br.readLine());
			int[][] nums=new int[line-1][];
			for(int i=0; i<line-1; i++) {
				nums[i]=Arrays.stream(br.readLine().split("\\s+"))
					.mapToInt(Integer::parseInt)
					.toArray();
			}


			bw.write(
				String.valueOf(
					new Main().resolve(
						line, nums
					)
				)
			);
	
			bw.flush();
		}
	}

	@SuppressWarnings("unchecked")
	String resolve(int n, int[][] nums) {
		int m=n-1;
		int max=0;
		int maxi=-1;
		long muls=1;
		List<int[]>[] adj;
		adj=new ArrayList[n];
		for(int i=0; i<n; i++) adj[i]=new ArrayList<>();

		for(int i=0; i<m; i++) {
			int a=nums[i][0], b=nums[i][1], ad=nums[i][2], bd=nums[i][3];

			adj[a].add(new int[] {ad, bd, b});
			adj[b].add(new int[] {bd, ad, a});

			int candi=ad>bd ? a : b;
			int cand=Math.max(ad, bd);

			muls*=ad*bd;

			if(max<cand) {
				max=cand;
				maxi=candi;
			}
		}
		long[] dist=new long[n];
		dist[maxi]=muls;
		Queue<Integer>q=new LinkedList<>();
		q.add(maxi);
		while(!q.isEmpty()) {
			int cur=q.poll();
			for(int[] nexts: adj[cur]) {
				if(dist[nexts[2]]!=0) continue;
				dist[nexts[2]]=(dist[cur]/nexts[0])*nexts[1];
				q.add(nexts[2]);
			}
		}

		long gcd=dist[0];
		for(int i=1; i<n; i++) {
			gcd=java.math.BigInteger.valueOf(gcd).gcd(java.math.BigInteger.valueOf(dist[i])).longValue();
		}
		for(int i=0; i<n; i++) dist[i]/=gcd;

		return Arrays.stream(dist).boxed().map(String::valueOf).collect(java.util.stream.Collectors.joining(" "));
	}
}