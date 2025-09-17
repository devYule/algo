import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			String[] split=br.readLine().split("\\s");
			int n=Integer.parseInt(split[0]);
			int k=Integer.parseInt(split[1]);
			String[] words=new String[n];
			for(int i=0; i<n; i++) words[i]=br.readLine();

			bw.write(
				String.valueOf(
					new Main().resolve(
						n, k, words
					)
				)
			);
			bw.flush();
		}
	}

	boolean[] vis;
	int k;
	int ret;
	int[] wbit;

	int resolve(int n, int k, String[] words) {
		int[] cnt=new int[26];
		int emptyCnt=0;
		this.wbit=new int[n];
		for(int i=0; i<words.length; i++) {
			for(int j=0; j<words[i].length(); j++) {
				words[i]=words[i].replaceAll("[antic]", "");
				if(words[i].isEmpty()) emptyCnt++;
			}
			for(int j=0; j<words[i].length(); j++) {
				wbit[i]|=1<<(words[i].charAt(j)-'a');
				cnt[words[i].charAt(j)-'a']++;
			}
		}

		List<Integer> c=new ArrayList<>();
		for(int i=0; i<26; i++) {
			if(cnt[i]!=0) c.add(i);
		}
		this.k=k-5;
		if(this.k==0) return emptyCnt;
		this.vis=new boolean[1<<c.size()];
		this.ret=0;
		combi(c, 0);
		return this.ret;
	}

	void combi(List<Integer> cs, int state) {
		if(vis[state]) return;
		vis[state]=true;
		if(Integer.bitCount(state)==Math.min(cs.size(), k)) {
			int cnt=0;
			int selBit=0;
			for(int i=0; i<26; i++) {
				if((state&1<<i)!=0) selBit|=(1<<cs.get(i));
			}
			for(int bit: wbit) {
				if((bit&selBit)==bit) cnt++;
			}
			ret=Math.max(ret, cnt);
			return;
		}
		for(int i=0; i<cs.size(); i++) {
			if((state&1<<i)!=0) continue;
			combi(cs, state|1<<i);
		}
	}

}
