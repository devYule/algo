import java.util.*;
class Solution {
    public String solution(long n, String[] bans) {
        int m=bans.length;
        long[] numb=new long[m];
        
        for(int i=0; i<m; i++) {
            numb[i]=destroy(bans[i]);
        }
        
        Arrays.sort(numb);
        
        long cur=n;
        for(int i=0; i<m; i++) {
            if(numb[i]<=cur) cur++;
        }
        return build(cur);
    }
    
    // 문자열 s의 번째 구하기. (26진법)
    long destroy(String s) {
        char[] c=s.toCharArray();
        long ret=0;
        long base=1;
        for(int i=c.length-1; i>=0; i--) {
            int order=toNumb(c[i]);
            ret+=base*order;
            base*=26;
        }
        return ret;
    }
    
    // num 번째 순서의 실제 문자열 구하기 (26진법)
    String build(long num) {
        StringBuilder sb=new StringBuilder();
        while(num>0) {
            int div=(int)(num%26);
            sb.append(toStr(div==0 ? 26 : div));
            num=num/26 + (div==0 ? -1 : 0);
        }
        return sb.reverse().toString();
    }
    
    String toStr(int n) {
        return String.valueOf((char)(n-1+'a'));
    }
    int toNumb(char c) {
        return c-'a'+1;
    }
}