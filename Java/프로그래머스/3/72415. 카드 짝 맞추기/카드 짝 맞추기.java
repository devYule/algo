import java.util.*;
class Solution {
    /*
        방향키     : 커서의 칸 이동
        ctrl+방향키: 인접한 유효한 카드로 한번에 커서이동
        enter    : 카드 오픈 (2장일 때 까지 유지)
        
        최소 조작횟수 구하기 (조작횟수: 각 커맨드를 입력한 횟수)
    */
    
    final int MAX=(int) 1e9;
    final int[] rg={-1, 0, 1, 0};
    final int[] cg={0, 1, 0, -1};
    
    int[][][][] memo;
    boolean[][][][] visited;
    int[][] map;
    int n, m;
    public int solution(int[][] board, int r, int c) {
        this.map=board;
        this.n=map.length;
        this.m=map[0].length;
        int cardMaxNum=-1;
        int mask=0;
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                cardMaxNum=Math.max(cardMaxNum, map[i][j]);
                if(map[i][j]==0) mask|=1<<toCell(i, j);
            }
        }
        
        // 7*16*(2^16)=7_340_032
        this.memo=new int[cardMaxNum+1][n+1][m+1][1<<(n*m)];
        this.visited=new boolean[cardMaxNum+1][n+1][m+1][1<<(n*m)];
        
        for(int i=0; i<memo.length; i++) {
            for(int j=0; j<memo[i].length; j++) {
                for(int k=0; k<memo[i][j].length; k++) {
                    Arrays.fill(memo[i][j][k], -1);
                }
            }
        }
        
        // 엔터 안치고 시작하는 경우.
        int ret=getMin(mask, r, c, 0);
        
        // 엔터 치고 시작하는 경우.
        int bu=map[r][c];
        map[r][c]=0;
        ret=Math.min(ret, getMin(mask | 1<<toCell(r, c), r, c, bu)+1);
        
        return ret;
    }
    
    // mask: 게임판의 상태
    // y, x: 좌표
    // state: 현재 카드오픈된 상태 (아무것도 되어있지 않다면 0)
    /*
        4방향 * 2개의 종류 (ctrl 유무)
        기저: count(mask)==16
        부분문제: 
            1. 커서를 이동한다.
            2. 이동한 칸의 값이 0이 아니라면, 해당 칸에 해당하는 mask를 0으로 바꾸고, 다음중 하나를 수행
                1. state가 0이라면, state를 현재 칸의 값으로 변경하여 다음 호출
                2. state가 0이 아니라면, state와 일치하는지 확인. 일치하면 mask에서 제거.
            3. 재귀호출
    */
    int getMin(int mask, int y, int x, int state) {
        if(Integer.bitCount(mask)==16) return 0;
        if(memo[state][y][x][mask]!=-1) return memo[state][y][x][mask];
        if(visited[state][y][x][mask]) return MAX;
        visited[state][y][x][mask]=true;
        
        int ret=MAX;
        for(int i=0; i<4; i++) {
            // !ctrl
            int ny=y+rg[i];
            int nx=x+cg[i];
            if(valid(ny, nx)) {
                // enter 를 누르지 않는 경우.
                ret=Math.min(ret, getMin(mask, ny, nx, state)+1);
                
                // 현재 칸이 카드칸인 경우에만,
                if(map[ny][nx]!=0) {
                    // 아직 오픈된 카드가 없다면,
                    if(state==0) {
                        // enter 눌러보기
                        int bu=map[ny][nx];
                        map[ny][nx]=0;
                        ret=Math.min(ret, getMin(mask | 1<<toCell(ny, nx), ny, nx, bu)+2);
                        map[ny][nx]=bu;
                    }
                    // 이미 오픈된 카드가 있다면
                    else {
                        // 카드가 같은 경우,
                        if(state==map[ny][nx]) {
                            // enter 눌러보기
                            int bu=map[ny][nx];
                            map[ny][nx]=0;
                            ret=Math.min(ret, getMin(mask | 1<<toCell(ny, nx), ny, nx, 0)+2);
                            map[ny][nx]=bu;
                        }
                    }
                    
                }
            }
            
            // ctrl
            while(valid(ny, nx) && map[ny][nx]==0) {
                ny+=rg[i];
                nx+=cg[i];
            }
            if(!valid(ny, nx)) {
                if(ny<0) ny=0;
                if(nx<0) nx=0;
                if(ny>=n) ny=n-1;
                if(nx>=m) nx=m-1;
            }
            
            // enter 를 누르지 않는 경우.
            ret=Math.min(ret, getMin(mask, ny, nx, state)+1);

            // 현재 칸이 카드칸인 경우
            if(map[ny][nx]!=0) {
                // 아직 오픈된 카드가 없다면,
                if(state==0) {
                    // enter 눌러보기
                    int bu=map[ny][nx];
                    map[ny][nx]=0;
                    ret=Math.min(ret, getMin(mask | 1<<toCell(ny, nx), ny, nx, bu)+2);
                    map[ny][nx]=bu;
                }
                // 이미 오픈된 카드가 있다면
                else {
                    // 카드가 같은 경우,
                    if(state==map[ny][nx]) {
                        // enter 눌러보기
                        int bu=map[ny][nx];
                        map[ny][nx]=0;
                        ret=Math.min(ret, getMin(mask | 1<<toCell(ny, nx), ny, nx, 0)+2);
                        map[ny][nx]=bu;
                    }
                }

            }
        }
        return memo[state][y][x][mask]=ret;
    }
    
    boolean valid(int y, int x) {
        return y>=0 && x>=0 && y<n && x<m;
    }
    
    int toCell(int y, int x) {
        return y*n+x;
    }

}