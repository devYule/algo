import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			int n=Integer.parseInt(br.readLine());
			List<List<String[]>> list=new ArrayList<>();
			for(int i=0; i<n; i++) {
				int cnt=Integer.parseInt(br.readLine());
				List<String[]> cur=new ArrayList<>();
				list.add(cur);
				for(int j=0; j<cnt; j++) {
					cur.add(br.readLine().split("\\s"));
				}
			}

			bw.write(
				String.valueOf(
					new Main().resolve(
						n, list
					)
				)
			);
			bw.flush();
		}
	}

	final String S="LUCKY", F="UNLUCKY";

	String resolve(int n, List<List<String[]>> candi) {
		List<String> ret=new ArrayList<>();
		
		for(int i=0; i<n; i++) {
			int[] prev=new int[7];
			prev[1]=1;
			List<String[]> cur=candi.get(i);
			for(String[] c: cur) {
				int[] newPrev=new int[7];

				String op1=c[0];
				int v1=Integer.parseInt(c[1]);
				String op2=c[2];
				int v2=Integer.parseInt(c[3]);

				boolean can=false;
				for(int j=0; j<7; j++) {
					if(prev[j]!=0) {
						if(!can) can=true;
						if("+".equals(op1)) {
							newPrev[(j+v1)%7]=1;
						} else {
							newPrev[(j*v1)%7]=1;
						}
						if("+".equals(op2)) {
							newPrev[(j+v2)%7]=1;
						} else {
							newPrev[(j*v2)%7]=1;
						}
					}
				}
				if(!can) break;
				prev=newPrev;
			}
			ret.add(prev[0]==0 ? F : S);
		}
		return ret.stream().collect(java.util.stream.Collectors.joining("\n"));
	}

}
