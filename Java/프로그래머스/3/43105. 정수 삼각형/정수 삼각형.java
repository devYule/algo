import java.util.*;
class Solution {
    int[] rg={1, 1};
    int[] cg={0, 1};
    
    int Y;
    int[][] triangle;
    
    int[][] memo;
    public int solution(int[][] triangle) {
        this.Y=triangle.length;
        this.memo=new int[Y][triangle[Y-1].length];
        for(int i=0; i<Y; i++) Arrays.fill(memo[i], -1);
        this.triangle=triangle;
        
        return getMax(0, 0);
        
    }
    
    int getMax(int y, int x) {
        if(y==Y) return 0;
        if(memo[y][x]!=-1) return memo[y][x];
        
        int ret=0;
        for(int i=0; i<2; i++) {
            ret=Math.max(ret, getMax(y+rg[i], x+cg[i]));
        }
        return memo[y][x]=ret+triangle[y][x];
    }
}