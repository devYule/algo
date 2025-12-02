import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			StringTokenizer st=new StringTokenizer(br.readLine());
			int n=Integer.parseInt(st.nextToken());
			int m=Integer.parseInt(st.nextToken());

			int[][] note=new int[n][2];
			for(int i=0; i<n; i++) {
				st=new StringTokenizer(br.readLine());
				note[i][0]=Integer.parseInt(st.nextToken());
				note[i][1]=Integer.parseInt(st.nextToken());
			}

			bw.write(
				String.valueOf(
					new Main().resolve(
						n, m, note
					)
				)
			);
			bw.flush();
		}
	}

	@SuppressWarnings("unchecked")
	int resolve(int n, int m, int[][] note) {
		ArrayDeque<Integer>[] qs=new ArrayDeque[7];
		for(int i=1; i<=6; i++) qs[i]=new ArrayDeque<>();

		int ret=0;
		for(int[] nt: note) {
			ArrayDeque<Integer> q= qs[nt[0]];
			int target=nt[1];
			while(!q.isEmpty() && q.peekLast()>target) {
				ret++;
				q.removeLast();
			}

			if(!q.isEmpty() && q.peekLast()==nt[1]) continue;
			ret++;
			q.add(nt[1]);
		}
		return ret;
	}

}
