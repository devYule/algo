import java.util.*;
class Solution {
    public int solution(int[] stones, int k) {
        int min=200_000_001;
        Deque<Integer> q=new LinkedList<>();
        
        for(int i=0; i<stones.length; i++) {
            while(!q.isEmpty() && stones[q.peekLast()] <= stones[i]) q.pollLast();
            q.addLast(i);
            if(q.peekFirst() < i-k+1) q.pollFirst();
            if(i>=k-1) min=Math.min(min, stones[q.peekFirst()]);
        }
        return min;
    }
}