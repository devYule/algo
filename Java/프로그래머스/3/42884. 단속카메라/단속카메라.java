class Solution {
    public int solution(int[][] routes) {
        java.util.Arrays.sort(routes, (a, b) -> a[0]==b[0] ? a[1]-b[1] : a[0]-b[0]);
        int ret=0;
        int hi=30001;
        int lo=-30001;
        for(int[] r: routes) {
            if(r[0]>lo || r[1]<hi) {
                ret++;
                hi=r[0];
                lo=r[1];
            } else {
                lo=Math.min(lo, r[1]);
                hi=Math.max(hi, r[0]);
            }
        }
        
        return ret;
    }
}