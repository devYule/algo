import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			int n=Integer.parseInt(br.readLine());
			int[] can=Arrays.stream(br.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();
			int m=Integer.parseInt(br.readLine());
			int[] weights=Arrays.stream(br.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();

			bw.write(
				String.valueOf(
					new Main().resolve(
						n, can, m, weights
					)
				)
			);
			bw.flush();
		}
	}

	int resolve(int n, int[] limits, int m, int[] weights) {

		TreeMap<Integer, Integer> tree=new TreeMap<>();
		for(int w: weights) {
			tree.computeIfAbsent(w, k->0);
			tree.put(w, tree.get(w)+1);
		}

		int round=0;
		while(!tree.isEmpty()) {
			round++;
			boolean moved=false;
			for(int i=n-1; i>=0; i--) {
				Map.Entry<Integer, Integer> ent=tree.floorEntry(limits[i]);
				if(ent==null) continue;
				moved=true;
				if(ent.getValue()==1) tree.remove(ent.getKey());
				else tree.put(ent.getKey(), ent.getValue()-1);
			}
			if(tree.isEmpty()) return round;
			if(!moved) return -1;
		}
		return round;
	}
}
