import java.util.*;

class Solution {
    int[] memo;
    int[] tops;
    final int MOD = 10007;
    public int solution(int n, int[] tops) {
        int answer = 0;
        this.memo = new int[n + 1];
        this.tops = tops;
        Arrays.fill(memo, -1);
        return find(n);
    }
    
    int find(int n) {
        if(n == 0) return 1;
        if(n == 1) return 3 + tops[n-1];
        if(memo[n] != -1) return memo[n];
        
        int left = find(n-1);
        int right = 3 + tops[n-1];
        int dup = (left * right) % MOD;
        int ret = (dup - find(n-2) + MOD) % MOD;
        
        return memo[n] = ret % MOD;
    }
}