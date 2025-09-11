import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			String[] split=br.readLine().split("\\s+");

			int y1=Integer.parseInt(split[0]);
			int x1=Integer.parseInt(split[1]);
			int y2=Integer.parseInt(split[2]);
			int x2=Integer.parseInt(split[3]);

			bw.write(
				String.valueOf(
					new Main().resolve(y1, x1, y2, x2)
				)
			);
			bw.flush();
		}
	}

	/*
		정사각형
		acc=1, 2번 사용 후 acc++
		[max(abs(y1), abs(y2))*2+1][eq]
		우,상,좌,하
	*/
	final int[] rg={0, -1, 0, 1};
	final int[] cg={1, 0, -1, 0};
	String resolve(int y1, int x1, int y2, int x2) {
		final int fixY=-y1;
		final int fixX=-x1;
		int lasty=y2+fixY+1;
		int lastx=x2+fixX+1;
		int[][] ret=new int[lasty][lastx];

		int maxy=Math.max(Math.abs(y1), Math.abs(y2))*2+1;
		int maxx=Math.max(Math.abs(x1), Math.abs(x2))*2+1;
		int maxall=Math.max(maxy, maxx);
		int lastNumber=maxall*maxall;

		int changePerMove=1;
		int moved=0;
		int maxUse=2;
		int used=0;
		int dir=0;

		int cy=0, cx=0;
		int maxInTable=0;
		for(int i=1; i<=lastNumber; i++) {
			if(cy>=y1 && cy<=y2 && cx>=x1 && cx<=x2) {
				ret[cy+fixY][cx+fixX]=i;
				maxInTable=Math.max(maxInTable, i);
			}
			// 움직임
			cy+=rg[dir];
			cx+=cg[dir];
			// 움직임 사용 횟수 증가
			moved++;
			used++;

			if(moved==changePerMove) {
				dir=(dir+1)%4;
				moved=0;
			}
			if(used==maxUse) {
				used=0;
				changePerMove++;
				maxUse=changePerMove*2;
			}
		}
		int place=2;
		int mul=10;
		while(true) {
			if(maxInTable/mul==0) break;
			place++;
			mul*=10;
		}
		StringBuilder sb=new StringBuilder();
		for(int i=0; i<lasty; i++) {
			for(int j=0; j<lastx; j++) {
				int plc=place;
				if(j==0) plc--;
				sb.append(String.format("%"+plc+"d", ret[i][j]));
			}
			sb.append("\n");
		}

		return sb.toString();
	}

}