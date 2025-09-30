import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			int n=Integer.parseInt(br.readLine());
			int[][] a=new int[n][], b=new int[n][];
			for(int i=0; i<n; i++) {
				a[i]=Arrays.stream(br.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();
			}
			for(int i=0; i<n; i++) {
				b[i]=Arrays.stream(br.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();
			}

			bw.write(
				String.valueOf(
					new Main().resolve(
						n, a, b
					)
				)
			);
			bw.flush();
		}
	}

	int resolve(int n, int[][] a, int[][] b) {
		int level=0;
		int[] ret=new int[6];
		while(level<n) {
			int max=level+1;
			int stage=0;
			while(stage<max) {
				int target=b[level][stage];

				int a0=a[level][stage];
				int a1=a[n-1-stage][level-stage];
				int a2=a[(n-1-level)+stage][n-1-level];
				int r0=a[level][level-stage];
				int r1=a[n-1-level+stage][stage];
				int r2=a[n-1-stage][n-1-level];

				ret[0]+=target!=a0 ? 1 : 0;
				ret[1]+=target!=a1 ? 1 : 0;
				ret[2]+=target!=a2 ? 1 : 0;
				ret[3]+=target!=r0 ? 1 : 0;
				ret[4]+=target!=r1 ? 1 : 0;
				ret[5]+=target!=r2 ? 1 : 0;

				stage++;
			}
			level++;
		}
		
		return Arrays.stream(ret).min().orElse(0);
	}

}
