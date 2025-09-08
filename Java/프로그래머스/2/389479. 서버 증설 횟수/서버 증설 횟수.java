class Solution {
    public int solution(int[] players, int m, int k) {
        int set=0;
        int[] add=new int[24];
        int ret=0;
        for(int i=0; i<24; i++) {
            if(i>=k) set-=add[i-k];
            int need=players[i]/m;
            if(set<need) {
                int added=need-set;
                set+=added;
                ret+=added;
                add[i]=added;
            }
        }
        return ret;
    }
}