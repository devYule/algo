import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			int n=Integer.parseInt(br.readLine());
			String[] words=new String[n];
			for(int i=0; i<n; i++) {
				words[i]=br.readLine();
			}

			bw.write(
				String.valueOf(
					new Main().resolve(
						n, words
					)
				)
			);
			bw.flush();
		}
	}

	int resolve(int n, String[] words) {
		List<String> token=Arrays.stream(words).collect(java.util.stream.Collectors.toList());
		token.sort((a, b)->a.length()-b.length());

		int ret=0;
		for(int i=0; i<n; i++) {
			boolean can=true;
			String base=token.get(i);
			for(int j=i+1; j<n; j++) {
				String target=token.get(j);
				int bi=0, ti=0;
				while(bi<base.length() && ti<target.length()) {
					if(base.charAt(bi)!=target.charAt(ti)) break;
					bi++; ti++;
				}
				if(bi==base.length()) {
					can=false;
					break;
				}
			}
			if(can) ret++;
		}
		return ret;
	}
}
