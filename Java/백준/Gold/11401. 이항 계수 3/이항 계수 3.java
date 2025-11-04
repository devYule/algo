import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			StringTokenizer st=new StringTokenizer(br.readLine());

			bw.write(
				String.valueOf(
					new Main().resolve(
						Integer.parseInt(st.nextToken()),
						Integer.parseInt(st.nextToken())
					)
				)
			);
			bw.flush();
		}
	}

	final int MOD=1_000_000_007;

	int resolve(int n, int k) {
		int top=1;
		int bot=1;
		for(long i=n; i>n-k; i--) {
			top=(int)((top*i)%MOD);
		}
		for(long i=k; i>1; i--) {
			bot=(int)((bot*i)%MOD);
		}
		long ib=modPow(bot, (long)MOD-2);
		return (int)((top*ib)%MOD);
	}

	long modPow(long a, long e) {
		long r=1;
		a%=MOD;
		while(e>0) {
			if(e%2==1) r=(r*a)%MOD;
			a=(a*a)%MOD;
			e>>=1;
		}
		return r;
	}

}
