import java.util.*;
class Solution {
    public double[] solution(int k, int[][] ranges) {
        List<Integer> ys=collatz(k);
        int xmax=ys.size()-1;
        double[] ret=new double[ranges.length];
        for(int i=0; i<ranges.length; i++) {
            int qa=ranges[i][0];
            int qb=xmax+ranges[i][1];
            if(qa==qb) ret[i]=0.0;
            else if(qa>qb) ret[i]=-1.0;
            else {
                double r=0.0;
                for(int j=qa+1; j<=qb; j++) {
                    r+=area(ys.get(j-1), ys.get(j));
                }
                ret[i]=r;
            }
        }
        return ret;
    }
    
    List<Integer> collatz(int start) {
        List<Integer> ret=new ArrayList<>();
        while(true) {
            ret.add(start);
            if(start==1) break;
            if(start%2==0) start/=2;
            else start=start*3+1;
        }
        return ret;
    }
    
    double area(int a, int b) {
        int trih=(int) Math.abs(a-b);
        int w=1;
        int sqrh=(int) Math.min(a, b);
        
        return trih/2d+sqrh;
    }
}