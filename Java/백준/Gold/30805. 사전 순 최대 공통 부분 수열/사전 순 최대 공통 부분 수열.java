import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			int n=Integer.parseInt(br.readLine());
			int[] A=Arrays.stream(br.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();
			int m=Integer.parseInt(br.readLine());
			int[] B=Arrays.stream(br.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();

			bw.write(
				String.valueOf(
					new Main().resolve(
						n, m, A, B
					)
				)
			);
			bw.flush();
		}
	}

	List<Integer> al=new ArrayList<>();
	List<Integer> bl=new ArrayList<>();	
	String resolve(int n, int m, int[] A, int[] B) {
		int[] amk=new int[101];
		int[] bmk=new int[101];
		for(int i=0; i<n; i++) amk[A[i]]++;
		for(int i=0; i<m; i++) bmk[B[i]]++;
		for(int i=0; i<n; i++) if(bmk[A[i]]>0) al.add(A[i]);
		for(int i=0; i<m; i++) if(amk[B[i]]>0) bl.add(B[i]);

		if(al.isEmpty()) return "0";
		List<Integer> holder=new ArrayList<>();
		find(0, 0, holder);

		return holder.size() + "\n" + holder.stream().map(String::valueOf).collect(java.util.stream.Collectors.joining(" "));
	}


	void find(int ai, int bi, List<Integer> holder) {
		if(ai>=al.size() || bi>=bl.size()) return;
		int canval=-1;
		int canai=-1;
		int canbi=-1;
		for(int i=ai; i<al.size(); i++) {
			int target=al.get(i);
			if(canval>=target) continue;
			for(int j=bi; j<bl.size(); j++) {
				if(bl.get(j)==target) {
					canval=target;
					canai=i;
					canbi=j;
					break;
				}
			}
		}
		if(canval!=-1) {
			holder.add(canval);
			find(canai+1, canbi+1, holder);
		}
	}

}
