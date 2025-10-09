import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			StringTokenizer st=new StringTokenizer(br.readLine());
			int n=Integer.parseInt(st.nextToken());
			int b=Integer.parseInt(st.nextToken());
			int a=Integer.parseInt(st.nextToken());

			bw.write(
				String.valueOf(
					new Main().resolve(
						n, b, a, Arrays.stream(br.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray()
					)
				)
			);
			bw.flush();
		}
	}

	int resolve(int n, int max, int a, int[] price) {
		Arrays.sort(price);
		long sum=0;
		int used=0;
		int ret=0;
		PriorityQueue<Integer> pos=new PriorityQueue<>((a1, b1) -> b1-a1);
		for(int i=0; i<n; i++) {
			long tmp=sum+price[i];
			pos.add(i);
			ret++;
			if(tmp>max) {
				while(!pos.isEmpty() && used<a) {
					int targeti=pos.poll();
					tmp-=price[targeti]/2;
					used++;
					if(tmp<=max) break;
				}
				if(tmp>max) {
					ret--;
					break;
				}
			} 
			sum=tmp;
		}

		return ret;
	}

}
