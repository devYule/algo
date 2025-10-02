import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			StringTokenizer st=new StringTokenizer(br.readLine());
			int n=Integer.parseInt(st.nextToken());
			int m=Integer.parseInt(st.nextToken());
			int k=Integer.parseInt(st.nextToken());

			char[][] map=new char[n][m];
			for(int i=0; i<n; i++) {
				map[i]=br.readLine().toCharArray();
			}
			int q=Integer.parseInt(br.readLine());

			int[][] loc=new int[q][2];
			for(int i=0; i<q; i++) {
				st=new StringTokenizer(br.readLine());
				loc[i][0]=Integer.parseInt(st.nextToken());
				loc[i][1]=Integer.parseInt(st.nextToken());
			}

			bw.write(
				String.valueOf(
					new Main().resolve(
						n, m, k, q, map, loc
					)
				)
			);
			bw.flush();
		}
	}

	int[] rg={0, 1};
	int[] cg={1, 0};

	int n, m, k;
	char[][] map;
	int[][][] memo;
	String[] name={"First", "Second"};
	String resolve(int n, int m, int k, int q, char[][] map, int[][] loc) {
		this.n=n; this.m=m; this.k=k;
		this.map=map;
		this.memo=new int[n][m][2];
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				Arrays.fill(memo[i][j], -1);
			}
		}
		List<Integer> ret=new ArrayList<>();
		for(int[] l: loc) {
			ret.add(find(l[0]-1, l[1]-1, 0));
		}
		return ret.stream().map(i->name[i]).collect(java.util.stream.Collectors.joining("\n"));
	}
	int find(int y, int x, int turn) {
		if(y==n-1 && x==m-1) return (turn+1)%2;
		if(memo[y][x][turn]!=-1) return memo[y][x][turn];
		int ret=(turn+1)%2;
		for(int i=0; i<2; i++) {
			int ny=y+rg[i];
			int nx=x+cg[i];
			if(!valid(ny, nx)) continue;
			int res=find(ny, nx, (turn+1)%2);
			if(res==turn) ret=res;
		}
		for(int i=1; i<=k; i++) {
			int ny=y+i;
			int nx=x+i;
			if(!valid(ny, nx)) continue;
			int res=find(ny, nx, (turn+1)%2);
			if(res==turn) ret=res;
		}
		return memo[y][x][turn]=ret;
	}

	boolean valid(int y, int x) {
		return y>=0 && x>=0 && y<n && x<m && map[y][x]!='#';
	}
}
