import java.util.*;
class Solution {
    /*
        111 을 처음 만나는 위치에 110을 넣으면 순서가 더 빨라짐.
        111이 없다면 그냥 두는게 제일 빠름.
            ex: 101110 -> 101101
                10110 -> 10110
                
        110 이 여러개라면?
            ex: 111110110 -> 110110111
                10110110 -> 10110110
        다 옮겨야함.
        
        110 이 더 먼저 나온다면?
            ex: 110111110 -> 110110111
                11010111
                
        결론: 110 을 111 보다 앞으로 다 뺀다.
    */
    public String[] solution(String[] s) {
        String[] ret=new String[s.length];
        int ri=0;
        for(String cur: s) {
            StringBuilder tmp=new StringBuilder();
            int count=0;
            for(int i=0; i<cur.length(); i++) {
                tmp.append(String.valueOf((char)cur.charAt(i)));
                int len=tmp.length();
                if(tmp.length()>=3) {
                    if(tmp.charAt(len-3)=='1' && tmp.charAt(len-2)=='1' && tmp.charAt(len-1) =='0') {
                        tmp.setLength(len-3);
                        count++;
                    }
                }
            }
            
            int pos=-1;
            for(int i=0; i<=tmp.length(); i++) {
                int j=i;
                boolean hasZero=false;
                while(j<i+3 && j<tmp.length()) {
                    if(tmp.charAt(j)=='0') {
                        hasZero=true;
                        break;
                    }
                    j++;
                }
                if(!hasZero) {
                    pos=i;
                    break;
                }
            }
            ret[ri++]=pos==-1 ? cur : tmp.substring(0, pos)+"110".repeat(count)+tmp.substring(pos);
        }
        return ret;
    }
    
}