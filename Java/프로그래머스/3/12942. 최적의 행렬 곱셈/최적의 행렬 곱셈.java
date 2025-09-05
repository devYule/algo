class Solution {
    int[][] memo;
    int n;
    int[][] matrix;
    public int solution(int[][] matrix_sizes) {
        this.matrix=matrix_sizes;
        this.n=matrix.length;
        this.memo=new int[n][n];
        for(int i=0; i<n; i++) java.util.Arrays.fill(memo[i], -1);
        return find(0, n-1);
    }
    
    int find(int i, int j) {
        if(i>=j) return 0;
        if(memo[i][j]!=-1) return memo[i][j];
        int ret=(int)1e9;
        for(int k=i; k<j; k++) {
            int left=find(i, k);
            int right=find(k+1, j);
            int add=matrix[i][0]*matrix[k][1]*matrix[j][1];
            ret=Math.min(ret, left+right+add);
        }
        return memo[i][j]=ret;
    }
}
