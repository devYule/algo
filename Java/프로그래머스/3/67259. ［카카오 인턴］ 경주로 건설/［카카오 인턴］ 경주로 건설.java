import java.util.*;
class Solution {
    // 직선: 100원, 코너: 500원
    // 직전 y, x가 rg[i], cg[i] 중 하나라도 다르면 코너.
    // 시작은 직진.
    int[] rg={-1, 0, 1, 0};
    int[] cg={0, 1, 0, -1};
    int[][] map;
    int n;
    public int solution(int[][] board) {
        this.map=board;
        this.n=map.length;
        return zobfs(0, 0);
    }
    
    int zobfs(int sy, int sx) {
        // [0]: y, [1]: x, [2]: prevDir [3]:cost
        // : prevDir: 짝수: 행이동, 홀수: 열이동.
        PriorityQueue<int[]> q=new PriorityQueue<>((a, b) -> a[3]-b[3]);
        int[][] dist=new int[n*n][4];
        for(int i=0; i<n*n; i++) Arrays.fill(dist[i], (int) 1e9);
        dist[cell(sy, sx)][0]=0;
        dist[cell(sy, sx)][1]=0;
        dist[cell(sy, sx)][2]=0;
        dist[cell(sy, sx)][3]=0;
        q.add(new int[] {sy, sx, 1, 0});
        q.add(new int[] {sy, sx, 2, 0});
        
        
        while(!q.isEmpty()) {
            int[] curs=q.poll();
            int cury=curs[0];
            int curx=curs[1];
            int prevDir=curs[2];
            int cost=curs[3];
            
            if(cost>dist[cell(cury, curx)][prevDir]) continue;
            
            for(int i=0; i<4; i++) {
                int ny=cury+rg[i];
                int nx=curx+cg[i];
                int nextDist=cost+(prevDir%2 == i%2 ? 100 : 600);
                if(valid(ny, nx) && dist[cell(ny, nx)][prevDir]>=nextDist) {
                    dist[cell(ny, nx)][prevDir]=nextDist;
                    q.add(new int[] {ny, nx, i, nextDist});
                }
            }
        }
        
        int max=(int)1e9;
        for(int i=0; i<4; i++) max=Math.min(max, dist[cell(n-1, n-1)][i]);
        return max;
    }
    boolean valid(int y, int x) {
        return y>=0 && x>=0 && y<n && x<n && map[y][x]!=1;
    }
    
    int cell(int y, int x) {
        return y*n+x;
    }
}