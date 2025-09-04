import java.util.*;
class Solution {
    public int solution(int k, int[] tangerine) {
        int n=tangerine.length;
        int max=Arrays.stream(tangerine).max().orElse(0);
        int[] cnt=new int[max+1];
        for(int i=0; i<n; i++) {
            cnt[tangerine[i]]++;
        }
        Arrays.sort(cnt);
        int m=cnt.length;
        
        int ret=0;
        for(int i=m-1; i>=0; i--) {
            k-=cnt[i];
            ret++;
            if(k<=0) break;
        }
        return ret;
    }
}