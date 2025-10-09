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
						Arrays.stream(br.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray()
					)
				)
			);
			bw.flush();
		}
	}

	final String POS="YES", NEG="NO";
	String resolve(int n, int[] nums) {
		int[] st=new int[4];

		for(int num: nums) {
			int posi=-1;
			int posgap=num+1;
			for(int i=0; i<4; i++) {
				if(num>st[i]) {
					if(posgap>num-st[i]) {
						posi=i;
						posgap=num-st[i];
					}
				}
			}
			if(posi==-1) return NEG;
			st[posi]=num;
		}
		return POS;
	}

}
