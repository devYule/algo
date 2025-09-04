import java.util.stream.*;
import java.util.*;
class Solution {
    List<String[]> clear;
    List<String[]> prob;
    public String[] solution(String[] expressions) {
        this.clear = new ArrayList<>();
        this.prob = new ArrayList<>();
        String[] answer = {};
        int candi = getCandi(expressions);
        
        // 이미 계산이 완료된 수식 순회하며 candi 에서 확정될 수 있는 숫자들 구하기
        int mask = saniCandi(candi);
        String[] ret = searchProb(mask);
        
        return ret;
    }
    
    // mask 의 켜진비트로 X가 포함된 식을 계산.
    // 구해지는 해가 유일하면 해당 해를 X대신 치환.
    // 해가 2개 이상이면 ? 로 X를 치환.
    String[] searchProb(int mask) {
        int pos = 0;
        while((mask & 1 << pos) == 0) {
            pos++;
        }
        boolean isOnly = (mask & (~(1 << pos))) == 0;
        
        String[] ret = new String[prob.size()];
        
        // prob[i] 의 m진법으로 계산된 해를 저장.
        // 한번도 다른적 없으면 해로 설정.
        // 한번이라도 다르면 ? 로 설정.
        int[] prev = new int[prob.size()];
        Arrays.fill(prev, -1);
        
        for(int i = 0; i < prob.size(); i++) {
            if(prev[i] == -2) continue;
            String[] s = prob.get(i);
            int x = Integer.parseInt(s[0]);
            int y = Integer.parseInt(s[2]);
            int f = s[1].equals("-") ? 0 : 1;
            
            
            for(int m = pos; m < 10; m++) {
				if((mask & 1<<m) == 0) continue;
                
                int xm = convert10(m, x);
                int ym = convert10(m, y);
                int calced = f == 1 ? xm + ym : xm - ym;
                int cm = convertM(m, calced);
                
                if(prev[i] == -1) prev[i] = cm;
                else if(prev[i] != cm) {
                    prev[i] = -2;
                    break;
                }
            }
            if(prev[i] >= 0) s[4] = String.valueOf(prev[i]);
            else s[4] = "?";
            ret[i] = Arrays.stream(s).collect(Collectors.joining(" "));
        }
        
        return ret;
    }
    
    int saniCandi(int mask) {
        int pos = 0;
        int bit = mask;
        while((bit & 1 << pos) == 0) {
            pos++;
        }
        
        int ret = mask;
        for(int i = 0; i < clear.size(); i++) {
            String[] s = clear.get(i);
            int x = Integer.parseInt(s[0]);
            int y = Integer.parseInt(s[2]);
            int f = s[1].equals("-") ? 0 : 1;
            int s4 = Integer.parseInt(s[4]);
            // 진법 후보
            for(int m = pos; m < 10; m++) {
                if((ret & 1 << m) == 0) continue;

                // x -> 10진법
                // y -> 10진법
                // 계산
                // 계산 결과를 다시 m진법으로 바꿨을 때,
                // s[4] 와 같은 숫자면 ok
                
                int xm = convert10(m, x);
                int ym = convert10(m, y);
                int calced = f == 1 ? xm + ym : xm - ym;
                int cm = convertM(m, calced);

                if(cm != s4) {
                    ret &= (~(1 << m));
                }
            }
        }
        return ret;
    }
    
    int getCandi(String[] exp) {
        int[] mark = new int[10];
        int max = 0;
        for(String e: exp) {
            String[] s = e.split(" ");
			max = Math.max(max, markMaxSingle(Integer.parseInt(s[0]), mark));
            max = Math.max(max, markMaxSingle(Integer.parseInt(s[2]), mark));
            if(s[4].equals("X")) prob.add(s);
            else {
                max = Math.max(max, markMaxSingle(Integer.parseInt(s[4]), mark));
                clear.add(s);
            }
        }
        
        int ret = 0;
        for(int i = max + 1; i < 10; i++) {
            ret |= (1 << i);
        }
        return ret;
    }
    
    int markMaxSingle(int n, int[] mark) {
        int m = 0;
        while(n > 0) {
            m = Math.max(m, n % 10);
            mark[n % 10]++;
            n /= 10;
        }
        return m;
	}
    
    // 숫자 T 를 M진법에서 10진법 표기법으로 변경한다.
    // M: 진법
    // T: 계산할 수
    int convert10(int M, int T) {
        int B = 0;
        int ret = 0;
        while(T > 0) {
            ret += (T % 10) * ((int) Math.pow(M, B++));
            T /= 10;
        }
        return ret;
    }
    
    // 숫자 T를 10진법에서 M진법 표기법으로 변경한다.
    int convertM(int M, int T) {
        int B = 1;
        int ret = 0;
		while(T > 0) {
            ret += T % M * B;
            T /= M;
            B *= 10;
        }
        return ret;
    }
}