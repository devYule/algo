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

	String resolve(int n, int[] each) {
		int max=Arrays.stream(each).max().orElse(0);
		int[] map=new int[max+1];
		Arrays.fill(map, -1);
		for(int i=0; i<n; i++) map[each[i]]=i;
		int[] score=new int[n];

		for(int i=0; i<n; i++) {
			int cur=each[i];
			int sqrt=(int)Math.sqrt(cur);
			for(int j=1; j<=sqrt; j++) {
				if(cur%j!=0) continue;
				if(map[j]!=-1) {
					score[map[j]]++;
					score[i]--;
				}
				if(j!=cur/j && map[cur/j]!=-1) {
					score[map[cur/j]]++;
					score[i]--;
				}
			}
		}
		StringBuilder sb=new StringBuilder();
		for(int i=0; i<n; i++) {
			if(i!=0) sb.append(" ");
			sb.append(String.valueOf(score[i]));
		}
		return sb.toString();
	}

}
