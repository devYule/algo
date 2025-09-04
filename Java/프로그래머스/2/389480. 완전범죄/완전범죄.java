import java.util.*;
class Solution {
    int N, M, K;
    int[][] info;
    final int MAX = (int) 1e9;
    
    int[][] memo;
    public int solution(int[][] info, int n, int m) {
        this.info = info;;
        this.K = info.length;
        this.N = n;
        this.M = m;
        
        this.memo = new int[K][M];
        for(int i = 0; i < K; i++) Arrays.fill(memo[i], -1);
        
        
        int ret = still(0, 0);
        return ret >= n ? -1 : ret;
    }
    
    int still(int idx, int bs) {
        if(bs >= M) return MAX;
        if(idx == K) return 0;
        if(memo[idx][bs] != -1) return memo[idx][bs];
       
		int ret = still(idx + 1, bs) + info[idx][0];
        ret = Math.min(ret, still(idx + 1, bs + info[idx][1]));
        return memo[idx][bs] = ret;
    }
}