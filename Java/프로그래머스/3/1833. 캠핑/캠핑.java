import java.util.*;
class Solution {
    
    /*
        1 0 1
        0 1 0
        1 0 0
    
        1 0 0 1 0
        0 0 0 0 0
        0 0 0 1 0
        0 1 0 0 1
        
        
        1 0 0 0 0 0
        0 0 0 0 1 0
        0 0 0 0 0 0
        0 1 0 0 0 1
        0 0 0 0 0 0
    */
    public int solution(int n, int[][] data) {

        Arrays.sort(data, (a, b) -> a[0]==b[0] ? a[1]-b[1] : a[0]-b[0]);
        final int MAX=(int)1e9;
        int ret=0;
        for(int i=0; i<data.length; i++) {
            int[] a=data[i];
            TreeSet<Integer> set=new TreeSet<>();
            
            int k=i+1;
            for(int j=i+1; j<data.length; j++) {
                int[] b=data[j];

                while(k<j && data[k][0]<b[0]) {
                    if(data[k][0]>a[0]) set.add(data[k][1]);
                    k++;
                }
                if(a[0]==b[0] || a[1]==b[1]) continue;
                
                int lo=Math.min(a[1], b[1]);
                int hi=Math.max(a[1], b[1]);
                Integer contain=set.higher(lo);
                if(contain==null || contain>=hi) ret++;
            }
        }
        return ret;
    }
}