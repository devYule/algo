import java.util.*;
class Solution {
    /*
          다 철 돌
       다
       철
       돌
    */
    final int[][] S = {
        {1, 1, 1},
        {5, 1, 1},
        {25, 5, 1}
    };
    
    final int MAX = (int) 1e9;
    
    int[] picks;
    String[] minerals;
    
    int[][] memo;
    
    int N, M;
    
    public int solution(int[] picks, String[] minerals) {
        this.N = minerals.length;
        this.M = picks.length;
        this.picks = picks;
        this.minerals = minerals;
        this.memo = new int[N][1<<3*3];
        for(int i = 0; i < N; i++) Arrays.fill(memo[i], -1);
        
        int mask = 0;
        int pos = 3;
        for(int i = 0; i < M; i++) {
            int cnt = picks[i];
            mask |= (cnt << (i*pos));
        }
        
        return getMin(mask, 0);
    }
    
    int getMin(int ps, int mi) {
        if(mi >= N || ps == 0) return 0;
        if(memo[mi][ps] != -1) {
            System.out.println("hit");
            return memo[mi][ps];
        }
        
        int ret = MAX;
        for(int i = 0; i < M; i++) {
            if(get(ps, i) == 0) continue;
            int add = 0;
            int j = 0;
            for(; j<5; j++) {
                if(mi + j >= N) break;
                add += S[i][minToNum(minerals[mi + j])];
            }
            ret = Math.min(ret, getMin(dim(ps, i), mi+j) + add);
        }
        return memo[mi][ps] = ret;
    }
    
    
    
    int minToNum(String min) {
        if("diamond".equals(min)) return 0;
        if("iron".equals(min)) return 1;
        if("stone".equals(min)) return 2;
        return -1;
    }
    
    int get(int mask, int n) {
        int bit = mask >>> n*3;
        bit &= ((1<<3)-1);
        return bit;
    }
    
    int dim(int mask, int n) {
        int bit = mask >>> n*3;
        bit &= ((1<<3)-1);
        bit-=1;
        int z = 0b111 << n*3;
        return (mask & ~z) | bit << n*3;
    }
    
    int acc(int mask, int n) {
        int bit = mask >>> n*3;
        bit &= ((1<<3)-1);
        bit+=1;
        int z = 0b111 << n*3;
        return (mask & ~z) | bit << n*3;
    }
}