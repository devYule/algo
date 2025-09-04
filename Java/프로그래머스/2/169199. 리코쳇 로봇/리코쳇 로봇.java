class Solution {
    final int[] rg = {-1, 0, 1, 0};
    final int[] cg = {0, 1, 0, -1};
    final int MAX = (int)1e9;
    int N, M;
    String[] map;
    int sy;
    int sx;
    int gy;
    int gx;
    int[][] memo;
    public int solution(String[] board) {
        this.map = board;
        this.N = map.length;
        this.M = map[0].length();
        this.memo = new int[N][M];
    
        
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                memo[i][j] = MAX;
                if(board[i].charAt(j) == 'R') {
                    sy=i;
                    sx=j;
                }
                if(board[i].charAt(j) == 'G') {
                    gy=i;
                    gx=j;
                }
            }
        }
        
        dfs(sy, sx, 0);
        return memo[gy][gx] == MAX ? -1 : memo[gy][gx];
    }
    
    void dfs(int sy, int sx, int dist) {
        if(over(sy, sx) || memo[sy][sx] <= dist) return;
        memo[sy][sx] = dist;
        if(map[sy].charAt(sx) == 'G') return;
        
        for(int i=0; i<4; i++) {
            int diry = rg[i];
            int dirx = cg[i];
            int ny = sy + diry;
            int nx = sx + dirx;
            
            if(!over(ny, nx) && map[ny].charAt(nx) == 'D') continue;
            while(ny>=0 && nx>=0 && ny<N && nx<M) {
                if(over(ny+diry, nx+dirx) || map[ny+diry].charAt(nx+dirx) == 'D') break;
                ny+=diry;
                nx+=dirx;
            }
            dfs(ny, nx, dist+1);
        }
    }
    
    
    boolean over(int y, int x) {
        return y < 0 || x < 0 || y >= N || x >= M;
    }
}