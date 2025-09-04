class Solution {
    // 모든아파트에 전파시키기 위한 "추가 기지국"의 "최소 개수"
    public int solution(int n, int[] stations, int w) {
        int lo=0, ret=0;
        int i=0;
        while(i<stations.length || lo<n) {
            int hi=0;
            if(i==stations.length) hi=n+1;
            else hi=stations[i++]-w;
            int left=(hi-lo-1);
            if(left>0) ret+=(left/(w*2+1)) + (left%(w*2+1)>0 ? 1 : 0);
            lo=hi+w*2;
        }
        return ret;
    }
}