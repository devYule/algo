import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			String[] nm=br.readLine().split("\\s");
			int n=Integer.parseInt(nm[0]);
			int m=Integer.parseInt(nm[1]);
			int[][] c=new int[n][];
			for(int i=0; i<n; i++) {
				c[i]=Arrays.stream(br.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();
			}

			bw.write(
				String.valueOf(
					new Main().resolve(
						n, m, c
					)
				)
			);
			bw.flush();
		}
	}

	int resolve(int n, int m, int[][] box) {
		int ret=(int)1e9;
		for(int i=0; i<n; i++) {
			int cnt=0;
			// i=joker
			// 이미 박스 하나에 배정된 색상 번호.
			int[] full=new int[m];

			for(int j=0; j<n; j++) {
				if(j==i) continue;
				int injbox=0;
				int thing=-1;
				for(int k=0; k<m; k++) {
					if(box[j][k]!=0) { injbox++; thing=k; }
				}
				if(injbox==0) continue;
				if(injbox==1) {
					if(full[thing]!=0) cnt++;
					else full[thing]=1;
				} else cnt++;
			}
			ret=Math.min(ret, cnt);
		}
		return ret;
	}
}
