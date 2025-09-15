import java.util.*;
class Solution {
    int[] rg={-1, 0, 1, 0};
    int[] cg={0, 1, 0, -1};
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        int[][] map=new int[101][101];
        init(map, rectangle);
        characterX*=2;
        characterY*=2;
        itemX*=2;
        itemY*=2;
        
        Deque<int[]> q=new ArrayDeque<>();
        q.add(new int[] {characterY, characterX});
        
        boolean[][] vis=new boolean[101][101];
        int round=0;
        while(!q.isEmpty()) {
            int size=q.size();
            for(int i=0; i<size; i++) {
                int[] curs=q.poll();
                int y=curs[0];
                int x=curs[1];
                
                if(y==itemY && x==itemX) return round/2;
                
                for(int j=0; j<4; j++) {
                    int ny=y+rg[j];
                    int nx=x+cg[j];
                    if(ny>=0 && nx>=0 && ny<101 && nx<101 && map[ny][nx]!=0 && !vis[ny][nx]) {
                        vis[ny][nx]=true;
                        q.addLast(new int[] {ny, nx});
                    }
                }
            }
            round++;
        }
        
        return 0;
    }
    
    void init(int[][] map, int[][] rectangle) {
        for(int[] r: rectangle) {
            int x1=r[0]*2;
            int y1=r[1]*2;
            int x2=r[2]*2;
            int y2=r[3]*2;
            map[y1][x1]=1;
            if(y2+1<101) map[y2+1][x1]=-1;
            if(x2+1<101) map[y1][x2+1]=-1;
            if(y2+1<101 && x2+1<101) map[y2+1][x2+1]=1;
        }
        
        for(int i=0; i<101; i++) {
            for(int j=1; j<101; j++) {
                map[i][j]+=map[i][j-1];
            }
        }
        for(int i=1; i<101; i++) {
            for(int j=0; j<101; j++) {
                map[i][j]+=map[i-1][j];
            }
        }
        for(int[] r: rectangle) {
            int x1=r[0]*2+1;
            int y1=r[1]*2+1;
            int x2=r[2]*2;
            int y2=r[3]*2;
            for(int i=y1; i<y2; i++) {
                for(int j=x1; j<x2; j++) {
                    map[i][j]=0;
                }
            }
        }
    } 
}