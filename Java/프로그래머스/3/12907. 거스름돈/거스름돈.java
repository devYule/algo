class Solution {
    public int solution(int n, int[] money) {
        int m=money.length;
        
        int[] dp=new int[n+1];
        dp[0]=1;
        
        for(int j=0; j<m; j++) {
            for(int i=money[j]; i<=n; i++) {
                dp[i]+=dp[i-money[j]];
            }
        }
        return dp[n];
    }
}