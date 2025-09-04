import java.util.*;
class Solution {
    /*
        0: [0][-1];
        1: [0][1];
        2: [-1][0];
        3: [1][0]
    */
    
    int n,m;
    int x,y;
    int[][] Q;
    public long solution(int n, int m, int x, int y, int[][] queries) {
        this.n=n; this.m=m; this.Q=queries; this.x=x; this.y=y;
        return simul();
    }
    
    long simul() {
        long ymin=x;
        long ymax=x;
        long xmin=y;
        long xmax=y;
        for(int i=Q.length-1; i>=0; i--) {
            int flag=Q[i][0];
            long dist=Q[i][1];
            
            if(flag==0 || flag==1) {
                if(flag==0) {
                    xmin=xmin==0 ? 0 : xmin+dist;
                    xmax=Math.min(m-1, xmax+dist);
                } else {
                    xmax=xmax==m-1 ? m-1 : xmax-dist;
                    xmin=Math.max(0, xmin-dist);
                }
            }
            
            if(flag==2 || flag==3) {
                if(flag==2) {
                    ymin=ymin==0 ? 0 : ymin+dist;
                    ymax=Math.min(n-1, ymax+dist);
                } else {
                    ymax=ymax==n-1 ? n-1 : ymax-dist;
                    ymin=Math.max(0, ymin-dist);
                }
            }
            if(xmin>xmax || ymin>ymax) return 0;
        }
        return (ymax-ymin+1)*(xmax-xmin+1);
    }
}