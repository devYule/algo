class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long ret=0;
        int rd=0;
        int rp=0;
        for(int i=n-1; i>=0; i--) {
            int dist=(i+1)*2;
            int cnt=0;
            while(deliveries[i]>rd || pickups[i]>rp) {
                cnt++;
                rd+=cap;
                rp+=cap;
            }
            rd-=deliveries[i];
            rp-=pickups[i];
            ret+=dist*cnt;
        }
        return ret;
    }
}