import java.util.*;
class Solution {
    int k, n;
    int[] E;
    public int solution(int n, int k, int[] enemy) {
        this.n=n;
        this.k=k;
        this.E=enemy;
        // decide
        return optimize(0, E.length-1)+1;
    }
    
    int optimize(int lo, int hi) {
        while(lo<=hi) {
            int mid=(lo+hi)>>>1;
            if(decide(mid)) {
                lo=mid+1;
            } else {
                hi=mid-1;
            }
        }
        return hi;
    }
    
    // 0~hi 구간을 n, k 로 깰 수 있는가
    // 이렇게 하면 0~hi구간은 정렬이 가능함.
    boolean decide(int hi) {
        int[] copied=Arrays.copyOfRange(E, 0, hi+1);
        Arrays.sort(copied);
        long sum=0;
        for(int i=copied.length-1-k; i>=0; i--) {
            sum+=copied[i];
        }
        return n>=sum;
    }
}