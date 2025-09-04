import java.util.*;
class Solution {
    /*
        시간/X = 시간내에 처리가능한 인원.
    */
    int[] time;
    int n;
    public long solution(int n, int[] times) {
        this.n=n;
        this.time=times;
        
        long hi=(long)n*Arrays.stream(times).max().orElse(0);
        return optimize(0, hi);
    }
    
    long optimize(long lo, long hi) {
        while(lo<=hi) {
            long mid=(lo+hi)>>>1L;
            if(decide(mid)) hi=mid-1;
            else lo=mid+1;
        }
        return lo;
    }
    
    boolean decide(long exp) {
        long cnt=0;
        for(int i=0; i<time.length; i++) {
            cnt+=exp/time[i];
            if(cnt>=n) return true;
        }
        return false;
    }
}