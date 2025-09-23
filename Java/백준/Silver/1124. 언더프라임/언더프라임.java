import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			String[] split=br.readLine().split("\\s");

			bw.write(
				String.valueOf(
					new Main().resolve(
						Integer.parseInt(split[0]), Integer.parseInt(split[1])
					)
				)
			);
			bw.flush();
		}
	}

	/*
		a<= x <=b
		x중 소인수의 길이가 소수개인 수의 개수
	*/

	int[] prime;
	int[] divs;
	int resolve(int a, int b) {
		this.prime=new int[b+1];
		for(int i=1; i<=b; i++) prime[i]=i;
		setPrime();

		this.divs=new int[b+1];
		Arrays.fill(divs, -1);
		for(int i=b; i>=a; i--) {
			if(divs[i]!=-1) continue;
			setDivs(i);
		}

		int ret=0;
		for(int i=a; i<=b; i++) {
			if(divs[i]==1) continue;
			if(prime[divs[i]]==divs[i]) ret++;
		}
		return ret;
	}

	int setDivs(int cur) {
		if(cur==1) return 0;
		if(divs[cur]!=-1) return divs[cur];
		int c=cur;
		int p=prime[c];
		int e=0;
		while(c%p==0) { e++; c/=p; }
		return divs[cur]=e+setDivs(c);
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
