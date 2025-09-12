import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			int n=Integer.parseInt(br.readLine());


			bw.write(
				String.valueOf(
					new Main().resolve(
						n, 
						Arrays.stream(br.readLine().split("\\s+"))
							.mapToInt(a->Integer.parseInt(a)).toArray(),
						Arrays.stream(br.readLine().split("\\s+"))
							.mapToInt(a->Integer.parseInt(a)).toArray()
					)
				)
			);
	
			bw.flush();
		}
	}

	int resolve(int n, int[] A, int[] B) {
		List<Integer> indices=new ArrayList<>();
		for(int i=0; i<n; i++) indices.add(i);
		indices.sort((a, b) -> Integer.compare(B[b], B[a]));

		Arrays.sort(A);

		int ret=0;
		for(int i=0; i<n; i++) ret+=A[i]*B[indices.get(i)];
		return ret;
	}
}