import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			String[] split=br.readLine().split("\\s+");

			bw.write(
				String.valueOf(
					new Main().resolve(
						Integer.parseInt(split[0]), Integer.parseInt(split[1])
					)
				)
			);
			bw.flush();
		}
	}


	int resolve(int n, int k) {
		String ns=String.valueOf(n);
		if(ns.length()==1) return -1;

		List<Integer> candi=new ArrayList<>();
		candi.add(n);
		for(int i=0; i<k; i++) {
			List<Integer> add=new ArrayList<>();
			boolean[] check=new boolean[1_000_001];
			for(int curs: candi) {
				String cur=String.valueOf(curs);
				char[] cs=cur.toCharArray();
				for(int j=0; j<cs.length; j++) {
					for(int l=j+1; l<cs.length; l++) {
						if(j==0 && cs[l]=='0') continue;
						char tmp=cs[j];
						cs[j]=cs[l];
						cs[l]=tmp;

						int num=Integer.parseInt(new String(cs));
						if(!check[num]) {
							check[num]=true;
							add.add(num);
						}

						tmp=cs[j];
						cs[j]=cs[l];
						cs[l]=tmp;
					}
				}
			}
			if(add.isEmpty()) return -1;
			candi=add;
		}
		candi.sort((a, b)->b-a);
		return candi.get(0);

	}
}