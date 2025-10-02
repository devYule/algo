import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			StringTokenizer st=new StringTokenizer(br.readLine());
			int n=Integer.parseInt(st.nextToken());
			int m=Integer.parseInt(st.nextToken());
			long p=Long.parseLong(st.nextToken());
			long[][] each=new long[n][m];
			for(int i=0; i<n; i++) {
				st=new StringTokenizer(br.readLine());
				for(int j=0; j<m; j++) {
					each[i][j]=Long.parseLong(st.nextToken());
				}
			}

			bw.write(
				String.valueOf(
					new Main().resolve(
						n, m, p, each
					)
				)
			);
			bw.flush();
		}
	}

	int resolve(int n, int m, long p, long[][] each) {

		for(long[] e: each) {
			Arrays.sort(e);
			int chance=0;
			int from=m;
			for(int i=0; i<m; i++) {
				if(e[i]==-1) chance++;
				else {
					from=i;
					break;
				}
			}

			for(int i=from; i<m; i++) {
				if(e[i]>p && chance>0) {
					chance--;
					p*=2;
				}
				if(e[i]>p) return 0;
				p+=e[i];
			}
			while(chance>0) { chance--; p*=2; }
		}
		return 1;
	}
}
