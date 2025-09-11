import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			String[] split=br.readLine().split("\\s+");

			int input0=Integer.parseInt(split[0]);
			int input1=Integer.parseInt(split[1]);

			int[][] map=new int[input0][];
			for(int i=0; i<input0; i++) {
				map[i]=Arrays.stream(br.readLine().split(""))
					.mapToInt(a->Integer.parseInt(a))
					.toArray();
			}
			bw.write(
				String.valueOf(
					new Main().resolve(input0, input1, map)
				)
			);
	
			bw.flush();
		}
	}

	/*
		숫자의 최대: 999_999_999
		1개만 고를수도 있음.
		각 셀에서 y로 0개, x로 0개 만큼 이동한것 부터.
		각 y당 x로 0~8만큼씩 단조증가하는 수 들로 숫자 만들수 있다.
		ㄴ> 매 부분문제마다 해당 숫자가 완전제곱수인지 확인해야함.
		ㄴ> (int)sqrt == sqrt
	*/
	final int[] rg={-1, 0, 1, 0, 1, 1, -1, -1};
	final int[] cg={0, 1, 0, -1, -1, 1, -1, 1};
	int N, M;
	int[][] nums;
	int resolve(int N, int M, int[][] nums) {
		this.nums=nums;
		this.N=N;
		this.M=M;
		int ret=-1;
		for(int r=0; r<N; r++) {
			for(int c=0; c<M; c++) {
				for(int i=0; i<9; i++) {
					for(int j=0; j<9; j++) {
						if(i==0 && j==0) continue;
						for(int dir=0; dir<rg.length; dir++) {
							ret=Math.max(ret, best(r, c, i, j, dir, nums[r][c]));
						}
					}
				}
			}
		}
		return ret;
	}

	int best(int y, int x, int yadd, int xadd, int dir, int cur) {
		double sqrt=Math.sqrt(cur);
		int ret=-1;
		if((int)sqrt==sqrt) ret=(int)cur;

		int ydist=yadd*rg[dir];
		int xdist=xadd*cg[dir];

		int ny=y+(ydist);
		int nx=x+(xdist);

		if(xdist==0 && ydist==0 || !valid(ny, nx)) return ret;

		return Math.max(ret, best(ny, nx, yadd, xadd, dir, cur*10+nums[ny][nx]));
	}

	boolean valid(int y, int x) {
		return y>=0 && x>=0 && y<N && x<M;
	}
}