import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			StringTokenizer st=new StringTokenizer(br.readLine());
			int n=Integer.parseInt(st.nextToken());
			int m=Integer.parseInt(st.nextToken());
			int[][] map=new int[n][m];
			for(int i=0; i<n; i++) {
				st=new StringTokenizer(br.readLine());
				for(int j=0; j<m; j++) {
					map[i][j]=Integer.parseInt(st.nextToken());
				}
			}

			bw.write(
				String.valueOf(
					new Main().resolve(
						n, m, map
					)
				)
			);
			bw.flush();
		}
	}

	int m, min0, map[][], memo[][][];
	int resolve(int n, int m, int[][] map) {
		this.m=m; this.map=map;
		this.memo=new int[n][m][4];
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				Arrays.fill(memo[i][j], -1);
			}
		}
		this.min0=Arrays.stream(map[0]).min().orElse(0);
		int ret=(int)1e9;
		for(int i=0; i<m; i++) {
			ret=Math.min(ret, find(n-1, i, 2));
		}
		return ret;
	}

	int find(int line, int col, int prevSelect) {
		if(col<0 || col>=m) return (int)1e9;
		if(line==0) return map[line][col];
		if(memo[line][col][prevSelect+1]!=-1) return memo[line][col][prevSelect+1];
		int ret=(int)1e9;
		for(int j=-1; j<=1; j++) {
			if(prevSelect==j) continue;
			ret=Math.min(ret, find(line-1, col+j, j)+map[line][col]);
		}
		return memo[line][col][prevSelect+1]=ret;
	}
}
