import java.util.*;
class Solution {
    char[][] map;
    int N, M;
    
    int[] rg={-1, 0, 1, 0};
    int[] cg={0, 1, 0, -1};
    public int solution(String[] maps) {
        
        this.N=maps.length;
        this.M=maps[0].length();
        this.map=new char[N][M];
        int[] s=new int[2];
        int[] e=new int[2];
        int[] l=new int[2];
        
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                map[i][j]=maps[i].charAt(j);
                
                int[] id=null;
                if(map[i][j]=='S') id=s;
                else if(map[i][j]=='E') id=e;
                else if(map[i][j]=='L') id=l;
                else continue;
                id[0]=i;
                id[1]=j;
            }
        }
        
        int toL=find(s[0], s[1], l[0], l[1]);
        if(toL==-1) return -1;        
        int toE=find(l[0], l[1], e[0], e[1]);
        if(toE==-1) return -1;
        return toL+toE;
    }
    
    int find(int sy, int sx, int ey, int ex) {
        boolean[][] visited=new boolean[N][M];
        Queue<int[]> q=new LinkedList<>();
        
        visited[sy][sx]=true;
        q.add(new int[] {0, sy, sx});
        
        while(!q.isEmpty()) {
            int[] curs=q.poll();
            int cury=curs[1];
            int curx=curs[2];
            int cost=curs[0];
            if(cury==ey && curx==ex) return cost;
            for(int i=0; i<4; i++) {
                int ny=cury+rg[i];
                int nx=curx+cg[i];
                if(!valid(ny, nx) || visited[ny][nx] || map[ny][nx]=='X') continue;
                q.add(new int[] {cost+1, ny, nx});
                visited[ny][nx]=true;
            }
        }
        return -1;
    }
    
    boolean valid(int y, int x) {
        return y>=0 && x>=0 && y<N && x<M;
    }
}