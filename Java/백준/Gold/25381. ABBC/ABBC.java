import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			bw.write(
				String.valueOf(
					new Main().resolve(
						br.readLine().toCharArray()
					)
				)
			);
			bw.flush();
		}
	}

	int resolve(char[] C) {
		int n=C.length;
		TreeSet<Integer> a=new TreeSet<>();
		TreeSet<Integer> b=new TreeSet<>();
		TreeSet<Integer> c=new TreeSet<>();
		for(int i=0; i<n; i++) {
			if(C[i]=='A') a.add(i);
			else if(C[i]=='B') b.add(i);
			else if(C[i]=='C') c.add(i);
		}

		int ret=0;
		int size=c.size();
		for(int i=0; i<size; i++) {
			int cloc=c.pollFirst();
			Integer res=b.lower(cloc);
			if(res!=null) {
				b.remove(res);
				ret++;
			}
		}
		size=b.size();
		for(int i=0; i<size; i++) {
			int bloc=b.pollFirst();
			Integer res=a.lower(bloc);
			if(res!=null) {
				a.remove(res);
				ret++;
			}
		}
		return ret;
	}
}
