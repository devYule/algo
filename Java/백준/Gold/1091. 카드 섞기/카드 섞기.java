import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			int n=Integer.parseInt(br.readLine());
			int[] goal=Arrays.stream(br.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();
			int[] shuffle=Arrays.stream(br.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();

			bw.write(
				String.valueOf(
					new Main().resolve(
						n, goal, shuffle
					)
				)
			);
			bw.flush();
		}
	}

	int[] card;
	int resolve(int n, int[] own, int[] shuffle) {
		this.card=new int[n];
		for(int i=0; i<n; i++) card[i]=i;

		int cnt=0;
		Set<String> vis=new HashSet<>();
		while(true) {
			boolean match=true;
			int place=1;
			StringBuilder sb=new StringBuilder();
			for(int i=0; i<n; i++) {
				int owner=i%3;
				if(match && own[card[i]]!=owner) { match=false; }
				sb.append(card[i]);
			}
			if(match) return cnt;
			String key=sb.toString();
			if(vis.contains(key)) return -1;
			vis.add(key);
			cnt++;
			shuffle(shuffle);
		}
	}

	void shuffle(int[] rule) {
		int[] newCard=new int[card.length];
		for(int i=0; i<rule.length; i++) {
			// i번에 있던 카드는 S[i] 로 이동.
			newCard[rule[i]]=card[i];
		}
		// 0 1 2 => 0 1 2
		// 2 0 1 => [0: 2]  [1: 0]  [2: 1]
		this.card=newCard;
	}


}
