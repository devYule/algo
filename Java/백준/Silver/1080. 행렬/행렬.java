import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			int[] split=Arrays.stream(br.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();

			int n=split[0], m=split[1];
			int[][] a=new int[n][];
			int[][] b=new int[n][];
			for(int i=0; i<2; i++) {
				for(int j=0; j<n; j++) {
					if(a[j]==null) a[j]=Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
					else b[j]=Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
				}
			}

			bw.write(
				String.valueOf(
					new Main().resolve(
						n, m, a, b
					)
				)
			);
			bw.flush();
		}
	}

	/*
		[y][x] 기준 우3, 하3 을 뒤집는다고 했을 때,
		[y][x] 는 언제나 더이상 변할 수 없음.
		따라서 [y][x]를 기준으로 탐욕적으로 플립을 수행함.
		최종적으로 같지 않으면 실패. 같으면 횟수 반환.
	*/

	int resolve(int n, int m, int[][] A, int[][] B) {
		int nm3=n-3;
		int mm3=m-3;
		int ret=0;
		for(int i=0; i<=nm3; i++) {
			for(int j=0; j<=mm3; j++) {
				if(A[i][j]==B[i][j]) continue;
				ret++;
				for(int k=0; k<3; k++) {
					for(int l=0; l<3; l++) {
						A[i+k][j+l]=(A[i+k][j+l]+1)%2;
					}
				}
			}
		}

		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				if(A[i][j]!=B[i][j]) return -1;
			}
		}
		return ret;
	}

}
