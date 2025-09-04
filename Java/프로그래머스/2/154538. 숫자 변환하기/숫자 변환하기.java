import java.util.*;
class Solution {
    int y, n;
    final int MAX=(int)1e9;
    
    int[] memo;
    
    public int solution(int x, int y, int n) {
        this.y=y;
        this.n=n;
        this.memo=new int[y+1];
        Arrays.fill(memo, (int)MAX);
        int[] cases={x+n, x*2, x*3};
        
        memo[x]=0;
        
        for(int i=Math.min(cases[0], cases[1]); i<=y; i++) {
            if(i-n>0 && memo[i-n] != MAX) {
                memo[i]=memo[i-n]+1;
            }
            if(i/2>0 && i%2==0 && memo[i/2] != MAX) {
                memo[i]=Math.min(memo[i], memo[i/2]+1);
            }
            if(i/3>0 && i%3==0 && memo[i/3] != MAX) {
                memo[i]=Math.min(memo[i], memo[i/3]+1);
            }
        }
        return memo[y]>=MAX ? -1 : memo[y];
    }
}