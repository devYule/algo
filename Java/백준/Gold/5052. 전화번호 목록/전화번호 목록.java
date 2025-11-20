import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			int T=Integer.parseInt(br.readLine());

			for(int r=0; r<T; r++) {
				if(r!=0) bw.write("\n");

				int n=Integer.parseInt(br.readLine());
				String[] tk=new String[n];
				for(int i=0; i<n; i++) tk[i]=br.readLine();

				bw.write(
					String.valueOf(
						new Main().resolve(
							n, tk
						)
					)
				);
			}
			bw.flush();
		}
	}

	String POS="YES", NEG="NO";
	String resolve(int n, String[] tk) {
		Arrays.sort(tk, (a, b) -> {
			int ai=0, bi=0;
			while(ai<a.length() && bi<b.length()) {
				if(a.charAt(ai)!=b.charAt(bi)) {
					return a.charAt(ai)-b.charAt(bi);
				}
				ai++; bi++;
			}
			return a.length()-b.length();
		});

		for(int i=0; i<n-1; i++) {
			String c=tk[i];
			String t=tk[i+1];
			boolean cont=true;
			for(int j=0; j<c.length(); j++) {
				if(c.charAt(j)!=t.charAt(j)) {
					cont=false;
					break;
				}
			}
			if(cont) return NEG;
		}
		return POS;
	}

}
