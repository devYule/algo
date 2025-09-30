import java.math.*;
import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			int n=Integer.parseInt(br.readLine());
			long[] add=Arrays.stream(br.readLine().split("\\s")).mapToLong(Long::parseLong).toArray();

			bw.write(
				String.valueOf(
					new Main().resolve(
						n, add
					)
				)
			);
			bw.flush();
		}
	}

	int n;
	String resolve(int n, long[] add) {
		this.n=n;
		BigInteger ret=BigInteger.ZERO;
		int start=-1;
		long max=-1;
		for(int i=0; i<n; i++) {
			if(add[i]>max) {
				start=i;
				max=add[i];
			}
		}
		boolean[] vis=new boolean[n];
		int walk=start;
		while(true) {
			int cur=walk;
			int prev=prev(walk);
			int next=next(walk);

			if(add[cur]!=0) {
				ret=ret.add(BigInteger.valueOf(add[cur]));
			} else if(!vis[prev] && !vis[next]) {
				ret=ret.add(BigInteger.ONE);
				vis[cur]=true;
			}

			walk=next(walk);
			if(walk==start) break;
		}
		return ret.toString();
	}
	int next(int idx) {
		int ret=idx+1;
		if(ret==n) return 0;
		return ret;
	}
	int prev(int idx) {
		int ret=idx-1;
		if(ret==-1) return n-1;
		return ret;
	}
}
