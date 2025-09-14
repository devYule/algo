import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			int[] axis=Arrays.stream(br.readLine().split("\\s+"))
				.mapToInt(Integer::parseInt).toArray();

			int y=axis[0];
			int x=axis[1];
			int[][] map=new int[y][];

			for(int i=0; i<y; i++) {
				map[i]=Arrays.stream(br.readLine().split(""))
					.mapToInt(Integer::parseInt).toArray();
			}

			bw.write(
				String.valueOf(
					new Main().resolve(
						y, x, map, Integer.parseInt(br.readLine())
					)
				)
			);
	
			bw.flush();
		}
	}


	int resolve(int y, int x, int[][] map, int k) {
		int ret=0;
		for(int i=0; i<y; i++) {
			int cnt=0;
			for(int j=0; j<x; j++) {
				if(map[i][j]==0) cnt++;
			}
			
			if(cnt<=k && (k-cnt)%2==0) {
				cnt=0;
				for(int r=i; r<y; r++) {
					boolean eq=true;
					for(int c=0; c<x; c++) {
						if(map[i][c]!=map[r][c]) { eq=false; break; }
					}
					if(eq)cnt++;
				}
				ret=Math.max(ret, cnt);
			}
		}
		return ret;
	}

}