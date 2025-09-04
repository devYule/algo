import java.util.*;
class Solution {
    int n;
    int[][] T;
    int[][] memo;
    public int solution(int k, int[][] dungeons) {
        n=dungeons.length;
        this.T=dungeons;
        this.memo=new int[k+1][1<<n];
        for(int i=0; i<=k; i++) Arrays.fill(memo[i], -1);
        
        return simul(k, 0);
    }
    
    // k: 남은 피로도
    // mask: 현재 던전 순회 현황.
    int simul(int k, int mask) {
        if(Integer.bitCount(mask)==n||k==0) return 0;
        if(memo[k][mask]!=-1) return memo[k][mask];
        int ret=0;
        for(int i=0; i<n; i++) {
            if((mask&1<<i)==0 && T[i][0]<=k) {
                // 던전 돌아보기.
                ret=Math.max(ret, simul(k-T[i][1], mask|1<<i)+1);
            }
        }
        return memo[k][mask]=ret;
    }
}