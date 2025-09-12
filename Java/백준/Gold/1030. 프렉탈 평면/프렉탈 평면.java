import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			String[] split=br.readLine().split("\\s+");


			bw.write(
				String.valueOf(
					new Main().resolve(
						Integer.parseInt(split[0]),
						Integer.parseInt(split[1]),
						Integer.parseInt(split[2]),
						Long.parseLong(split[3]),
						Long.parseLong(split[4]),
						Long.parseLong(split[5]),
						Long.parseLong(split[6])
					)
				)
			);
	
			bw.flush();
		}
	}

	/*
		구하려는 구간 [y?][x?] 는 두 가지중 하나다.
			1. 이번에 확장된 N^s 의 가운데 N^s-1 에 포함되는 경우
				ㄴ> 무조건 검정.
			2. 그 외의 경우 (s'=s-1)
				1. N^s' 의 가운데에 포함되는 경우 
					ㄴ> 무조건 검정
					...
		recursive 하다.

		필요: 
			1. 현재 가운데 범위 ry, rx 구하기.
			2. 현재 [y?][x?] 가 가운데에 포함하지 않는 경우에 사용할,
				이전 단계로의 좌표 치환.

	*/
	int n;
	int k;
	int whiteSet;
	PrintStream o=System.out;
	String resolve(int s, int N, int K, long r1, long r2, long c1, long c2) {
		this.n=N;
		this.k=K;
		this.whiteSet=(n-k)/2;

		StringBuilder sb=new StringBuilder();
		for(long i=r1; i<=r2; i++) {
			for(long j=c1; j<=c2; j++) {
				sb.append(String.valueOf(isBlack(i, j, s) ? 1 : 0));
			}
			if(i<r2) sb.append("\n");
		}

		return sb.toString();
	}

	boolean isBlack(long y, long x, int s) {
		if(s<0) return false;
		if(center(y, x, s)) return true;
		long[] prev=fix(y, x, s);
		return isBlack(prev[0], prev[1], s-1);
	}

	boolean center(long y, long x, int s) {
		long per=(long)Math.pow(n, s-1);
		long from=per*whiteSet;
		long to=from+per*k;
		return y>=from && y<to && x>=from && x<to;
	}

	long[] fix(long y, long x, int s) {
		if(s==0) return new long[2];
		int prevs=s-1;
		long prevMax=(long)Math.pow(n, prevs);
		return new long[] {y%prevMax, x%prevMax};
	}
}