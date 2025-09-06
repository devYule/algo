import java.util.*;
class Solution {
    /*
        cores[]: 코어별 작업 처리 시간.
        n: <= 50,000
        max(cores[i]) <= 10,000
    */
    // 마지막 작업을 처리한 코어의 번호.
    public int solution(int n, int[] cores) {
        int lo=0;
        int hi=Arrays.stream(cores).max().orElse(0)*(n-cores.length);
        // 모든일을 처리할 수 있는 최소시간.
        while(lo<=hi) {
            int mid=(lo+hi)>>>1;
            long count=cores.length;
            for(int i=0; i<cores.length; i++) count+=mid/(long)cores[i];
            if(count>=n) hi=mid-1;
            else lo=mid+1;
        }

        long task=cores.length;
        for(int c: cores) task+=(lo-1)/(long)c;
        // lo시간에 처리해야할 남은 작업량
        long rest=n-task;
        for(int i=0; i<cores.length; i++) {
            // lo 시간에 유휴코어면,
            if(lo%cores[i]==0) {
                rest--;
                if(rest==0) return i+1;
            }
        }
        
        return -1;
        
    }
}