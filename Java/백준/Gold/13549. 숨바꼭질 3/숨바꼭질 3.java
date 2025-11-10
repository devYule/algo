import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			StringTokenizer st=new StringTokenizer(br.readLine());

			bw.write(
				String.valueOf(
					new Main().resolve(
						Integer.parseInt(st.nextToken()),
						Integer.parseInt(st.nextToken())
					)
				)
			);
			bw.flush();
		}
	}

	int resolve(int a, int b) {
		if(a>=b) return a-b;
		int limit=b+b-a;
		PriorityQueue<int[]> q=new PriorityQueue<>((a1, b1) -> a1[0]-b1[0]);
		int[] dist=new int[limit];
		Arrays.fill(dist, (int)1e9);
		q.add(new int[] {0, a});
		dist[a]=0;
		while(!q.isEmpty()) {
			int[] curs=q.poll();
			int cost=curs[0];
			int loc=curs[1];
			if(dist[loc]!=cost) continue;

			if(loc==b) return cost;

			if(loc-1>=0 && dist[loc-1]>cost+1) {
				dist[loc-1]=cost+1;
				q.add(new int[] {cost+1, loc-1});
			}
			if(loc+1<limit && dist[loc+1]>cost+1) {
				dist[loc+1]=cost+1;
				q.add(new int[] {cost+1, loc+1});
			}
			if(loc*2<limit && dist[loc*2]>cost) {
				dist[loc*2]=cost;
				q.add(new int[] {cost, loc*2});
			}
		}
		return -1;
	}

}
