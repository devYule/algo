class Solution {
    public int[] solution(int n, int s) {
        int base=s/n;
        if(base==0) return new int[] {-1};
        int add=s%n;
        int[] ret=new int[n];
        java.util.Arrays.fill(ret, base);
        int ri=n-1;
        while(add>0) { ret[ri--]++; add--; }
        return ret;
    }
}