import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {
			
			int n=Integer.parseInt(br.readLine());

			int[][] axis=new int[n][2];
			for(int i=0; i<n; i++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				axis[i][0]=Integer.parseInt(st.nextToken());
				axis[i][1]=Integer.parseInt(st.nextToken());
			}

			bw.write(
				String.valueOf(
					new Main().resolve(
						n, axis
					)
				)
			);
			bw.flush();
		}
	}

	int resolve(int n, int[][] ax) {
		Arrays.sort(ax, (a, b) -> a[0]==b[0] ? a[1]-b[1] : a[0]-b[0]);
		List<int[]> lower=new ArrayList<>();
		for(int[] c: ax) {

			while(lower.size()>=2) {
				int[] a=lower.get(lower.size()-2);
				int[] b=lower.get(lower.size()-1);
				int ccw=ccw(a, b, c);
				if(ccw<=0) lower.remove(lower.size()-1);
				else break;
			}
			lower.add(c);
		}

		List<int[]> high=new ArrayList<>();
		for(int i=ax.length-1; i>=0; i--) {
			int[] c=ax[i];

			while(high.size()>=2) {
				int[] a=high.get(high.size()-2);
				int[] b=high.get(high.size()-1);
				int ccw=ccw(a, b, c);
				if(ccw<=0) high.remove(high.size()-1);
				else break;
			}
			high.add(c);
		}

		lower.remove(lower.size()-1);
		high.remove(high.size()-1);
		lower.addAll(high);

		return lower.size();

	}

	int ccw(int[] a, int[] b, int[] c) {
		long ccw=((long)c[1]-a[1])*(b[0]-a[0])-((long)b[1]-a[1])*(c[0]-a[0]);
		return Long.compare(ccw, 0);
	}

}
