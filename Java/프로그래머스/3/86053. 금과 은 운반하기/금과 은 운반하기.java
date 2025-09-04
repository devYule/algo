class Solution {
    
    int a, b;
    int[] g, s, w, t;
    
    public long solution(int a, int b, int[] g, int[] s, int[] w, int[] t) {
        this.a=a; this.b=b; this.g=g; this.s=s; this.w=w; this.t=t;
        return ps(0, (100000*1000000000L)*4);
    }
    
    long ps(long lo, long hi) {
        while(lo<=hi) {
            long mid=(lo+hi)>>>1L;
            if(can(mid)) {
                hi=mid-1;
            } else lo=mid+1;
        }
        return lo;
    }
    boolean can(long time) {
        long accg=0, accs=0, tot=0;
        for(int i=0; i<g.length; i++) {
            // 시간 내에 옮길 수 있는 횟수.
            long timeCan=time/(t[i]*2L);
            if(time%(t[i]*2L)>=t[i]) timeCan++;
            
            long both=Math.min(g[i]+s[i], timeCan*w[i]);
            tot+=both;
            accg+=Math.min(both, g[i]);
            accs+=Math.min(both, s[i]);
        }
        return tot>=a+b && accg>=a && accs>=b;
    }
}