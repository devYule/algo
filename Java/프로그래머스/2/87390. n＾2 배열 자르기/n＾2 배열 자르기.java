class Solution {
    /*
        i==j ? i+1;
        i>j  ? i+1;
        i<j  ? i+1+(j-i)
    */
    public int[] solution(int n, long left, long right) {
        int[] ret=new int[(int)(right-left+1)];
        int ri=0;
        for(long k=left; k<=right; k++) {
            int i=(int)(k/n);
            int j=(int)(k%n);
            ret[ri++]=i<j ? 1+i+(j-i) : i+1;
        }
        return ret;
    }
}