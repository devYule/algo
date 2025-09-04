class Solution {
    // 기존 발판은 이동시 사라짐.
    // ㄴ> 이를 이용하여 떨어트리거나, 상대가 움직일 곳이 없으면 승리.
    
    int[] rg={-1, 0, 1, 0};
    int[] cg={0, 1, 0, -1};

    int n, m;
    public int solution(int[][] board, int[] aloc, int[] bloc) {
        // i*j 비트마스크 (최대 5*5)
        // 게임판의 상태: int
        // a사용자의 위치 상태: m*i+j
        // b사용자의 위치 상태: m*i+j
        // 전이: 상하좌우 움직임 (움직임 횟수 +1)
        // 목표: 게임판의 상태에서 최대로 움직일 수 있는 움직임 횟수.
        
        this.n=board.length;
        this.m=board[0].length;
        int mask=0;
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                if(board[i][j]==0) continue;
                int loc=cell(i, j);
                mask|=1<<loc;
            }
        }
        
        return Math.abs(simul(mask, aloc, bloc));
    }
    
    int simul(int mask, int[] turn, int[] counter) {
        int cella=cell(turn[0], turn[1]);
        int cellb=cell(counter[0], counter[1]);
        int y=turn[0];
        int x=turn[1];
        
        if(!safe(mask, y, x)) return 0;
        
        boolean canMv=false;
        int winBest=(int)1e9;
        int loseBest=0;
        
        int nextMask=remove(mask, y, x);
        for(int i=0; i<4; i++) {
            int ny=y+rg[i];
            int nx=x+cg[i];
            if(!valid(ny, nx) || !safe(mask, ny, nx)) continue;
            if(!canMv) canMv=true;

            int res=simul(nextMask, counter, new int[] {ny, nx});
            // 내가 이긴 경우.
            // 최대한 짧게 이겨야함.
            if(res>=0) winBest=Math.min(winBest, res+1);
            // 내가 진 경우.
            // 최대한 오래 끌어야함.
            else loseBest=Math.min(loseBest, res-1);
        }
        if(!canMv) return 0;
        if(winBest!=(int)1e9) return -winBest;
        return -loseBest;
    }
    
    // mask: 게임판 전체 비트마스크
    int remove(int mask, int y, int x) {
        int loc=cell(y, x);
        return mask & ~(1<<loc);
    }
    
    // [y, x] 가 여전히 1인지 검사.
    boolean safe(int mask, int y, int x) {
        int loc=cell(y, x);
        return (mask&(1<<loc))!=0;
    }
    
    int cell(int y, int x) {
        return y*m+x;
    }
    
    boolean valid(int y, int x) {
        return y>=0 && x>=0 && y<n && x<m;
    }
}