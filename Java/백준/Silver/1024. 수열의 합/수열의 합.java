import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			String[] split=br.readLine().split("\\s+");

			int input0=Integer.parseInt(split[0]);
			int input1=Integer.parseInt(split[1]);

			bw.write(
				String.valueOf(
					new Main().resolve(input0, input1)
				)
			);
	
			bw.flush();
		}
	}

	// 합이 N이 되는 L개 이상의 연속된 숫자중 원소가 최소개수.
	// N<=1_000_000_000, 2<=L<=100
	// System.out.println(Math.log(1_000_000_000)*100*100);
	String resolve(int N, int L) {
		// N/L 보다 같거나 클수는 없다.

		int from=-1;
		int cnt=-1;
		// i개로 만들 수 있는가.
		for(int i=L; i<=100; i++) {
			int lo=0, hi=N;
			while(lo<=hi) {
				int mid=(lo+hi)>>>1;
				long calc=0;
				for(long j=0; j<i; j++) {
					calc+=mid+j;
				}
				if(calc>N) hi=mid-1;
				else if (calc<N) lo=mid+1;
				else {
					from=mid;
					cnt=i;
					break;
				}
			}
			if(cnt!=-1) break;
		}
		if(cnt==-1) return "-1";

		List<String> ret=new ArrayList<>();
		for(int i=0; i<cnt; i++) ret.add(String.valueOf(from+i));

		return ret.stream().collect(java.util.stream.Collectors.joining(" "));
	}

}