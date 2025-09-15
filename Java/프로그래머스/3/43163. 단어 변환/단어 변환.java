import java.util.*;
class Solution {
    public int solution(String begin, String target, String[] words) {
        Deque<String> q=new ArrayDeque<>();
        q.add(begin);
        
        boolean[] used=new boolean[words.length];
        int round=0;
        while(!q.isEmpty()) {
            int size=q.size();
            for(int i=0; i<size; i++) {
                String cur=q.poll();
                if(cur.equals(target)) return round;
                for(int j=0; j<words.length; j++) {
                    if(used[j]) continue;
                    int ci=0, wi=0;
                    int diff=0;
                    while(ci<cur.length() && wi<words[j].length()) {
                        if(cur.charAt(ci)!=words[j].charAt(wi)) diff++;
                        if(diff>1) break;
                        ci++; wi++;
                    }
                    if(diff==1) {
                        q.addLast(words[j]);
                        used[j]=true;
                    }
                }
            }
            if(q.isEmpty()) return 0;
            round++;
        }
        return 0;
    }
}