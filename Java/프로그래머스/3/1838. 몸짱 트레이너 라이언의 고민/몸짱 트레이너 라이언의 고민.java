import java.util.*;
class Solution {
    /*
        n*n, <=10
        10:00~22:00
        m명 m<1_000
        인접:1, 대각:2
        n*n 보다 많은 사람이 동시에 락커를 사용하는 경우 X
    */
    // 사용자간 거리를 최대한 멀게 했을 때, 동시에 사용했던 사용자의 거리의 최소값
    public int solution(int n, int m, int[][] timetable) {
        if(m==0) return m;
        // 겹치는 최대 인원 구하기
        Arrays.sort(timetable, (a, b)->a[0]==b[0] ? a[1]-b[1]: a[0]-b[0]);
        // 차분배열
        int[] diff=new int[23*60];
        for(int[] tt: timetable) {
            diff[tt[0]]++;
            diff[tt[1]+1]--;
        }
        int ppl=0;
        for(int i=600; i<diff.length; i++) {
            diff[i]+=diff[i-1];
            ppl=Math.max(ppl, diff[i]);
        }

        if(ppl==1) return 0;
        
        // 해당 인원을 n*n 에 최대한 먼 거리로 배치했을 때 사용자간의 거리 최소값 구하기.
        /*
            1. 대각선은 생각할 필요 없다. "행의 차+열의 차" 만 생각하면 된다.
            2. n*n 에서 1:1간의 최대 거리는 양 대각 끝으로, (n-1)+(n-1) 이다. (== (n-1)*2)
        */
        
        int lo=1;
        int hi=(n-1)*2;
        while(lo<=hi) {
            int mid=(lo+hi)>>>1;
            int[] vis=new int[n];
            if(can(0, mid, ppl, vis)) lo=mid+1;
            else hi=mid-1;
        }
        return hi;
    }
    
    // row: 이번에 검증할 행.
    // range: 자신이 차지할 수 있는 칸수.
    // rest: 배치해야하는 사람 수.
    // vis: 행단위 이미 차지된 비트마스크 상태표현.
    boolean can(int row, int range, int rest, int[] vis) {
        if(rest==0) return true;
        if(row>=vis.length) return false;
        
        int n=vis.length;
        
        int canEachRow=n/range+(n%range==0 ? 0 : 1);
        int restRow=n-row;
        if(canEachRow*restRow<rest) return false;

        int cannot=0;
        // 차지공간.
        int area=range-1;
        for(int i=1; i<=area; i++) {
            if(row-i<0) break;
            int prevmask=vis[row-i];
            int rowmv=area-i;

            for(int j=0; j<=rowmv; j++) {
                cannot|=(prevmask<<j);
                cannot|=(prevmask>>j);
            }
        }
        
        int can=(~cannot)&(1<<n)-1;
        int mask=can;
        while(true) {
            boolean ok=true;
            int prev=-1;
            for(int i=0; i<n; i++) {
                if(((mask>>i)&1)==1) {
                    if(prev!=-1) { 
                        int gap=i-prev;
                        if(gap<range) {
                            ok=false;
                            break;
                        }
                    }
                    prev=i;
                }
            }
            if(ok) {
                int placed=Integer.bitCount(mask);
                if(placed<=rest) {
                    vis[row]=mask;
                    if(can(row+1, range, rest-placed, vis)) return true;
                }
            }
            if(mask==0) break;
            mask=(mask-1)&can;
        }
        
        return false;
        
    }
    
    boolean valid(int y, int x, int n) {
        return y>=0 && x>=0 && y<n && x<n;
    }
}