class Solution {
    final int MAX=(int)1e9;
    int[] rg={-1, 0, 1, 0};
    int[] cg={0, 1, 0, -1};
    int[][] map;
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        map=new int[101][101];
        setPoint(rectangle);
        map[characterY*2][characterX*2]=-1;
        map[itemY*2][itemX*2]=-2;
        
        return find(characterY*2, characterX*2, new boolean[101][101])/2;
    }
    
    int find(int y, int x, boolean[][] visited) {
        if(map[y][x]==-2) return 0;
        
        int ret=MAX;
        
        for(int i=0; i<4; i++) {
            int ny=y+rg[i];
            int nx=x+cg[i];
            if(!valid(ny, nx) || map[ny][nx]==0 || visited[ny][nx]) continue;
            visited[ny][nx]=true;
            ret=Math.min(ret, find(ny, nx, visited)+1);
            visited[ny][nx]=false;
        }
        return ret;
    }
    
    boolean valid(int y, int x) {
        return y>=0 && x>=0 && y<101 && x<101;
    }   
    
    // 선긋기
    // [x1, y1, x2, y2];
    // ㄴ> [y1][x1]~[y1][x2], [y1][x1]~[y2][x1], [y2][x1]~[y2][x2], [y1][x2]~[y2][x2];
    void setPoint(int[][] r) {
        for(int[] ax: r) {
            int y1=ax[1]*2;
            int x1=ax[0]*2;
            int y2=ax[3]*2;
            int x2=ax[2]*2;
            
            for(int i=y1; i<=y2; i++) {
                map[y1][x1]=2;
                map[y1][x2]=2;
                map[y2][x1]=2;
                map[y2][x2]=2;
                for(int j=x1; j<=x2; j++) {
                    if(i==y1 || i==y2 || j==x1||j==x2) map[i][j]=map[i][j]>0 ? 2 : 1;
                }
            }
        }
        // 내부 길 다 지우기.
        for(int[] ax: r) {
            int y1=ax[1]*2+1;
            int x1=ax[0]*2+1;
            int y2=ax[3]*2-1;
            int x2=ax[2]*2-1;
            
            for(int i=y1; i<=y2; i++) {
                for(int j=x1; j<=x2; j++) {
                    map[i][j]=0;
                }
            }
        }
    }
}