import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			int T=Integer.parseInt(br.readLine());

			for(int r=0; r<T; r++) {
				char[] command=br.readLine().toCharArray();
				int n=Integer.parseInt(br.readLine());
				String snum=br.readLine();
				String[] split=snum.substring(1, snum.length()-1).split(",");
				int[] nums=new int[split.length];
				for(int i=0; i<n; i++) {
					nums[i]=Integer.parseInt(split[i]);
				}
				

				if(r!=0) bw.write("\n");
				bw.write(
					String.valueOf(
						new Main().resolve(
							command, n, nums
						)
					)
				);
			}
			bw.flush();
		}
	}

	final String FAIL="error";
	String resolve(char[] C, int n, int[] nums) {
		int[] lohi={0, n-1};

		int cursor=0;
		for(char c: C) {
			if(c=='R') cursor=(cursor+1)%2;
			else {
				if(lohi[0]>lohi[1]) return FAIL;
				lohi[cursor]+=(cursor==0 ? 1 : -1);
			}
		}

		List<Integer> ret=new ArrayList<>();
		for(int i=lohi[0]; i<=lohi[1]; i++) {
			ret.add(nums[i]);
		}

		if(cursor==1) Collections.reverse(ret);
		return "[" + ret.stream().map(String::valueOf).collect(java.util.stream.Collectors.joining(",")) + "]";
	}

}
