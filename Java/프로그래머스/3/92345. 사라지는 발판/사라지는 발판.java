class Solution {
    final int MAX=(int)1e9;
    final int[] rg={-1, 0, 1, 0};
    final int[] cg={0, 1, 0, -1};
    int[][] map;
    public int solution(int[][] board, int[] aloc, int[] bloc) {
        this.map=board;
        return Math.abs(game(aloc, bloc));
    }
    
    int game(int[] a, int[] b) {
        if(map[a[0]][a[1]]==0) return 0;
        map[a[0]][a[1]]=0;
        int win=-MAX;
        int lose=-MAX;
        boolean moved=false;
        for(int i=0; i<4; i++) {
            int ny=a[0]+rg[i];
            int nx=a[1]+cg[i];
            if(!valid(ny, nx)) continue;
            if(!moved) moved=true;
            int res=game(b, new int[] {ny, nx});
            if(res<=0) win=Math.max(win, res-1);
            else lose=Math.max(lose, res+1);
        }
        map[a[0]][a[1]]=1;
        return moved ? win==-MAX ? -lose : -win : 0;
    }
    
    boolean valid(int y, int x) { return y>=0 && x>=0 && y<map.length && x<map[0].length && map[y][x]!=0; }
}