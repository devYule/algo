import java.util.*;
class Solution {
    int[] rg={-1, 0, 1, 0};
    int[] cg={0, 1, 0, -1};
    public int solution(int[][] map) {
        Deque<int[]> loc=new ArrayDeque<>();
        loc.add(new int[] {0, 0});
        int n=map.length;
        int m=map[0].length;
        boolean[][] vis=new boolean[n][m];
        vis[0][0]=true;
        
        int round=1;
        while(!loc.isEmpty()) {
            int size=loc.size();
            for(int i=0; i<size; i++) {
                int[] ax=loc.pollFirst();
                if(ax[0]==n-1 && ax[1]==m-1) return round;
                for(int j=0; j<4; j++) {
                    int ny=ax[0]+rg[j];
                    int nx=ax[1]+cg[j];
                    if(ny>=0 && nx>=0 && ny<n && nx<m && map[ny][nx]==1 && !vis[ny][nx]) {
                        loc.addLast(new int[] {ny, nx});
                        vis[ny][nx]=true;
                    }
                }
            }
            round++;
        }
        return -1;
    }
    
}