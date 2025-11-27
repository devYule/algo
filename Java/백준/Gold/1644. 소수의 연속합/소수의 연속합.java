import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			bw.write(
				String.valueOf(
					new Main().resolve(
						Integer.parseInt(br.readLine())
					)
				)
			);
			bw.flush();
		}
	}

	int resolve(int n) {
		int[] prime=new int[n+1];
		for(int i=1; i<=n; i++) prime[i]=i;
		int sqrt=(int)Math.sqrt(n);

		for(int i=2; i<=sqrt; i++) {
			if(prime[i]!=i) continue;
			for(int j=i*i; j<=n; j+=i) {
				if(prime[j]==j) prime[j]=i;
			}
		}

		List<Integer> ps=new ArrayList<>();
		for(int i=2; i<=n; i++) {
			if(prime[i]==i) ps.add(i);
		}

		int l=0, r=0;
		int sum=0;
		int ret=0;
		while(l<ps.size()) {
			while(r<ps.size() && sum<n) sum+=ps.get(r++);
			if(sum==n) ret++;
			sum-=ps.get(l++);
		}
		return ret;
	}

}
