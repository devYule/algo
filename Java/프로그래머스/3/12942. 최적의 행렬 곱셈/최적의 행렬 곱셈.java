class Solution {
    public int solution(int[][] matrix_sizes) {
        int n=matrix_sizes.length;
        int[][] dp=new int[n][n];
        for(int gap=1; gap<n; gap++) {
            for(int i=0; i+gap<n; i++) {
                int j=i+gap;
                int best=(int)1e9;
                for(int k=i; k<j; k++) {
                    best=Math.min(best, dp[i][k]+dp[k+1][j]+(matrix_sizes[i][0]*matrix_sizes[k][1]*matrix_sizes[j][1]));
                }
                dp[i][j]=best;
            }
        }
        return dp[0][n-1];
    }

}