import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
				BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {
			int n=Integer.parseInt(br.readLine());
			
			for(int i=0; i<n; i++) {
				int[] fromTo=Arrays.stream(br.readLine().split("\\s"))
						.mapToInt(s->Integer.parseInt(s))
						.toArray();
				int csize=Integer.parseInt(br.readLine());
				int[][] circles=new int[csize][];
				for(int j=0; j<csize; j++) {
					circles[j]=Arrays.stream(br.readLine().split("\\s+"))
							.mapToInt(s->Integer.parseInt(s))
							.toArray();
				}
				bw.write(String.valueOf(new Main().resolve(fromTo, circles)));
				if(i<n-1) bw.write("\n");
			}
			bw.flush();
		}
	}
	
	/*
	 * from 만 포함하는 원의 개수 + to만 포함하는 원의 개수
	 */
	int resolve(int[] fromTo, int[][] circles) {
		int ret=0;
		int fromx=fromTo[0], fromy=fromTo[1], tox=fromTo[2], toy=fromTo[3];
		for(int[] c: circles) {
			boolean fromFlag=contains(fromy, fromx, c[1], c[0], c[2]);
			boolean toFlag=contains(toy, tox, c[1], c[0], c[2]);
			if(fromFlag==toFlag) continue;
			ret++;
		}
		return ret;
	}
	boolean contains(int py, int px, int cy, int cx, int r) {
		// D: [cy, cx] -> [py, px], D가 r보다 작으면 포함.
		int ygap=Math.abs(py-cy);
		int xgap=Math.abs(px-cx);
		int D=ygap*ygap+xgap*xgap;
		return D<r*r;
	}
}
	