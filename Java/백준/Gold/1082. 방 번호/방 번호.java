import java.math.*;
import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			int n=Integer.parseInt(br.readLine());
			int[] prices=Arrays.stream(br.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();
			int limit=Integer.parseInt(br.readLine());

			bw.write(
				String.valueOf(
					new Main().resolve(
						n, prices, limit
					)
				)
			);
			bw.flush();
		}
	}

	String[][] memo;
	int n, prices[], limit;
	BigInteger resolve(int n, int[] prices, int limit) {
		this.n=n; this.prices=prices; this.limit=limit;
		this.memo=new String[n][limit+1];
		return new BigInteger(find(n-1, limit));
	}
	String find(int idx, int rest) {
		if(idx==0) return rest<prices[idx] ? "" : "0".repeat(rest/prices[idx]);
		if(memo[idx][rest]!=null) return memo[idx][rest];
		int price=prices[idx];
		int lim=rest/price;
		
		BigInteger ret=BigInteger.valueOf(-1);
		StringBuilder sb=new StringBuilder();
		for(int i=0; i<=lim; i++) {
			int use=price*i;
			if(i>0) sb.append(idx);
			for(int j=0; j<idx; j++) {
				String strNum=(sb.length()>0 ? sb.toString() : "")+find(j, rest-use);
				if(strNum.isEmpty()) continue;
				ret=ret.max(new BigInteger(strNum));
			}
			
		}
		
		return memo[idx][rest]="-1".equals(ret.toString()) ? "" : ret.toString();
	}
}
