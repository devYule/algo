import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			String[] split=br.readLine().split("\\s");
			int n=Integer.parseInt(split[2]);
			String[] mv=new String[n];
			for(int i=0; i<n; i++) {
				mv[i]=br.readLine();
			}

			bw.write(
				String.valueOf(
					new Main().resolve(
						split[0], split[1], n, mv
					)
				)
			);
			bw.flush();
		}
	}

	Map<String, int[]> go;
	int[][] map;
	int ky, kx, sy, sx;
	String resolve(String kingLocation, String stoneLocation, int n, String[] mv) {
		init(kingLocation, stoneLocation);

		for(String m: mv) {
			int[] next=go.get(m);
			int nky=ky+next[0];
			int nkx=kx+next[1];
			int nsy=sy;
			int nsx=sx;
			if(nky==nsy && nkx==nsx) {
				nsy+=next[0]; nsx+=next[1];
			}
			if(!valid(nky, nkx) || !valid(nsy, nsx)) continue;
			ky=nky;
			kx=nkx;
			sy=nsy;
			sx=nsx;
		}

		return new StringBuilder().append((char)(kx+'A')).append(ky+1).append("\n").append((char)(sx+'A')).append(sy+1).toString();
	}

	boolean valid(int y, int x) {
		return y>=0 && x>=0 && y<8 && x<8;
	}

	void init(String kl, String sl) {
		this.go=new HashMap<>();
		go.put("R", new int[] {0, 1});
		go.put("L", new int[] {0, -1});
		go.put("B", new int[] {-1, 0});
		go.put("T", new int[] {1, 0});
		go.put("RT", new int[] {1, 1});
		go.put("LT", new int[] {1, -1});
		go.put("RB", new int[] {-1, 1});
		go.put("LB", new int[] {-1, -1});
		this.map=new int[8][8];
		this.kx=kl.charAt(0)-'A';
		this.ky=kl.charAt(1)-'1';
		this.sx=sl.charAt(0)-'A';
		this.sy=sl.charAt(1)-'1';

	}
}
