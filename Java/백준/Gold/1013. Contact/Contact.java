import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {
			int R=Integer.parseInt(br.readLine());
			for(int i=0; i<R; i++) {
				String token=br.readLine();
				bw.write(String.valueOf(new Main().resolve(token)));
				if(i<R-1) bw.write("\n");
			}
			bw.flush();
		}
	}

	String resolve(String token) {
		return token.matches("^(100+1+|01)+$") ? "YES" : "NO";
	}
}