import java.util.*;
class Solution {
    int[] rg={-1, 0, 1, 0};
    int[] cg={0, 1, 0, -1};
    
    String[] map;
    int N, M;
    public int[] solution(String[] maps) {
        this.map=maps;
        List<Integer> ret=new ArrayList<>();
        this.N=maps.length;
        this.M=maps[0].length();
        boolean[][] visited=new boolean[N][M];
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(visited[i][j] || map[i].charAt(j)=='X') continue;
                ret.add(getDays(i, j, visited));
            }
        } 
        
        
        if(ret.isEmpty()) ret.add(-1);
        else ret.sort((a, b) -> a-b);
        return ret.stream().mapToInt(a -> a).toArray();
    }
    
    int getDays(int y, int x, boolean[][] visited) {
        visited[y][x]=true;
        int ret=map[y].charAt(x)-'0';
        for(int i=0; i<4; i++) {
            int ny=y+rg[i];
            int nx=x+cg[i];
            if(!valid(ny, nx) || visited[ny][nx] || map[ny].charAt(nx)=='X') continue;
            ret+=getDays(ny, nx, visited);
        }
        return ret;
    }
    
    boolean valid(int y, int x) {
        return y>=0 && x>=0 && y<N && x<M;
    }
}