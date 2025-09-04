class Solution {
    int n, m;
    public int solution(int[][] board, int[][] skill) {
        n=board.length;
        m=board[0].length;
        long[][] sumArr=new long[n][m];
        
        for(int[] s: skill) {
            int type=s[0];
            int yf=s[1];
            int xf=s[2];
            int yt=s[3];
            int xt=s[4];
            int d=s[5];
            if(type==1) d=-d;
            
            sumArr[yf][xf]+=d;
            int leftEnd=xt+1;
            int bottomEnd=yt+1;
            if(bottomEnd<n&&leftEnd<m) {
                sumArr[bottomEnd][leftEnd]+=d;
            } 
            if(leftEnd<m) {
                sumArr[yf][leftEnd]+=-d;
            }
            if(bottomEnd<n) {
                sumArr[bottomEnd][xf]+=-d;
            }
        }
        fire(sumArr);
        int ret=0;
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                if(board[i][j]+sumArr[i][j]>0) ret++;
            }
        }
        
        return ret;
    }
    
    void fire(long[][] sumArr) {
        for(int i=0; i<n; i++) {
            for(int j=1; j<m; j++) {
                sumArr[i][j]+=sumArr[i][j-1];
            }
        }
        for(int j=0; j<m; j++) {
            for(int i=1; i<n; i++) {
                sumArr[i][j]+=sumArr[i-1][j];
            }
        }
    }
}