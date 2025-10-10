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
		int a=0, b=0, ab=0, bc=0;
		for(int i=0; i<n; i++) {
			if(C[i]=='A') a++;
			else if(C[i]=='B') {
				if(a>0) {
					ab++;
					a--;
				} else b++;
			} else {
				if(b>0) {
					bc++;
					b--;
				} else if(ab>0) {
					ab--;
					bc++;
					a++;
				}
			}
		}
		return ab+bc;
	}
}
