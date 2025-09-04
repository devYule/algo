import java.util.*;
class Solution {
    public int solution(int[] cards) {
        int n=cards.length;
        List<Integer> grp=new ArrayList<>();
        int selected=0;
        for(int i=0; i<n; i++) {
            if(cards[i]==-1) continue;
            int cur=i;
            int cnt=0;
            while(cards[cur]!=-1) {
                cnt++;
                int next=cards[cur]-1;
                cards[cur]=-1;
                cur=next;
            }
            grp.add(cnt);
        }
        grp.add(0);
        grp.sort((a, b) -> b-a);
        return grp.get(0)*grp.get(1);
    }
}