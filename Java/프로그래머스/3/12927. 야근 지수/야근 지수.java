import java.util.*;
class Solution {
    public long solution(int n, int[] works) {
        PriorityQueue<Integer> q=new PriorityQueue<>(Comparator.reverseOrder());
        for(int w: works) q.add(w);
        while(n>0) { q.add(q.poll()-1); n--; }
        long ret=0;
        while(!q.isEmpty()) { 
            int num=q.poll(); 
            if(num>0) ret+=(long)num*num; 
        }
        return ret;
    }
}