import java.util.*;
class Solution {
    int Y, X, k;
    
    int[] rg={1, 0, 0, -1};
    int[] cg={0, -1, 1, 0};
    String[] T={"d", "l", "r", "u"};
    
    boolean[][][] visited;
    
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        this.Y=n;
        this.X=m;
        this.k=k;
        this.visited=new boolean[Y][X][k+1];
        
        int sy=x-1;
        int sx=y-1;
        int ey=r-1;
        int ex=c-1;
        
        String ret=buildStr(sy, sx, ey, ex, "");
        
        return ret==null ? "impossible" : ret;
    }
    
    String buildStr(int cy, int cx, int ey, int ex, String holder) {
        if(holder.length()==k) {
            if(cy==ey && cx==ex) return holder;
            return null;
        }
        int len=holder.length();
        
        visited[cy][cx][len]=true;
        
        String ret=null;
        for(int i=0; i<4; i++) {
            int ny=cy+rg[i];
            int nx=cx+cg[i];
            if(!valid(ny, nx) || visited[ny][nx][len+1]) continue;
            
            ret=buildStr(ny, nx, ey, ex, holder+T[i]);
            if(ret != null) return ret;
        }
        return ret;
    }
    
    boolean valid(int y, int x) {
        return y>=0 && x>=0 && y<Y && x<X;
    }
}