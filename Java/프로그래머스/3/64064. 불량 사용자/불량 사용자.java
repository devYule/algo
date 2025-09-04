import java.util.*;
class Solution {
    // banned_id 의 각 인덱스별로 포함 가능한 숫자중 최소값.
    
    // 명확한 ban 대상인 사람들의 수.
    
    // 조합.
    
    
    @SuppressWarnings("unchecked")
    public int solution(String[] user_id, String[] banned_id) {
        // 글자수 대로 분류.
        List<Integer>[] lens=new ArrayList[9];
        for(int i=1; i<9; i++) lens[i]=new ArrayList<>();
        for(int i=0; i<user_id.length; i++) {
            String cur=user_id[i];
            lens[cur.length()].add(i);
        }
        List<Integer>[] contain=new ArrayList[banned_id.length];
        for(int i=0; i<banned_id.length; i++) contain[i]=new ArrayList<>();
        
        // banned_id 의 각 인덱스별로 포함 가능한 숫자중 최소값.
        for(int i=0; i<banned_id.length; i++) {
            String ban=banned_id[i];
            int banLen=ban.length();
            List<Integer> candi=lens[banLen];
            
            
            for(int j=candi.size()-1; j>=0; j--) {
                boolean can=true;
                String c=user_id[candi.get(j)];
                for(int k=0; k<ban.length(); k++) {
                    if(ban.charAt(k)=='*') continue;
                    if(ban.charAt(k)!=c.charAt(k)) {
                        can=false;
                        break;
                    }
                }
                if(can) contain[i].add(candi.get(j));
            }
        }
        
        return cnt(0, new boolean[1<<8], contain, 0);
    }
    
    int cnt(int idx, boolean[] vis, List<Integer>[] lst, int mask) {
        if(vis[mask]) return 0;
        vis[mask]=true;
        if(idx==lst.length && Integer.bitCount(mask)==lst.length) return 1;
        
        List<Integer> cand=lst[idx];
        int ret=0;
        for(int i=0; i<cand.size(); i++) {
            ret+=cnt(idx+1, vis, lst, mask | 1<<cand.get(i));
        }
        return ret;
    }
}