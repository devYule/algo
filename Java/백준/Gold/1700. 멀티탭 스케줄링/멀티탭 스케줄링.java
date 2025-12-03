import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			StringTokenizer st=new StringTokenizer(br.readLine());
			int n=Integer.parseInt(st.nextToken());
			int m=Integer.parseInt(st.nextToken());
			int[] order=new int[m];
			st=new StringTokenizer(br.readLine());
			for(int i=0; i<m; i++) order[i]=Integer.parseInt(st.nextToken());

			bw.write(
				String.valueOf(
					new Main().resolve(
						n, m, order
					)
				)
			);
			bw.flush();
		}
	}

	int resolve(int n, int m, int[] order) {
		boolean[] used=new boolean[m+1];
		int use=0;
		int ret=0;
		List<int[]> will=new ArrayList<>();
		for(int i=0; i<m; i++) {
			int cur=order[i];
			if(use<n || used[cur]) {
				if(!used[cur] && use<n) {
					used[cur]=true;
					use++;
				}
				continue;
			}

			int rm=-1;
			int rmi=-1;
			for(int j=0; j<m; j++) if(used[order[j]]) {
				int candi=order[j];
				int loc=m+1;
				for(int k=i+1; k<m; k++) {
					if(order[k]==candi) {
						loc=k;
						break;
					}
				}
				if(loc>rmi) {
					rm=order[j];
					rmi=loc;
				}
			}

			ret++;
			used[rm]=false;
			used[cur]=true;
		}
		return ret;
	}
}
