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

			int[][] scores=new int[n][2];
			for(int i=0; i<n; i++) {
				st=new StringTokenizer(br.readLine());
				scores[i][0]=Integer.parseInt(st.nextToken());
				scores[i][1]=Integer.parseInt(st.nextToken());
			}

			bw.write(
				String.valueOf(
					new Main().resolve(
						n, m, k, scores
					)
				)
			);
			bw.flush();
		}
	}

	long resolve(int n, int m, int k, int[][] scores) {
		boolean[] picked=new boolean[n];
		List<Integer> your=new ArrayList<>();
		List<Integer> my=new ArrayList<>();
		for(int i=0; i<n; i++) {
			your.add(i); my.add(i);
		}

		my.sort((a, b) -> scores[b][0]-scores[a][0]);
		your.sort((a, b) -> scores[b][1]-scores[a][1]);

		long ret=0;
		for(int i=0; i<k; i++) {
			picked[your.get(i)]=true;
			ret+=scores[your.get(i)][0];
		}

		int cnt=0;
		int idx=0;
		while(cnt<m && idx<n) {
			if(!picked[my.get(idx)]) {
				cnt++;
				ret+=scores[my.get(idx)][0];
			}
			idx++;
		}
		return ret;
	}
}
