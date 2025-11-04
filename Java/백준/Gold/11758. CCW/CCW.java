import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			StringTokenizer st=new StringTokenizer(br.readLine());
			int[] p1=new int[2];
			p1[0]=Integer.parseInt(st.nextToken());
			p1[1]=Integer.parseInt(st.nextToken());

			st=new StringTokenizer(br.readLine());
			int[] p2=new int[2];
			p2[0]=Integer.parseInt(st.nextToken());
			p2[1]=Integer.parseInt(st.nextToken());

			st=new StringTokenizer(br.readLine());
			int[] p3=new int[2];
			p3[0]=Integer.parseInt(st.nextToken());
			p3[1]=Integer.parseInt(st.nextToken());

			bw.write(
				String.valueOf(
					new Main().resolve(
						p1, p2, p3
					)
				)
			);
			bw.flush();
		}
	}

	int resolve(int[] p1, int[] p2, int[] p3) {
		int a=(p3[1]-p1[1])*(p2[0]-p1[0]);
		int b=(p2[1]-p1[1])*(p3[0]-p1[0]);
		return a>b ? 1 : a<b ? -1 : 0;
	}

}
