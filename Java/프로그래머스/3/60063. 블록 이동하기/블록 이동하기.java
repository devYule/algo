import java.util.*;
class Solution {
    // 상태: 기준칸 [y][x] 기준 가로일 경우, 세로일 경우.
    // pointer: y가 같다면 x가 더 큰것, x가 같다면 y가 더 큰것. (둘다 같은 경우, 둘다 다른경우는 존재하지 않음.)
    // pointer == [N-1][N-1] 이면 goal
    // init pointer=[0][1]

    final int MAX=(int)1e9;
    int[] rg={-1, 0, 1, 0};
    int[] cg={0, 1, 0, -1};
    int[][][] memo;
    int n;
    int[][] map;
    int ret;
    public int solution(int[][] board) {
        this.map=board;
        this.n=map.length;
        this.ret=n*n*2;
        this.memo=new int[n][n][2];
        for(int i=0; i<n; i++) for(int j=0; j<n; j++) Arrays.fill(memo[i][j], MAX);
        dfs(0, 1, 0, 0);
        return ret;
    }
    
    // cy: y좌표 상태
    // cx: x좌표 상태
    // dir: 방향 상태 (0: 가로, 1: 세로)
    void dfs(int cy, int cx, int dir, int time) {
        if(time>ret) return;
        if(cy==n-1 && cx==n-1) {
            this.ret=Math.min(ret, time);
            return;
        }
        if(memo[cy][cx][dir]<=time) return;
        memo[cy][cx][dir]=time;
        // 방향 이동 하지 않는 경우.
        for(int i=0; i<4; i++) {
            int ny=cy+rg[i];
            int nx=cx+cg[i];
            if(!valid(ny, nx) || !valid(ny-(dir==0 ? 0 : 1), nx-(dir==1 ? 0 : 1))) continue;
            dfs(ny, nx, dir, time+1);
        }
        
        // 방향 이동 하는 경우. (방향 이동만 하고, 다음으로 넘김. (방향이동 가중치: 1))
        if(dir==0) {
            // 지금 가로인 경우.
            // 1. 아래로 내리는 경우
            if(valid(cy+1, cx) && valid(cy+1, cx-1)) {
                // 좌측부를 오른쪽 아래로 내리는 경우.
                dfs(cy+1, cx, 1, time+1);
                // 우측부를 왼쪽 아래로 내리는 경우.
                dfs(cy+1, cx-1, 1, time+1);
            }
            // 2. 위로 올리는 경우 (좌표변경)
            if(valid(cy-1, cx) && valid(cy-1, cx-1)) {
                // 좌측부를 오른쪽 위로 올리는 경우 
                dfs(cy, cx, 1, time+1);
                // 우측부를 왼쪽 위로 올리는 경우
                dfs(cy, cx-1, 1, time+1);
            }
        } else {
            // 지금 세로인 경우.
            // 1. 좌측으로 변경하는 경우 (좌표변경)
            if(valid(cy, cx-1) && valid(cy-1, cx-1)) {
                // 상단부를 좌측 아래로 내리는 경우. 
                dfs(cy, cx, 0, time+1);
                // 하단부를 좌측 위로 올리는 경우.
                dfs(cy-1, cx, 0, time+1);
            }
            // 2. 우측으로 변경하는 경우
            if(valid(cy, cx+1) && valid(cy-1, cx+1)) {
                // 상단부를 우측 아래로 내리는 경우. 
                dfs(cy, cx+1, 0, time+1);
                // 하단부를 우측 위로 올리는 경우.
                dfs(cy-1, cx+1, 0, time+1);
            }
        }
    }
    boolean valid(int y, int x) {
        return y>=0 && x>=0 && y<n && x<n && map[y][x]==0;
    }
}