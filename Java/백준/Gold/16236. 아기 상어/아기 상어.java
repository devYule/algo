import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			int n=Integer.parseInt(br.readLine());
			int[][] map=new int[n][n];
			for(int i=0; i<n; i++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				for(int j=0; j<n; j++) {
					map[i][j]=Integer.parseInt(st.nextToken());
				}
			}

			bw.write(
				String.valueOf(
					new Main().resolve(
						n, map
					)
				)
			);
			bw.flush();
		}
	}

	class Fish {
		int y, x, size;
		Fish(int y, int x, int size) {
			this.y=y; this.x=x; this.size=size;
		}

		public String toString() {
			return "{ size: " + size + ", y: " + y + ", x: " + x + " }"; 
		}
	}
	int resolve(int n, int[][] map) {
		PriorityQueue<Fish> q=new PriorityQueue<>((a, b) -> a.size-b.size);
		int y=-1, x=-1;
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				if(map[i][j]>0 && map[i][j]<9) {
					q.add(new Fish(i, j, map[i][j]));
				} else if(map[i][j]==9) {
					y=i; x=j;
					map[i][j]=0;
				}
			}
		}

		int round=0;
		int size=2;
		int ate=0;

		while(true) {
			List<Fish> caneat=new ArrayList<>();
			while(!q.isEmpty() && q.peek().size<size) {
				caneat.add(q.poll());
			}
			if(caneat.isEmpty()) return round;

			int[][] dist=setPosLast(caneat, y, x, n, size, map);
			boolean notyet=true;
			for(int i=caneat.size()-1; i>=0; i--) {
				Fish f=caneat.get(i);
				if(notyet && dist[f.y][f.x]!=(int)1e9) {
					notyet=false;
					round+=dist[f.y][f.x];
					y=f.y; x=f.x;
					ate++;
					if(ate==size) {
						ate=0;
						size++;
					}
					continue;
				}
				q.add(f);
			}
			if(notyet) return round;
		}
	}

	int[] rg={-1, 0, 1, 0};
	int[] cg={0, 1, 0, -1};
	int[][] setPosLast(List<Fish> fish, int y, int x, int n, int sharkSize, int[][] map) {
		int[][] dist=new int[n][n];
		for(int i=0; i<n; i++) Arrays.fill(dist[i], (int)1e9);

		PriorityQueue<int[]> q=new PriorityQueue<>((a, b) -> a[0]-b[0]);
		q.add(new int[] {0, y, x});
		dist[y][x]=0;

		while(!q.isEmpty()) {
			int[] as=q.poll();
			int ay=as[1];
			int ax=as[2];
			int cost=as[0];

			for(int i=0; i<4; i++) {
				int ny=ay+rg[i];
				int nx=ax+cg[i];
				if(ny>=0 && nx>=0 && ny<n && nx<n && map[ny][nx]<=sharkSize) {
					if(dist[ny][nx]>cost+1) {
						dist[ny][nx]=cost+1;
						q.add(new int[] {cost+1, ny, nx});
					}
				}
			}
		}

		fish.sort((a, b) -> {
			if(dist[a.y][a.x]!=dist[b.y][b.x]) return dist[b.y][b.x]-dist[a.y][a.x];
			else if(a.y!=b.y) return b.y-a.y;
			else return b.x-a.x;
		});

		return dist;
	}
}
