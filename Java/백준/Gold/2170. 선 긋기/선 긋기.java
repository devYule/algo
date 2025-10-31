import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			int n=Integer.parseInt(br.readLine());
			int[][] p=new int[n][2];
			for(int i=0; i<n; i++) {
				StringTokenizer st=new StringTokenizer(br.readLine());

				p[i][0]=Integer.parseInt(st.nextToken());
				p[i][1]=Integer.parseInt(st.nextToken());
			}

			bw.write(
				String.valueOf(
					new Main().resolve(
						n, p
					)
				)
			);
			bw.flush();
		}
	}

	int resolve(int n, int[][] fromTo) {
		List<int[]> event=new ArrayList<>();
    for(int ft[]: fromTo) {
      int from=Math.min(ft[0], ft[1]);
      int to=Math.max(ft[0], ft[1]);
      event.add(new int[] {from, 1});
      event.add(new int[] {to, -1});
    }

    event.sort((a, b) -> a[0]==b[0] ? a[1]-b[1] : a[0]-b[0]);

    int ret=0;
    int connect=0;
    int start=0;

    for(int i=0; i<event.size(); i++) {
      int e[]=event.get(i);
      connect+=e[1];
      if(e[1]==-1 && connect==0) ret+=e[0]-start;
      if(e[1]==1 && connect==1) start=e[0];
    }
    return ret;
	}

}
