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

	String resolve(char[] S) {
		int i=0;
		boolean open=false;
		int start=-1;
		StringBuilder ret=new StringBuilder();
		while(i<S.length) {
			if(S[i]=='<') {
				while(i<S.length && S[i]!='>') i++;
				i++;
				continue;
			}
			if(S[i]==' ') { i++; continue; }
			int from=i;
			while(i<S.length && S[i]!=' ' && S[i]!='<') i++;
			int to=i-1;
			int gap=to-from;
			while(from<to) {
				char tmp=S[from];
				S[from]=S[to];
				S[to]=tmp;
				from++;
				to--;
			}
		}
		return new String(S);
	}
}
