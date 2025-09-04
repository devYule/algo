class Solution {
    /*
        자물쇠: n*n
        열쇠: m*m
        3<=n<=m<=20 (정수)
        0: 홈, 1: 돌기
        
        lock의 모든 홀은 매칭이 되어야 함.
    */
    
    // 완전 매칭 여부 반환.
    
    int f=0;
    int n, m;
    int[][] key, lock;
    public boolean solution(int[][] key, int[][] lock) {
        this.key=key;
        this.lock=lock;
        this.n=lock.length;
        this.m=key.length;
        
        for(int i=0; i<4; i++) {
            for(int j=0; j<n*n; j++) for(int k=0; k<m*m; k++) {
                if(valid(j, k)) return true;
            }
            rotate();
        }
        
        return false;
    }
    
    boolean valid(int lockcell, int keycell) {
        int[] la=axis(lockcell, n);
        int[] ka=axis(keycell, m);
        int ky=la[0]-ka[0];
        int kx=la[1]-ka[1];
        
        for(int i=0; i<n; i++) for(int j=0; j<n; j++) {
            int yk=i-ky;
            int xk=j-kx;
            if(yk<0 || xk<0 || yk>=m || xk>=m) {
                if(lock[i][j]==0) return false;
                continue;
            }
            if(lock[i][j]==key[yk][xk]) return false;
        }
        
        return true;
    }
    
    int[] axis(int cell, int nm) { return new int[] { cell/nm, cell%nm }; }
        
    // key 돌리기
    void rotate() {
        int[][] newKey=new int[m][m];
        for(int i=0; i<m; i++) for(int j=0; j<m; j++) {
            newKey[j][m-i-1]=key[i][j];
        }
        this.key=newKey;
    }
}