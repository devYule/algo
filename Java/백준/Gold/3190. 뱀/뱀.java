import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			int n=Integer.parseInt(br.readLine());
			int m=Integer.parseInt(br.readLine());
			int[][] map=new int[n][n];
			for(int i=0; i<m; i++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				map[Integer.parseInt(st.nextToken())-1][Integer.parseInt(st.nextToken())-1]=1;
			}

			int k=Integer.parseInt(br.readLine());
			List<int[]> turn=new ArrayList<>();
			for(int i=0; i<k; i++) {
				String[] info=br.readLine().split("\\s+");
				turn.add(new int[] {
					Integer.parseInt(info[0]),
					"D".equals(info[1]) ? 1 : -1
				});
			}

			bw.write(
				String.valueOf(
					new Main().resolve(
						n, m, map, k, turn
					)
				)
			);
			bw.flush();
		}
	}

	int resolve(int n, int m, int[][] map, int k, List<int[]> T) {
		int max=T.stream().mapToInt(it -> it[0]).max().orElse(0);
		int[] turn=new int[max+1];
		for(int i=0; i<T.size(); i++) {
			turn[T.get(i)[0]]=T.get(i)[1];
		}

		int[] rg={-1, 0, 1, 0};
		int[] cg={0, 1, 0, -1};
		int dir=1;

		int round=0;
		ArrayDeque<int[]> snake=new ArrayDeque<>();
		snake.add(new int[] {0, 0});

		while(true) {
			round++;
			
			int[] head=snake.peekLast();
			for(int i=0; i<snake.size(); i++) {
				int[] body=snake.removeFirst();
				snake.add(body);
			}
			int ny=head[0]+rg[dir];
			int nx=head[1]+cg[dir];
			if(ny<0 || nx<0 || ny==n || nx==n) return round;

			for(int i=0; i<snake.size(); i++) {
				int[] body=snake.removeFirst();
				if(body[0]==ny && body[1]==nx) return round;
				snake.add(body);
			}

			snake.add(new int[] {ny, nx});
			if(map[ny][nx]==0) {
				snake.removeFirst();
			} else map[ny][nx]=0;

			if(round<turn.length) {
				dir+=turn[round];
				if(dir==4) dir=0;
				if(dir==-1) dir=3;
			}
		}
	}

}
