import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			int n=Integer.parseInt(br.readLine());
			int[] order=Arrays.stream(br.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();

			bw.write(
				String.valueOf(
					new Main().resolve(
						n, order
					)
				)
			);
			bw.flush();
		}
	}

	String resolve(int n, int[] order) {
		int[] ordered=new int[n];
		for(int i=0; i<n; i++) {
			int pass=order[i]+1;
			int passed=0;
			for(int j=0; j<n; j++) {
				if(ordered[j]==0) passed++;
				if(pass==passed) {
					ordered[j]=i+1;
					break;
				}
			}
		}
		return Arrays.stream(ordered).boxed().map(String::valueOf).collect(java.util.stream.Collectors.joining(" "));
	}

}
