import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			StringTokenizer st=new StringTokenizer(br.readLine());

			int n=Integer.parseInt(st.nextToken());
			int k=Integer.parseInt(st.nextToken());

			int[] rest=new int[n*2];
			st=new StringTokenizer(br.readLine());
			for(int i=0; i<n*2; i++) rest[i]=Integer.parseInt(st.nextToken());

			bw.write(
				String.valueOf(
					new Main().resolve(
						n, k, rest
					)
				)
			);
			bw.flush();
		}
	}

	int resolve(int n, int k, int[] rest) {
		int from=0;
		int to=n-1;
		ArrayDeque<Integer> rloc=new ArrayDeque<>();
		int zcnt=0;
		int round=0;
		boolean[] vis=new boolean[rest.length];
		while(zcnt<k) {
			round++;
			if(--from==-1) from=rest.length-1;
			if(--to==-1) to=rest.length-1;
			int size=rloc.size();
			for(int i=0; i<size; i++) {
				int loci=rloc.removeFirst();
				if(loci==to) { vis[loci]=false; continue; }
				int will=loci+1;
				if(will==rest.length) will=0;
				if(rest[will]==0 || vis[will]) { rloc.add(loci); continue; }
				vis[loci]=false;
				if(rest[will]==1) zcnt++;
				rest[will]--;
				if(will!=to) {
					rloc.add(will);
					vis[will]=true;
				}
			}

			if(rest[from]>0 && !vis[from]) {
				rloc.add(from);
				vis[from]=true;
				if(rest[from]==1) zcnt++;
				rest[from]--;
			}
		}
		return round;
	}

}
