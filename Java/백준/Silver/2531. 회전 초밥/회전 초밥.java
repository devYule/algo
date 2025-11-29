import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			StringTokenizer st=new StringTokenizer(br.readLine());
			int n=Integer.parseInt(st.nextToken());
			int d=Integer.parseInt(st.nextToken());
			int k=Integer.parseInt(st.nextToken());
			int c=Integer.parseInt(st.nextToken());
			int[] s=new int[n];
			for(int i=0; i<n; i++) s[i]=Integer.parseInt(br.readLine());

			bw.write(
				String.valueOf(
					new Main().resolve(
						n, d, k, c, s
					)
				)
			);
			bw.flush();
		}
	}

	int resolve(int n, int d, int k, int c, int[] S) {
		List<int[]> event= new ArrayList<>();
		for(int i=0;i<n;i++) {
			event.add(new int[] {S[i], 1, i});
			event.add(new int[] {S[i], -1, i+k});
		}
		for(int i=0; i<k-1; i++) {
			event.add(new int[] {S[i], 1, n+i});
			event.add(new int[] {S[i], -1, n+i+k});
		}
		event.sort((a, b) -> a[2]==b[2] ? a[1]-b[1] : a[2]-b[2]);

		int[] eat=new int[d+1];
		eat[c]=1;
		int sum=0;
		int cnt=1;
		int ret=1;
		for(int[] e: event) {
			int kind=e[0], flag=e[1];
			sum+=flag;
			if(flag==1) {
				eat[kind]++;
				if(eat[kind]==1) cnt++;
			} else {
				eat[kind]--;
				if(eat[kind]==0) cnt--;
			}
			if(sum==k) {
				ret=Math.max(ret, cnt);
			}
		}
		return ret;
	}

}
