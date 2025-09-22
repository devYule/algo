import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			int n=Integer.parseInt(br.readLine());
			int m=Integer.parseInt(br.readLine());
			int[] dest=new int[m];
			if(m!=0) dest=Arrays.stream(br.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();


			bw.write(
				String.valueOf(
					new Main().resolve(
						n, m, dest
					)
				)
			);
			bw.flush();
		}
	}

	int nl, n;
	boolean[] cannot;
	int resolve(int n, int m, int[] dest) {
		if(n==100) return 0;
		this.n=n;
		this.cannot=new boolean[10];
		for(int i=0; i<m; i++) cannot[dest[i]]=true;
		int noMv=Math.abs(n-100);
		int tn=n;
		while(tn>0) {
			this.nl++;
			tn/=10;
		}
		nl++;
		return Math.min(noMv, find(0, ""));
	}

	int find(int press, String cur) {
		if(press==nl) return Math.abs(Integer.parseInt(cur)-n)+press;

		int ret=cur.isEmpty() ? (int)1e9 : Math.abs(Integer.parseInt(cur)-n)+press;
		for(int i=0; i<10; i++) {
			if(cannot[i]) continue;
			ret=Math.min(ret, find(press+1, i+cur));
		}
		return ret;
	}

}
