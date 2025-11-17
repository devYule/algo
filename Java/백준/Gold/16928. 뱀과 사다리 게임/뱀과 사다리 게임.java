import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			StringTokenizer st=new StringTokenizer(br.readLine());
			int n=Integer.parseInt(st.nextToken());
			int m=Integer.parseInt(st.nextToken());

			int[] mv=new int[101];
			for(int i=0; i<n+m; i++) {
				st=new StringTokenizer(br.readLine());
				mv[Integer.parseInt(st.nextToken())]=
					Integer.parseInt(st.nextToken());
			}

			bw.write(
				String.valueOf(
					new Main().resolve(
						n, m, mv
					)
				)
			);
			bw.flush();
		}
	}


	int resolve(int n, int m, int[] mv) {
		for(int i=0; i<mv.length; i++) if(mv[i]==0) mv[i]=i;
		PriorityQueue<int[]> q=new PriorityQueue<>((a, b) -> a[0]==b[0] ? a[1]-b[1] : a[0]-b[0]);

		int[] dist=new int[101];
		Arrays.fill(dist, (int)1e9);

		q.add(new int[] {0, 1});
		dist[1]=0;

		while(!q.isEmpty()) {
			int[] as=q.poll();

			int loc=as[1];
			int cost=as[0];

			if(dist[loc]!=cost) continue;

			for(int i=1; i<=6; i++) {
				if(loc+i>100) continue;
				int nextLoc=mv[loc+i];
				if(dist[nextLoc]>cost+1) {
					dist[nextLoc]=cost+1;
					q.add(new int[] {cost+1, nextLoc});
				}
			}
		}
		return dist[100];
	}

}
