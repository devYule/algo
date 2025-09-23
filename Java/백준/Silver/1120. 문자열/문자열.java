import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			String[] split=br.readLine().split("\\s");
			bw.write(
				String.valueOf(
					new Main().resolve(
						split[0], split[1]
					)
				)
			);
			bw.flush();
		}
	}

	/*
		 adaabc 
		aababbc
	*/
	int resolve(String a, String b) {
		int ret=(int)1e9;
		int shift=0;
		while(shift+a.length()<=b.length()) {
			int ai=0, bi=shift;
			int cnt=0;
			while(ai<a.length() && bi<b.length()) {
				if(a.charAt(ai)!=b.charAt(bi)) cnt++;
				ai++; bi++;
			}
			ret=Math.min(ret, cnt);
			shift++;
		}
		return ret;
	}
}
