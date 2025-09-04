import java.util.*;
class Solution {
    
    final int[] rg = {-1, 0, 1, 0};
    final int[] cg = {0, 1, 0, -1};
    final int MAX = (int) 1e9;
    
    int[][] map;
    int R, C;
    public int solution(int[][] maze) {
        this.R = maze.length;
        this.C = maze[0].length;
        int ry = 0;
        int rx = 0;
        int by = 0;
        int bx = 0;
        this.map = maze;
        for(int i=0; i<R; i++) {
            for(int j=0; j<C; j++) {
                if(maze[i][j] == 1) {
                    ry=i; rx=j;
                }
                if(maze[i][j] == 2) {
                    by=i; bx=j;
                }
            }
        }
        
        boolean[][] rv = new boolean[R][C];
        boolean[][] bv = new boolean[R][C];
        rv[ry][rx] = true;
        bv[by][bx] = true;
        
        int ret = move(ry, rx, by, bx, rv, bv);
        return ret==MAX ? 0 : ret;
    }
    
    int move(int ry, int rx, int by, int bx, boolean[][] rv, boolean[][] bv) {
        if(map[ry][rx] == 3 && map[by][bx] == 4) return 0;
        
        List<int[]> rcandi = null;
        List<int[]> bcandi = null;
        
        if(map[ry][rx] != 3) rcandi = getCandi(ry, rx, rv);
        else {
            rcandi = new ArrayList<>();
            rcandi.add(new int[]{ry, rx});
        }
        
        if(map[by][bx] != 4) bcandi = getCandi(by, bx, bv);
        else {
            bcandi = new ArrayList<>();
            bcandi.add(new int[]{by, bx});
        }
        
        int ret = MAX;
        for(int[] rc: rcandi) {
            for(int[] bc: bcandi) {
                // 수레 자리 서로 바꾸는 경우
                if(rc[0]==by && rc[1]==bx && bc[0]==ry && bc[1]==rx) continue;
                // 수레 같은 자리로 이동하는 경우
                if(rc[0]==bc[0] && rc[1]==bc[1]) continue;
                
                rv[rc[0]][rc[1]] = true;
                bv[bc[0]][bc[1]] = true;
                ret = Math.min(ret, move(rc[0],rc[1],bc[0],bc[1],rv,bv));
                rv[rc[0]][rc[1]] = false;
                bv[bc[0]][bc[1]] = false;
            }
        }
        return ret == MAX ? MAX : ret+1;
    }
    
    List<int[]> getCandi(int y, int x, boolean[][] v) {
        List<int[]> ret = new ArrayList<>();
        for(int i=0; i<4; i++) {
            int nr = y + rg[i];
            int nc = x + cg[i];
            if(nr<0 || nc<0 || nr>=R || nc>=C || map[nr][nc] == 5 || v[nr][nc]) continue;
            ret.add(new int[]{nr, nc});
        }
        return ret;
    }
}