class Solution {
    
    public long solution(int k, int d) {
        long ret=0;
        for(int i=0; i<=d; i+=k) {
            ret++;
            // 현재 행의 0~d사이에 d를 넘지 않는 거리 이분탐색으로 찾기
            // 찾은 점 / k == 점을 찍을 수 있는 개수.
            long point=optimize(i, 0, d);
            ret+=point/k;
        }
        return ret;        
    }
    long optimize(int row, int collo, int colhi) {
        int d=colhi;
        int lo=collo;
        int hi=colhi;
        while(lo<=hi) {
            int mid=(lo+hi)>>>1;
            if(decide(row, mid, d)) {
                lo=mid+1;
            } else hi=mid-1;
        }
        return hi;
    }
    
    boolean decide(int row, int col, int d) {
        // 대각선 거리가 d를 넘지 않는가 반환.
        return (long)row*row+(long)col*col<=(long)d*d;
    }
}