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
						Integer.parseInt(split[0]),
						Integer.parseInt(split[1])
					)
				)
			);
			bw.flush();
		}
	}

	String resolve(int n, int b) {
		return tob(n, b);
	}

	String tob(int n, int b) {
		if(b>0 && n<0) return "-"+tob(-n, b);
		StringBuilder sb=new StringBuilder();
		while(n!=0) {
			int r=n%b;
			n/=b;
			if(r<0) {
				r+=Math.abs(b);
				n+=1;
			}
			sb.append(r);
		}
		if(sb.length()==0) return "0";
		return sb.reverse().toString();
	}

}
