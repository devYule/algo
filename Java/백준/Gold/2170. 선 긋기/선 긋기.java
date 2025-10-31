import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			int n=Integer.parseInt(br.readLine());
			int[][] p=new int[n][2];
			for(int i=0; i<n; i++) {
				StringTokenizer st=new StringTokenizer(br.readLine());

				p[i][0]=Integer.parseInt(st.nextToken());
				p[i][1]=Integer.parseInt(st.nextToken());
			}

			bw.write(
				String.valueOf(
					new Main().resolve(
						n, p
					)
				)
			);
			bw.flush();
		}
	}

	int resolve(int n, int[][] p) {
		Arrays.sort(p, (a, b) -> a[0]==b[0] ? Integer.compare(a[1], b[1]) : Integer.compare(a[0], b[0]));

		int ret=0;
		int ls=p[0][0];
		int rs=p[0][1];
		for(int i=1; i<n; i++) {
			int[] c=p[i];
			if(c[0]>rs) {
				ret+=rs-ls;
				ls=c[0];
				rs=c[1];
			} else if(c[1]>rs) {
				rs=c[1];
			}
		}
		return ret+(rs-ls);
	}

}
