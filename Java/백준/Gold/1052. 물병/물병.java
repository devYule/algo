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

	int resolve(int n, int m) {
		int ncopy=n;
		while(true) {
			int p=0;
			int mod=0;
			int num=ncopy;
			while(num>0) {
				if(num+mod<=m) return ncopy-n;
				mod+=num%2;
				num/=2;
			}
			ncopy++;
		}
	}

}
