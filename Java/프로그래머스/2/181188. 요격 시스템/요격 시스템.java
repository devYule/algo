import java.util.*;
class Solution {
    public int solution(int[][] targets) {
        Arrays.sort(targets, (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        int N = targets.length;
        int left = targets[0][0];
        int right = targets[0][1];
        int ret = 1;
        for(int i=1; i<N; i++) {
            int[] cur = targets[i];
            if(cur[0] < right) {
                left = cur[0];
                right = Math.min(right, cur[1]);
            } else {
                ret++;
                left = cur[0];
                right = cur[1];
            }
        }
        
        return ret;
    }
}