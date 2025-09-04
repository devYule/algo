class Solution {
    int[] rg={-1, 0, 1, 0};
    int[] cg={0, 1, 0, -1};
    int[][] map;
    int R, C;
    public int solution(int[][] clockHands) {
        this.map=clockHands;
        this.R=map.length;
        this.C=map[0].length;

        return simul(0);
    }
    
    int simul(int col) {
        if(col==C) {
            int c=check();
            return c==-1 ? (int)1e9 : c;
        }

        // [0][col] 시계를 돌리지 않는 경우.
        int ret=simul(col+1);
        // [0][col] 시계를 i번 돌리는 경우.
        for(int i=1; i<4; i++) {
            next(0, col, i);
            for(int j=0; j<4; j++) {
                int ny=rg[j];
                int nx=col+cg[j];
                if(!valid(ny, nx)) continue;
                next(ny, nx, i);
            }
            
            ret=Math.min(ret, simul(col+1)+i);
            
            back(0, col, i);
            for(int j=0; j<4; j++) {
                int ny=rg[j];
                int nx=col+cg[j];
                if(!valid(ny, nx)) continue;
                back(ny, nx, i);
            }
        }
        return ret;
    }
    
    int check() {
        int[][] copied=new int[R][C];
        for(int i=0; i<R; i++) {
            for(int j=0; j<C; j++) {
                copied[i][j]=map[i][j];
            }
        }
        int becor=0;
        for(int i=1; i<R; i++) {
            for(int j=0; j<C; j++) {
                if(copied[i-1][j]==0) continue;
                int gap=4-copied[i-1][j];
                becor+=gap;
                next(i, j, gap, copied);
                for(int k=0; k<4; k++) {
                    int ny=i+rg[k];
                    int nx=j+cg[k];
                    if(!valid(ny, nx)) continue;
                    next(ny, nx, gap, copied);
                }
            }
        }
        
        boolean flag=true;
        for(int i=0; i<R; i++) {
            for(int j=0; j<C; j++) {
                if(copied[i][j]!=0) {
                    flag=false;
                    break;
                }
            }
            if(!flag) break;
        }
        return flag ? becor : -1;
    }
    
    // dist 만큼 시계를 움직이고,
    // 12시에 맞춰졌는지 여부 반환.
    void next(int y, int x, int dist) {
        next(y, x, dist, this.map);
    }
    
    void next(int y, int x, int dist, int[][] map) {
        map[y][x]+=dist;
        map[y][x]%=4;
    }
    
    // dist 만큼 시계를 다시 되돌린다.
    void back(int y, int x, int dist) {
        map[y][x]-=dist;
        if(map[y][x]<0) {
            map[y][x]=4+map[y][x];
        }
    }
    
    boolean valid(int y, int x) {
        return y>=0&&x>=0&&y<R&&x<C;
    }
}