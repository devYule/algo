import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			StringTokenizer st=new StringTokenizer(br.readLine());
			int[] a1=new int[2];
			int[] a2=new int[2];
			a1[0]=Integer.parseInt(st.nextToken());
			a1[1]=Integer.parseInt(st.nextToken());
			a2[0]=Integer.parseInt(st.nextToken());
			a2[1]=Integer.parseInt(st.nextToken());

			st=new StringTokenizer(br.readLine());
			int[] b1=new int[2];
			int[] b2=new int[2];
			b1[0]=Integer.parseInt(st.nextToken());
			b1[1]=Integer.parseInt(st.nextToken());
			b2[0]=Integer.parseInt(st.nextToken());
			b2[1]=Integer.parseInt(st.nextToken());

			bw.write(
				String.valueOf(
					new Main().resolve(
						a1, a2, b1, b2
					)
				)
			);
			bw.flush();
		}
	}

	int resolve(int[] a1, int[] a2, int[] b1, int[] b2) {
		int a=ccw(a1, a2, b1);
		int b=ccw(a1, a2, b2);
		int c=ccw(b1, b2, a1);
		int d=ccw(b1, b2, a2);
		if(a==0 && b==0 && c==0 && d==0) {
			return (
				between(a1[0], a2[0], b1[0], b2[0]) &&
				between(a1[1], a2[1], b1[1], b2[1])
			) ? 1 : 0;
		}
		return a*b<=0 && c*d<=0 ? 1 : 0;
	}

	boolean between(int a1, int a2, int b1, int b2) {
		int amin=Math.min(a1, a2);
		int amax=Math.max(a1, a2);
		int bmin=Math.min(b1, b2);
		int bmax=Math.max(b1, b2);

		return Math.max(amin, bmin)<=Math.min(amax, bmax);
	}

	int ccw(int[] a, int[] b, int[] c) {
		long ccw=((long)(c[1]-a[1]))*(b[0]-a[0])-((long)(b[1]-a[1]))*(c[0]-a[0]);
		return Long.compare(ccw, 0);
	}

}
