import java.util.*;
class Solution {
    
    final int mod = 1_000_000_007;
    int[] memo;
    public int solution(int n) {
        this.memo = new int[n + 1];
        Arrays.fill(memo, -1);
        
        return dfs(n);
    }
    
    int dfs(int n) {
        if(n==0) return 0;
        if(n==1) return 1;
        if(n==2) return 3;
        if(n==3) return 10;
        if(n==4) return 23;
        if(n==5) return 62;
        if(n==6) return 170;
        if(memo[n] != -1) return memo[n];
        
        long l1 = dfs(n-1) %mod;
        long l2 = dfs(n-2)*2L %mod;
        long l3 = dfs(n-3)*6L %mod;
        long l4 = dfs(n-4) %mod;
        long l6 = dfs(n-6) %mod;
        
        return memo[n] = (int) (((l1+l2+l3+l4)-l6 + mod) %mod);
    }
    
    
}