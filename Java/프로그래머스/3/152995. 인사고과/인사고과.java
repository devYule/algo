import java.util.*;
class Solution {
    
    int[][] sorted;
    public int solution(int[][] scores) {
        int n=scores.length;
        this.sorted=new int[n][3];
        for(int i=0; i<n; i++) {
            int[] s=scores[i];
            this.sorted[i]=new int[] {s[0], s[1], i};
        }

        Arrays.sort(sorted, (a, b) -> a[0]==b[0] ? a[1]-b[1] : b[0]-a[0]);
        
        int maxb=-1;
        
        for(int i=0; i<n; i++) {
            int[] cur=sorted[i];
            if(cur[1]<maxb) {
                scores[cur[2]][0]=-1;
                scores[cur[2]][1]=-1;
                continue;
            }
            maxb=Math.max(maxb, cur[1]);
        }
        
        int[] base=scores[0];
        if(base[0]==-1) return -1;
        int ret=1;
        for(int i=1; i<n; i++) {
            if(scores[i][0]+scores[i][1] > base[0]+base[1]) ret++;
        }
        
        return ret;
    }
}