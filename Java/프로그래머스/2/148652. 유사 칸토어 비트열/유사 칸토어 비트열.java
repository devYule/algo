class Solution {
    public int solution(int n, long l, long r) {
        if(n==0) return 1;
        long hi=(long) Math.pow(5, n)-1;
        return find(0, hi, l-1, r-1);
    }
    
    int find(long lo, long hi, long l, long r) {
        if(lo>r || hi<l) return 0;
        long len=hi-lo+1;
        if(len==5) {
            int ret=0;
            for(int i=0; i<5; i++) {
                if(i==2) continue;
                if(lo+i>=l && lo+i<=r) ret++;
            }
            return ret;
        }
        
        long cnt=len/5;
        
        int ret=0;
        for(int i=0; i<5; i++) {
            if(i==2) continue;
            long slo=lo+i*cnt;
            long shi=slo+cnt-1;
            ret+=find(slo, shi, l, r);
        }
        return ret;
    }
}