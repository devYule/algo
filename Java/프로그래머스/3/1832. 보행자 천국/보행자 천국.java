class Solution {
    /*
        m*n 지도
        0: 통행가능
        1: 통행금지
        2: 직진만 가능.
    */
    // 가능한 경로의 수.
    int MOD = 20170805;
    int[][] map;
    int[][][] memo;
    int m, n;
    int[] rg={0, 1};
    int[] cg={1, 0};
    public int solution(int m, int n, int[][] cityMap) {
        this.map=cityMap;
        this.m=n;
        this.n=m;
        this.memo=new int[m][n][2];
        for(int i=0; i<m; i++) for(int j=0; j<n; j++) java.util.Arrays.fill(memo[i][j], -1);
        memo[m-1][n-1][0]=1; memo[m-1][n-1][1]=1; 
        return find(0, 0, 0); 
    }
    
    int find(int y, int x, int prev) {
        if(memo[y][x][prev]!=-1) return memo[y][x][prev];
        
        int tmp=prev;
        if(map[y][x]!=2) tmp=-1;
        int ret=0;
        for(int i=0; i<2; i++) {
            if(tmp!=-1 && i!=tmp) continue;
            int ny=y+rg[i];
            int nx=x+cg[i];
            if(!valid(ny, nx)) continue;
            ret=(ret%MOD+find(ny, nx, i)%MOD)%MOD;
        }
        return memo[y][x][prev]=ret;
    }
    
    boolean valid(int y, int x) {
        return y>=0 && x>=0 && y<n && x<m && map[y][x]!=1;
    }
    
}