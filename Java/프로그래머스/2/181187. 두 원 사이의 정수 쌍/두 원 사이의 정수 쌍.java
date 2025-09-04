class Solution {
    public long solution(int r1, int r2) {
        long ret = 0;
        long R1 = r1;
        long R2 = r2;
        for(long i=1; i<=r2; i++) {
            long outy = (long) Math.floor(Math.sqrt(R2*R2 - i*i));
            double in = Math.sqrt(R1*R1 - i*i);
            long iny = in < 0 ? 0 : (long) Math.ceil(in);
            
            ret += outy-iny+1;
        }
        return ret*4;
    }
}