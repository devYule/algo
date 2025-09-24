import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			List<String> words=new ArrayList<>();
			List<String> token=new ArrayList<>();
			int round=0;
			while(true) {
				String line=br.readLine();
				if("-".equals(line)) { round=1; continue; }
				if("#".equals(line)) break;
				if(round==0) words.add(line);
				else token.add(line);
			}


			bw.write(
				String.valueOf(
					new Main().resolve(
						words, token
					)
				)
			);
			bw.flush();
		}
	}

	String resolve(List<String> words, List<String> tokens) {
		
		int[][] wordsCnt=new int[words.size()][26];
		for(int i=0; i<words.size(); i++) {
			String w=words.get(i);
			for(int j=0; j<w.length(); j++) {
				wordsCnt[i][w.charAt(j)-'A']++;
			}
		}

		List<String> ret=new ArrayList<>();
		for(String token: tokens) {
			boolean[] use=new boolean[26];
			int[] cnt=new int[26];
			int[] tokenCnt=new int[26];
			for(int i=0; i<token.length(); i++) {
				use[token.charAt(i)-'A']=true;
				tokenCnt[token.charAt(i)-'A']++;
			}
			for(int i=0; i<words.size(); i++) {
				boolean can=true;
				List<Integer> able=new ArrayList<>();
				for(int j=0; j<26; j++) {
					if(wordsCnt[i][j]>tokenCnt[j]) { can=false; break; }
					if(wordsCnt[i][j]>0) able.add(j);
				}
				if(can) {
					for(int a: able) cnt[a]++;
				}
			}
			int min=(int)1e9;
			int max=-1;
			for(int i=0; i<26; i++) {
				if(!use[i]) continue;
				min=Math.min(min, cnt[i]);
				max=Math.max(max, cnt[i]);
			}
			StringBuilder mins=new StringBuilder();
			StringBuilder maxs=new StringBuilder();
			for(int i=0; i<26; i++) {
				if(!use[i]) continue;
				if(cnt[i]==min) mins.append(String.valueOf((char)(i+'A')));
				if(cnt[i]==max) maxs.append(String.valueOf((char)(i+'A')));
			}
			ret.add(mins.toString() + " " + min + " " + maxs.toString() + " " + max);
		}
		return ret.stream().collect(java.util.stream.Collectors.joining("\n"));
	}


}
