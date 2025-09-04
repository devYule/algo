import java.util.*;
class Solution {
    public int solution(int[][] data, int col, int row_begin, int row_end) {
        int coli=col-1;
        Arrays.sort(data, (a, b) -> a[coli]==b[coli] ? b[0]-a[0] : a[coli]-b[coli]);
        
        int ret=-1;
        
        for(int i=row_begin-1; i<row_end; i++) {
            int row=0;
            for(int j=0; j<data[i].length; j++) {
                row+=data[i][j]%(i+1);
            }
            if(ret==-1) ret=row;
            else ret^=row;
        }
        
        return ret;
    }
}