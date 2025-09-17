import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			int n=Integer.parseInt(br.readLine());

			char[][] rel=new char[n][];
			for(int i=0; i<n; i++) rel[i]=br.readLine().toCharArray();

			bw.write(
				String.valueOf(
					new Main().resolve(
						n, rel
					)
				)
			);
			bw.flush();
		}
	}

	int resolve(int n, char[][] rel) {
		int[] cnt=new int[n];
		for(int i=0; i<n; i++) {
			for(int j=i+1; j<n; j++) {
				if(rel[i][j]=='Y') {
					cnt[i]++; cnt[j]++;
					continue;
				}
				for(int k=0; k<n; k++) {
					if(k==i || k==j) continue;
					if(rel[i][k]=='Y' && rel[k][j]=='Y') {
						cnt[i]++; cnt[j]++;
						break;
					}
				}
			}
		}
		return Arrays.stream(cnt).max().orElse(-1);
	}
}
