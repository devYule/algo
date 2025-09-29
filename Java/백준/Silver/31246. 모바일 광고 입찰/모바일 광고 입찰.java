import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			String[] split=br.readLine().split("\\s");
			int n=Integer.parseInt(split[0]);
			int k=Integer.parseInt(split[1]);

			int[] A=new int[n];
			int[] B=new int[n];
			for(int i=0; i<n; i++) {
				String[] each=br.readLine().split("\\s");
				A[i]=Integer.parseInt(each[0]);
				B[i]=Integer.parseInt(each[1]);
			}

			bw.write(
				String.valueOf(
					new Main().resolve(
						n, k, A, B
					)
				)
			);
			bw.flush();
		}
	}

	int resolve(int n, int k, int[] A, int[] B) {
		List<Integer> idx=new ArrayList<>();
		for(int i=0; i<n; i++) idx.add(i);
		idx.sort((a, b)-> Integer.compare(B[a]-A[a], B[b]-A[b]));

		int targetIdx=idx.get(k-1);
		if(A[targetIdx]<B[targetIdx]) return B[targetIdx]-A[targetIdx];
		return 0;
	}

}
