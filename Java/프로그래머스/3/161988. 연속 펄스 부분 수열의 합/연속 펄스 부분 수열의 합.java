import java.util.*;
class Solution {
    
    public long solution(int[] sequence) {
        int N = sequence.length;
        long[][] memo = new long[N][2];
        
        // 기저조건: idx 가 N 일 때.
        memo[0][0] = -sequence[0];
        memo[0][1] = sequence[0];
        
        // [0] 인덱스도 결과 후보에 포함하기.
        long ret = Math.max(memo[0][0], memo[0][1]);
        
        // 각 숫자에서 시작하는 모든 부분수열에대한 최대값 구하기.
        for(int i=1; i<N; i++) {
            // flag: 0 = sequence[idx] * -1
            //       1 = sequence[idx] *  1
            for(int j=0; j<2; j++) {
                memo[i][j] = getNumb(sequence[i], j);
                long prevMax = memo[i-1][toggle(j)];
                // 다음 숫자의 최대 연속 부분수열의 합과 현재 숫자를 더한 경우가
                // 지금 내 숫자로 다시 수열을 만드는것보다 유리할 경우.
                if(memo[i][j]+prevMax > memo[i][j]) memo[i][j]+=prevMax;
                ret = Math.max(ret, memo[i][j]);
            }
        }
        
        return ret;
    }
    
    int getNumb(int a, int flag) {
        return flag == 1 ? a : -a;
    }
    int toggle(int n) {
        return (n+1)%2;
    }
}