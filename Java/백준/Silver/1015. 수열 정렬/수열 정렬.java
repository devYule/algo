import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {
			int N=Integer.parseInt(br.readLine());
			
			bw.write(
				new Main().resolve(N, Arrays.stream(br.readLine().split("\\s+"))
					.mapToInt(a->Integer.parseInt(a)).toArray())
			);
			bw.flush();
		}
	}


	/* 
		B[P[0]]=A[0]=2
		B[P[1]]=A[1]=3
		B[P[2]]=A[2]=1

		P=[1, 2, 0]

		B[1]=2, B[2]=3, B[0]=1
		B=[1, 2, 3]

	*/
	String resolve(int n, int[] A) {
		// (N-1)-(나보다 큰숫자의 개수 + (나와 작은 숫자의 개수 -1))
		int[] cnt=new int[Arrays.stream(A).max().orElse(-2)+1];
		int[] used=new int[cnt.length];
		for(int a: A) cnt[a]++;
		List<String> ret=new ArrayList<>();
		int[] P=new int[n];
		for(int a: A) {
			used[a]++;
			int more=0;
			int same=cnt[a]-used[a];
			for(int j=a+1; j<cnt.length; j++) {
				if(cnt[j]>0) more+=cnt[j];
			}
			ret.add(String.valueOf((n-1)-(more+same)));
		}
		int[] B=new int[A.length];
		for(int i=0; i<n; i++) B[P[i]]=A[i];
		return ret.stream().collect(java.util.stream.Collectors.joining(" "));
	}

}