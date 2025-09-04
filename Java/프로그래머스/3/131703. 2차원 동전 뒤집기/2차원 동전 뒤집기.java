import java.util.*;
class Solution {
    
    int[] start;
    int[] goal;
    int N, M;
    
    Map<String, Integer> min;
    
    int ret;
    
    public int solution(int[][] beginning, int[][] target) {
        init(beginning, target);
        ret=(int)1e9;
        sim(start, 0, 0, 0);
        
        return ret==(int)1e9 ? -1 : ret;
    }
    
    void sim(int[] state, int r, int c, int cnt) {
        if(cnt>=ret) return;
        String key=genKey(state, r, c);
        
        if(min.get(key)==null) {
            min.put(key, cnt);
        } else {
            if(min.get(key)<=cnt) return;
            min.put(key, cnt);
        }
        
        if(r==N && c==M) {
            boolean flag=true;
            for(int i=0; i<N; i++) {
                if(state[i]!=goal[i]) {
                    flag=false;
                    break;
                }
            }
            if(flag) ret=Math.min(ret, cnt);
            return;
        }
        
        
        // 아무것도 뒤집지지 않는 경우 1: 행만 증가
        if(r<N) sim(state, r+1, c, cnt);
        // 아무것도 뒤집지지 않는 경우 2: 열만 증가
        if(c<M) sim(state, r, c+1, cnt);
        
        if(r<N) {
            // 행만 뒤집는 경우
            int ord=state[r];
            state[r]=flipRow(state[r]);
            sim(state, r+1, c, cnt+1);
            state[r]=ord;
        }
        
        if(c<M) {
            // 열만 뒤집는 경우
            int[] copied=flipCol(state, c);
            sim(copied, r, c+1, cnt+1);
        }
        
        if(r<N && c<M) {
            // 행렬 다뒤집는 경우
            int ord=state[r];
            state[r]=flipRow(state[r]);
            int[] copied=flipCol(state, c);
            state[r]=ord;
            sim(copied, r+1, c+1, cnt+2);
        }
    }
    
    void init(int[][] bg, int[][] tg) {
        this.N=bg.length;
        this.M=bg[0].length;
        this.start=new int[N];
        this.goal=new int[N];
        this.min=new HashMap<>();
        
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(bg[i][j]!=0) start[i]|=1<<j;
                if(tg[i][j]!=0) goal[i]|=1<<j;
            }
        }
    }
    
    int flipRow(int mask) {
        return (~mask) & (1<<M)-1;
    }
    
    int[] flipCol(int[] orin, int col) {
        int[] copied=Arrays.copyOf(orin, N);
        for(int i=0; i<N; i++) {
            copied[i]^=1<<col;
        }
        return copied;
    }
 
    String genKey(int[] a, int r, int c) {
        return Arrays.toString(a)+":"+r+":"+c;
    }
}