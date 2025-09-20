import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			bw.write(
				String.valueOf(
					new Main().resolve(
						Integer.parseInt(br.readLine())
					)
				)
			);
			bw.flush();
		}
	}

	int resolve(int n) {
		List<Integer> all=new ArrayList<>();
		all.add(64);

		while(true) {
			int sum=0;
			for(int i=0; i<all.size(); i++) sum+=all.get(i);
			if(sum==n) return all.size();
			int min=all.get(all.size()-1);
			int div=min/2;
			sum-=div;
			if(sum==n) return all.size();
			all.remove(all.size()-1);
			all.add(div);
			if(sum<n) all.add(div);
		}
	}
}
