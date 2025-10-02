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
			int k=Integer.parseInt(br.readLine());

			bw.write(
				String.valueOf(
					new Main().resolve(
						n, m, k, map
					)
				)
			);
			bw.flush();
		}
	}

	/*
		N, M<=1000
		K<=min(N*M, 100_000)

		N*M 직사각형
		map[i][j]=옥수수의 가치 (1~N*M 의 정수)

		K그루의 옥수수 수확. 가장 가가 높은 순.
		수확은 가장 바깥에서 옥수수를 밟지 않고 이동할 수 있어야 함.

	*/
	// 수확 순서대로 인덱스

	
	int[] rg={-1, 0, 1, 0};
	int[] cg={0, 1, 0, -1};

	String resolve(int n, int m, int k, int[][] map) {
		PriorityQueue<int[]> corn=new PriorityQueue<>((a, b)-> b[2]-a[2]);
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				if(i==0 || j==0 || i==n-1 || j==m-1) corn.add(new int[] {i, j, map[i][j]});
			}
		}
		boolean[][] vis=new boolean[n][m];
		List<String> ret=new ArrayList<>();
		for(int i=0; i<k; i++) {
			while(!corn.isEmpty()) {
				int[] c=corn.poll();
				int y=c[0], x=c[1];
				if(vis[y][x]) continue;
				vis[y][x]=true;
				ret.add((y+1) + " " + (x+1));
				for(int j=0; j<4; j++) {
					int ny=y+rg[j], nx=x+cg[j];
					if(ny>=0 && nx>=0 && ny<n && nx<m && !vis[ny][nx]) corn.add(new int[] {ny, nx, map[ny][nx]});
				}
				break;
			}
		}
		return ret.stream().collect(java.util.stream.Collectors.joining("\n"));
	}
}
