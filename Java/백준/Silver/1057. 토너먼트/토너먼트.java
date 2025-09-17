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
						Integer.parseInt(split[0]), Integer.parseInt(split[1]), Integer.parseInt(split[2])
					)
				)
			);
			bw.flush();
		}
	}

	int resolve(int n, int a, int b) {
		int round=1;
		if(a>b) { int tmp=a; a=b; b=tmp; }
		while(a%2==0 || a+1<b) {
			round++;
			a=a/2+(a%2);
			b=b/2+(b%2);
		}
		return round;
	}

}
