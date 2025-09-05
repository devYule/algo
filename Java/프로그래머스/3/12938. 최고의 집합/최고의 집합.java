class Solution {
    public int[] solution(int n, int s) {
        int base=s/n;
        if(base==0) return new int[] {-1};
        int add=s%n;
        int[] ret=new int[n];
        for(int i=n-1; i>=0; i--) { ret[i]=base+(add-->0 ? 1 : 0); }
        return ret;
    }
}