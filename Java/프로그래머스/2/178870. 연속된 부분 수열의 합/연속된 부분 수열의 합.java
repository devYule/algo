class Solution {
    public int[] solution(int[] sequence, int k) {
        int N = sequence.length;
        int left = 0;
        int sum = 0;
        int lc = 0;
        int rc = N;
        for(int i = 0; i < N; i++) {
            int right = i;
            sum+=sequence[right];
            
            while(left <= right && sum > k) sum-=sequence[left++];
            if(sum==k) {
                if(rc-lc > right-left) {
                    lc=left;
                    rc=right;
                }
            }
            if(left > right) left = right;
        }
        return new int[] {lc, rc};
    }
}