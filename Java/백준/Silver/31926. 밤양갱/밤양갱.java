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
		int base=8+2;
		n--;
		int can=1;
		while(n>0) {
			if(n<can) base--;
			n-=can;
			can*=2;
			base++;
		}
		return base;
	}
}
