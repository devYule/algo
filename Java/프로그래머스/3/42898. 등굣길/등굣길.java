import java.util.*;
class Solution {
    
    /*
        오른쪽 | 아래쪽 으로만 움직임.
        물에 잠긴 지역 못감.
        최단경로 개수 구하기.
    */
    
    final int[] rg={0, 1};
    final int[] cg={1, 0};
    final int MOD=1_000_000_007;
    final int MAX=(int)1e9;
    boolean[][] P;
    int m, n;
    int[][] memo;
    public int solution(int m, int n, int[][] puddles) {
        this.m=m;
        this.n=n;
        this.memo=new int[n+1][m+1];
        this.P=new boolean[n+1][m+1];
        for(int i=0; i<=n; i++) Arrays.fill(memo[i], -1);
        for(int[] p: puddles) P[p[1]][p[0]]=true;
        return find(1, 1) % MOD;
    }
    
    int find(int y, int x) {
        if(y==n && x==m) return 1;
        if(memo[y][x] != -1) return memo[y][x];
        int ret=0;
        for(int i=0; i<2; i++) {
            int ny=y+rg[i];
            int nx=x+cg[i];
            if(!valid(ny, nx)) continue;
            ret+=find(ny, nx)%MOD;
        }
        return memo[y][x]=ret%MOD;
    }
 
    boolean valid(int y, int x) {
        return y>=1 && x>=1 && y<=n && x<=m && !P[y][x];
    }
    
}