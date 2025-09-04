import java.util.*;
class Solution {
    
    // 온도 변경: 에어컨이 켜져있을 때.
    // 실내 온도와 희망 온도가 다르면 1분뒤 온도가 변경 (희망온도쪽으로)
    // 실내온도==희망온도 ? 실내온도 변하지 않음.
    // 에어컨 off: 실외온도쪽으로 매분 온도 변화.
    // 실내온도==실외온도 ? 실내온도 변화 X
    
    // 희망온도!=실내온도 ? 전력 a소비
    // 희망온도==실내온도 ? 전력 b소비
    // 에어컨 off: 전력 0
    
    final int MAX=(int)1e9;
    int[][] memo;
    int n;
    int[] B;
    int t1, t2;
    int T;
    int a, b;
    public int solution(int temperature, int t1, int t2, int a, int b, int[] onboard) {
        int minimum=Math.min(temperature, Math.min(t1, t2));
        if(minimum<0) {
            int revision=Math.abs(minimum);
            temperature+=revision;
            t1+=revision;
            t2+=revision;
        }
        
        this.a=a;
        this.b=b;
        this.T=temperature;
        this.t1=t1;
        this.t2=t2;
        this.B=onboard;
        this.n=onboard.length;
        this.memo=new int[n][Math.max(temperature, t2)+1];
        for(int i=0; i<n; i++) {
            Arrays.fill(memo[i], -1);
        }
        return dp(0, temperature);
    }
    
    // idx까지 현재 온도가 t일 때, 누적 가중치 최소값
    // on: 에어컨이 켜져있는지 꺼져있는지 상태. (1: on, 0: off)
    int dp(int idx, int t) {
        if(idx==n) return 0;
        if(t<0 || t>=memo[idx].length) return MAX;
        if(B[idx]==1 && (t<t1||t>t2)) return MAX;
        if(memo[idx][t] != -1) return memo[idx][t];

        int ifOff=t<T ? t+1 : t>T ? t-1 : t;
        
        int ret=MAX;
        // 지금 에어컨을 켜져있는 상태에서 끄는 경우.
        ret=Math.min(ret, dp(idx+1, ifOff));
        // 지금 에어컨을 켜져있는 상태로 유지하는 경우.
        if(t<t1) {
            ret=Math.min(ret, dp(idx+1, t+1)+a);
        } else if(t>t2) {
            ret=Math.min(ret, dp(idx+1, t-1)+a);
        } else {
            ret=Math.min(ret, dp(idx+1, t-1)+a);
            ret=Math.min(ret, dp(idx+1, t)+b);
            ret=Math.min(ret, dp(idx+1, t+1)+a);
        }
        
        return memo[idx][t]=ret;
    }
}