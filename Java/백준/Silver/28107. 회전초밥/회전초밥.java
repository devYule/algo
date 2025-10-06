import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			StringTokenizer st=new StringTokenizer(br.readLine());
			int n=Integer.parseInt(st.nextToken());
			int m=Integer.parseInt(st.nextToken());

			int[][] order=new int[n][];
			for(int i=0; i<n; i++) {
				st=new StringTokenizer(br.readLine());
				int cnt=Integer.parseInt(st.nextToken());
				order[i]=new int[cnt];
				for(int j=0; j<cnt; j++) {
					order[i][j]=Integer.parseInt(st.nextToken());
				}
			}
			int[] make=Arrays.stream(br.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();

			bw.write(
				String.valueOf(
					new Main().resolve(
						n, m, order, make
					)
				)
			);
			bw.flush();
		}
	}

	@SuppressWarnings("unchecked")
	String resolve(int n, int m, int[][] order, int[] make) {
		int max=Arrays.stream(make).max().orElseThrow();
		Queue<Integer>[] each=new ArrayDeque[max+1];
		for(int i=0; i<n; i++) {
			int[] o=order[i];
			for(int j=0; j<o.length; j++) {
				if(order[i][j]>max) continue;
				if(each[order[i][j]]==null) each[order[i][j]]=new ArrayDeque<>();
				each[order[i][j]].add(i);
			}
		}

		int[] get=new int[n];
		for(int ma: make) {
			if(each[ma]!=null && !each[ma].isEmpty()) get[each[ma].poll()]++;
		}

		return Arrays.stream(get).mapToObj(String::valueOf).collect(java.util.stream.Collectors.joining(" "));
	}

}
