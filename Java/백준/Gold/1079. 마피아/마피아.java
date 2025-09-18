import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			int n=Integer.parseInt(br.readLine());
			long[] glt=Arrays.stream(br.readLine().split("\\s")).mapToLong(Long::parseLong).toArray();
			int[][] dist=new int[n][];
			for(int i=0; i<n; i++) {
				dist[i]=Arrays.stream(br.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();
			}
			int p=Integer.parseInt(br.readLine());

			bw.write(
				String.valueOf(
					new Main().resolve(
						n, glt, dist, p
					)
				)
			);
			bw.flush();
		}
	}

	/*
		p만 혼자 마피아.
		n%2==0?:
		밤: 한명 죽일 수 있음. i가 죽으면 모든 사람의 glt 가 dist[i][j] 만큼 오름.
		n%2!=0?:
		낮: 가장 glt[i]가 높은 사람이 죽음. glt값은 변하지 않음.

		live: 죽은 사람 마킹. (죽음: 0, 살아있음: 1)
		live&1<<p==0 || bitCount(live)==1 이면 게임끝.

	*/
	long[] glt;
	int[][] dist;
	int p, N;
	int resolve(int n, long[] glt, int[][] dist, int p) {
		this.glt=glt; this.dist=dist; this.p=p; this.N=n;
		int live=0;
		for(int i=0; i<n; i++) live|=1<<i;
		return simul(live);
	}
	int simul(int live) {
		int rest=Integer.bitCount(live);
		if((live&1<<p)==0 || rest==1) return 0;
		boolean isNight=rest%2==0;

		if(isNight) {
			int ret=0;
			for(int i=0; i<N; i++) {
				if(i==p || (live&1<<i)==0) continue;
				// i번을 죽일 경우
				for(int j=0; j<dist[i].length; j++) glt[j]+=dist[i][j];

				ret=Math.max(ret, simul(live&(~(1<<i))));
				// btrk
				for(int j=0; j<dist[i].length; j++) glt[j]-=dist[i][j];
			}
			return ret+1;
		} else {
			long max=Long.MIN_VALUE;
			int maxIdx=-1;
			for(int i=0; i<N; i++) {
				if((live&1<<i)==0) continue;
				if(max<glt[i]) { max=glt[i]; maxIdx=i; }
			}
			return simul(live&(~(1<<maxIdx)));
		}
	}
}
