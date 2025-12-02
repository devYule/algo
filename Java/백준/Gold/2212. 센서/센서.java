import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			int n=Integer.parseInt(br.readLine());
			int m=Integer.parseInt(br.readLine());
			int[] locs=new int[n];
			StringTokenizer st=new StringTokenizer(br.readLine());
			for(int i=0; i<n; i++) {
				locs[i]=Integer.parseInt(st.nextToken());
			}

			bw.write(
				String.valueOf(
					new Main().resolve(
						n, m, locs
					)
				)
			);
			bw.flush();
		}
	}

	int resolve(int n, int m, int[] locs) {
		Set<Integer> l=new HashSet<>();
		for(int loc: locs) l.add(loc);
		locs=l.stream().sorted(Comparator.naturalOrder()).mapToInt(t->t).toArray();
		n=locs.length;
		List<Integer>L=new ArrayList<>();
		for(int i=1; i<n; i++) {
			L.add(locs[i]-locs[i-1]);
		}
		L.sort(Comparator.naturalOrder());
		int ret=0;
		for(int i=0; i<n-m; i++) {
			ret+=L.get(i);
		}
		return ret;
	}

}
