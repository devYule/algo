import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			bw.write(
				String.valueOf(
					new Main().resolve(
						Integer.parseInt(br.readLine())
					)
				)
			);
			bw.flush();
		}
	}

	int resolve(int n) {
		if(n<100) return n;
		int ret=99;
		for(int i=100; i<=n; i++) {
			String num=String.valueOf(i);
			boolean is=true;
			int gap=(num.charAt(1)-'0')-(num.charAt(0)-'0');
			for(int j=2; j<num.length(); j++) {
				int g=(num.charAt(j)-'0') - (num.charAt(j-1)-'0');
				if(g!=gap) { is=false; break; }
			}
			if(is) ret++;
		}
		return ret;
	}

}
