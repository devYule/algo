import java.util.*;
class Solution {
    public int solution(int[] priorities, int location) {
        int n=priorities.length;
        int[] sorted=Arrays.copyOf(priorities, n);
        Arrays.sort(sorted);
        int maxi=n-1;
        
        Queue<int[]> q=new LinkedList<>();
        for(int i=0; i<n; i++) {
            q.add(new int[] {priorities[i], i});
        }
        
        int ret=1;
        while(!q.isEmpty()) {
            int[] curs=q.poll();
            int cur=curs[0];
            if(cur!=sorted[maxi]) {
                q.add(curs);
                continue;
            }
            if(curs[1]==location) return ret;
            maxi--;
            ret++;
        }
        
        return -1;
    }
}