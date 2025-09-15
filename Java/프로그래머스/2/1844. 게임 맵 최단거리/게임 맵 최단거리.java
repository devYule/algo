import java.util.*;
class Solution {
    int[] rg={-1, 0, 1, 0};
    int[] cg={0, 1, 0, -1};
    public int solution(int[][] map) {
        List<int[]> loc=new ArrayList<>();
        loc.add(new int[] {0, 0});
        int n=map.length;
        int m=map[0].length;
        boolean[][] vis=new boolean[n][m];
        vis[0][0]=true;
        
        int round=1;
        while(!loc.isEmpty()) {
            List<int[]> next=new ArrayList<>();
            for(int[] ax: loc) {
                if(ax[0]==n-1 && ax[1]==m-1) return round;
                for(int i=0; i<4; i++) {
                    int ny=ax[0]+rg[i];
                    int nx=ax[1]+cg[i];
                    if(ny>=0 && nx>=0 && ny<n && nx<m && map[ny][nx]==1 && !vis[ny][nx]) {
                        next.add(new int[] {ny, nx});
                        vis[ny][nx]=true;
                    }
                }
            }
            round++;
            loc=next;
        }
        return -1;
    }
    
}