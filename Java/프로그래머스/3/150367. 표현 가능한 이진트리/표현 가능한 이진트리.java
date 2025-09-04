class Solution {
    // 7: 111
    // 5: 101
    public int[] solution(long[] numbers) {
        int n=numbers.length;
        int[] ret=new int[n];
        for(int i=0; i<n; i++) {
            long mask=numbers[i];

            int root=0;
            int pow=0;
            int nextPow=(int) Math.pow(2, pow);
            while(nextPow <= 32 && mask > 1L<<(nextPow-1)) {
                root=nextPow-1;
                nextPow=(int) Math.pow(2, ++pow);
            }

            if(canMake(mask, 0, root*2)) ret[i]=1;
        }
        return ret;
    }
    
    // lo: 비트의 좌측부분의 오른쪽 기준 시작점. (좌측 서브트리)
    // hi: 비트의 우측 부분의 오른쪽 기준 시작점. (우측 서브트리)
    // 각 마스크의 절반기준 좌, 우를 나눠서 각 서브트리로 전달.
    // 자신이 0인데 좌우중 하나라도 1이면 false
    boolean canMake(long mask, int lo, int hi) {

        if(lo>=hi) return true;
        int mid=(lo+hi)>>>1;
        long root=mask & 1L<<mid;

        long m=mask & ~(1L<<mid);
        m=m>>>lo & ((1L<<(hi-lo+1))-1L);
        if(root==0 && (m!=0)) return false;
        
        boolean left=canMake(mask, lo, mid-1);
        boolean right=canMake(mask, mid+1, hi);
        
        return left&&right;
    }
}