import java.util.*;
class Solution {
    int M, N;
    public int[] solution(int m, int n, int startX, int startY, int[][] balls) {
        this.M = m;
        this.N = n;
        int[] ret = new int[balls.length];
        Arrays.fill(ret, Integer.MAX_VALUE);
        int ri = 0;
        for(int i=0; i<balls.length; i++) {
            int[] b = balls[i];
            if(startX==b[0] || startY==b[1]) {
                if(startX < b[0]) ret[i] = startX+b[0];
                else if(startX > b[0]) ret[i] = (M-startX)+(M-b[0]);
                else if(startY < b[1]) ret[i] = startY+b[1];
                else ret[i] = (N-startY)+(N-b[1]);
                ret[i] *= ret[i];
            }
            for(int j=0; j<4; j++) {
                
                int[] mb = getMirror(j, b);
                if(startX==mb[0] || startY==mb[1]) continue;
                
                int bx = mb[0];
                int by = mb[1];
                int xlen = Math.max(startX, bx) - Math.min(startX, bx);
                int ylen = Math.max(startY, by) - Math.min(startY, by);
                ret[i] = Math.min(ret[i], xlen*xlen+ylen*ylen);
            }
        }
        
        return ret;
    }
    
    int[] getMirror(int type, int[] o) {
        if(type == 0) return new int[] {o[0], N+(N-o[1])};
        if(type == 1) return new int[] {M+(M-o[0]), o[1]};
        if(type == 2) return new int[] {o[0], -o[1]};
        return new int[] {-o[0], o[1]};
    }
}