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

	int[] prime;

	String resolve(int n) {
		this.prime=new int[n+1];
		for(int i=1; i<prime.length; i++) prime[i]=i;
		setPrime();

		List<Integer> ret=new ArrayList<>();
		find(n, ret);
		if(ret.size()==4) return ret.stream().map(String::valueOf).collect(java.util.stream.Collectors.joining(" "));
		return "-1";
	}

	boolean find(int rest, List<Integer> ret) {
		if(ret.size()==4) return rest==0;
		for(int i=rest; i>=2; i--) {
			if(prime[i]!=i) continue;
			ret.add(i);
			boolean s=find(rest-i, ret);
			if(s) return s;
			ret.remove(ret.size()-1);
		}
		return false;
	}

	void setPrime() {
		int sqrt=(int)Math.sqrt(prime.length);
		for(int i=2; i<=sqrt; i++) {
			if(prime[i]!=i) continue;
			for(int j=i*i; j<prime.length; j+=i) {
				if(prime[j]==j) prime[j]=i;
			}
		}
	}

}
