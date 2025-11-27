import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			bw.write(
				String.valueOf(
					new Main().resolve(
						br.readLine()
					)
				)
			);
			bw.flush();
		}
	}

	int resolve(String s) {
		Set<String> set=new HashSet<>();
		int n=s.length();
		for(int i=0; i<n; i++) {
			for(int j=i+1; j<=n; j++) {
				String c=s.substring(i, j);
				set.add(c);
			}
		}
		return set.size();
	}

}
