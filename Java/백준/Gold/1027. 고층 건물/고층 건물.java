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
			int A=buildings[i];
			int cnt=0;
			for(int j=0; j<n; j++) {
				if(i==j) continue;
				int B=buildings[j];
				boolean can=true;
				int ti=Math.min(i, j);
				int tj=Math.max(i, j);
				double acc=Math.abs(A-B)/(double)(tj-ti);
				int lower=A<B ? A : B;
				int loweri=A<B ? i : j;
				for(int k=ti+1; k<tj; k++) {
					if(lower+Math.abs(k-loweri)*acc<=buildings[k]) { can=false; break; }
				}
				if(can) cnt++;
			}
			ret=Math.max(ret, cnt);
		}
		return ret;
	}

}