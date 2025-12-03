import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			StringTokenizer st=new StringTokenizer(br.readLine());
			int n=Integer.parseInt(st.nextToken());
			int k=Integer.parseInt(st.nextToken());
			int b=Integer.parseInt(st.nextToken());
			int[] brk=new int[b];
			for(int i=0; i<b; i++) {
				brk[i]=Integer.parseInt(br.readLine());
			}

			bw.write(
				String.valueOf(
					new Main().resolve(
						n, k, b, brk
					)
				)
			);
			bw.flush();
		}
	}

	int resolve(int n, int k, int b, int[] brk) {
		int[] B=new int[n+1];
		for(int i=0; i<b; i++) B[brk[i]]=1;

		int ret=n;
		int fix=0;
		int hi=1;
		for(int lo=1; lo<=n; lo++) {
			while(hi<=n && hi-lo<k) fix+=B[hi++];
			if(hi-lo==k) ret=Math.min(ret, fix);
			fix-=B[lo];
		}
		return ret;
	}

}
