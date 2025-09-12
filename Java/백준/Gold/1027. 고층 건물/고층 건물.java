import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			int n=Integer.parseInt(br.readLine());


			bw.write(
				String.valueOf(
					new Main().resolve(
						n, 
						Arrays.stream(br.readLine().split("\\s+"))
							.mapToInt(a->Integer.parseInt(a)).toArray()
					)
				)
			);
	
			bw.flush();
		}
	}

	int resolve(int n, int[] buildings) {
		/*
			(min(A, B)+abs(A-B)/사이건물개수+1)<=C 를 만족하는 사이 건물이 하나라도 있으면, 볼 수 없음.

			return: X에서 볼 수 있는 건물의 최대수.
		*/

		int ret=0;
		for(int i=0; i<n; i++) {
			int cnt=0;
			for(int j=0; j<n; j++) {
				if(i==j) continue;
				boolean can=true;
				int x1=Math.min(i, j);
				int x3=Math.max(i, j);
				int y1=buildings[x1];
				int y3=buildings[x3];
				
				for(int k=x1+1; k<x3; k++) {
					int x2=k;
					int y2=buildings[k];
					if(((long)y3-y1)*((long)x2-x1)<=((long)y2-y1)*((long)x3-x1)) { can=false; break; }
				}
				if(can) cnt++;
			}
			ret=Math.max(ret, cnt);
		}
		return ret;
	}

}