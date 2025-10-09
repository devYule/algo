import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			bw.write(
				String.valueOf(
					new Main().resolve(
						Integer.parseInt(br.readLine()),
						br.readLine()
					)
				)
			);
			bw.flush();
		}
	}

	int resolve(int n, String s) {
		Deque<Character> st=new ArrayDeque<>();

		int ret=0;
		for(char c: s.toCharArray()) {
			if(!st.isEmpty() && st.peekLast()!=c) {
				st.pollLast();
			} else st.addLast(c);
			ret=Math.max(ret, st.size());
		}
		return st.isEmpty() ? ret : -1;
	}

}
