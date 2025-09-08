import java.util.*;
class Solution {
    public String solution(String S) {
        final String INV="invalid";
        int[] cnt=new int[26];
        boolean[] used=new boolean[26];
        
        for(int i=0; i<S.length(); i++) {
            if(!((S.charAt(i)>='A' && S.charAt(i)<='Z') || (lower(S.charAt(i))))) return INV;
            if(lower(S.charAt(i))) cnt[S.charAt(i)-'a']++;
        }
        List<int[]> border=new ArrayList<>();
        
        int i=0;
        while(i<S.length()) {
            int tmp=i;
            while(i<S.length() && !lower(S.charAt(i))) i++;
            if(i>=S.length()) {
                border.add(new int[] {tmp, S.length()});
                break;
            }
            
            char c=S.charAt(i);
            if(tmp==i && cnt[c-'a']>2) return INV;
            if(lower(S.charAt(tmp)) && cnt[c-'a']!=2) return INV;
            if(used[c-'a']) return INV;
            
            if(cnt[c-'a']==2) {
                if(tmp<i) border.add(new int[] {tmp, i});
                int lo=i+1;
              
                if(lo>=S.length() || lower(S.charAt(lo))) return INV;
                i++;
                char inner='Z';
                while(i<S.length() && S.charAt(i)!=c) {
                    if(inner!='Z' && i>lo && !lower(S.charAt(i)) && !lower(S.charAt(i-1))) return INV;
                    i++;
                    if(S.charAt(i)!=c && lower(S.charAt(i))) {
                        if(inner=='Z') inner=S.charAt(i);
                        if(inner!=S.charAt(i)) return INV;
                    }
                }
                if(i>=S.length() || lower(S.charAt(i-1))) return INV;
                if(inner!='Z') {
                    if(used[inner-'a'] || cnt[inner-'a']==0) return INV;
                    used[inner-'a']=true;
                    cnt[inner-'a']=0;
                }
                
                cnt[c-'a']-=2;
                border.add(new int[] {lo, i});
                i++;
            } else {
                if(tmp<i) border.add(new int[] {tmp, i-1});
                int lo=i-1;
                while(i<S.length() && S.charAt(i)==c) {
                    if(i==S.length()-1 || lower(S.charAt(i+1)) || lower(S.charAt(i-1))) return INV;
                    cnt[c-'a']--;
                    i+=2;
                }
                if(lo+1==i || i>S.length()) return INV;
                border.add(new int[] {lo, i});
            }
            if(cnt[c-'a']!=0) return INV;
            used[c-'a']=true;
        }
        
        StringBuilder sb=new StringBuilder();
        for(int[] b: border) {
            String tk=S.substring(b[0], b[1]);
            sb.append(tk);
            if(!tk.isEmpty()) sb.append(" ");
        }
        return sb.toString().replaceAll("[a-z]", "").replaceAll(" {2,}", " ").trim();
    }
    
    boolean lower(char c) {
        return c>='a' && c<='z';
    }
}
