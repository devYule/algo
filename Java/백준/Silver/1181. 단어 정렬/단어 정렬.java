import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			int n=Integer.parseInt(br.readLine());
			Set<String> str=new HashSet<>();
			for(int i=0; i<n; i++) {
				str.add(br.readLine());
			}

			bw.write(
				String.valueOf(
					new Main().resolve(
						str
					)
				)
			);
			bw.flush();
		}
	}

	String resolve(Set<String> str) {
		return str.stream().sorted((a, b) -> {
			if(a.length()!=b.length()) return a.length()-b.length();
			return a.compareTo(b);
		}).collect(java.util.stream.Collectors.joining("\n"));
	}

}
