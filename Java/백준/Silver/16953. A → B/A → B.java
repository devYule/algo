import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			StringTokenizer st=new StringTokenizer(br.readLine());
			int A=Integer.parseInt(st.nextToken());
			int B=Integer.parseInt(st.nextToken());

			bw.write(
				String.valueOf(
					new Main().resolve(
						A, B
					)
				)
			);
			bw.flush();
		}
	}

	int resolve(int A, int B) {
		PriorityQueue<int[]> q=new PriorityQueue<>((a, b) -> a[0]-b[0]);
		q.add(new int[] {1, B});

		while(!q.isEmpty()) {
			int[] curs=q.poll();
			int t=curs[1];
			int cost=curs[0];
			if(t==A) return cost;

			if(t%10==1 && t/10>=A) q.add(new int[] {cost+1, t/10});
			else if(t%2==0 && t/2>=A) q.add(new int[] {cost+1, t/2});
		}
		return -1;
	}

}
