import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			StringTokenizer st=new StringTokenizer(br.readLine());
			int n=Integer.parseInt(st.nextToken());
			int r=Integer.parseInt(st.nextToken());
			int c=Integer.parseInt(st.nextToken());

			bw.write(
				String.valueOf(
					new Main().resolve(
						n, r, c
					)
				)
			);
			bw.flush();
		}
	}

	int r, c;
	int resolve(int n, int r, int c) {
		this.r=r;
		this.c=c;
		int end=(int)Math.pow(2, n)-1;
		return find(0, 0, end, end, 0);
	}

	int find(int sy, int sx, int ey, int ex, int acc) {
		if(sy==ey && sx==ex) return acc;

		int ymid=(sy+ey)>>>1;
		int xmid=(sx+ex)>>>1;
		int fsize=(ymid-sy+1)*(xmid-sx+1);

		int[][] each=new int[][] {
			{sy, sx, ymid, xmid},
			{sy, xmid+1, ymid, ex},
			{ymid+1, sx, ey, xmid},
			{ymid+1, xmid+1, ey, ex}
		};

		int nacc=acc;
		for(int[] e: each) {
			if(r>=e[0] && c>=e[1] && r<=e[2] && c<=e[3]) {
				return find(e[0], e[1], e[2], e[3], nacc);
			}
			nacc+=fsize;
		}
		throw new RuntimeException();
	}

}
