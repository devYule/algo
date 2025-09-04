import java.util.*;
class Solution {

    final int[] yg = {-1, 0, 1, 0};
    final int[] xg = {0, 1, 0, -1};
    
    
    int R, C;
    int[][] land;
    
    // [i] 번 그룹의 점수 총합
    int[] score;
    // [y][x] 석유의 그룹 속성. (음수.)
    int[][] group;
    
    boolean[][] oilVisited = new boolean[R][C];
    
    int nid;
    public int solution(int[][] land) {
        this.land = land;
        this.R = land.length;
        this.C = land[0].length;
        this.nid = 0;
        this.group = new int[R][C];
        this.score = new int[R*C];
        this.oilVisited = new boolean[R][C];
        init();
        
        return dfs(0);
    }
    
    int dfs(int width) {
        if(width == C) return 0;
        boolean[] visited = new boolean[nid + 1];
        int ret = 0;
        int x = width;
        for(int y = 0; y < R; y++) {
            if(land[y][x] == 0 || visited[-group[y][x]]) continue;
            visited[-group[y][x]] = true;
            ret += score[-group[y][x]];
        }
        return Math.max(ret, dfs(width + 1));
    }
    
    void init() {
        for(int i = 0; i < R; i++) 
            for(int j = 0; j < C; j++) {
                if(land[i][j] == 1 && !oilVisited[i][j]) {
                    bfs(i, j);
                }
            }
    }
    
    // 해당 석유의 점수 총합을 구한다.
    // 해당 석유와 연결된 모든 칸에 동일한 식별을 부여한다.
    void bfs(int y, int x) {
        nid++;
        int curIdx = -nid;
        
        Queue<int[]> q = new LinkedList<>();
        
        oilVisited[y][x] = true;
        q.add(new int[] {y, x});
        group[y][x] = curIdx;
        
        int score = 1;
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int cy = cur[0];
            int cx = cur[1];
            
            for(int i = 0; i < 4; i++) {
                int ny = cy + yg[i];
                int nx = cx + xg[i];
                if(ny < 0 || nx < 0 || ny >= R || nx >= C || land[ny][nx] == 0 || oilVisited[ny][nx]) continue;
                score++;
                oilVisited[ny][nx] = true;
                q.add(new int[] {ny, nx});
                group[ny][nx] = curIdx;
            }
        }
   
        this.score[nid] = score;
    }
    
}