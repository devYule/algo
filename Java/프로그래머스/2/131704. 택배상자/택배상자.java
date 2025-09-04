import java.util.*;
class Solution {
    public int solution(int[] order) {
        int n=order.length;
        int ret=0;
        Stack<Integer> st=new Stack<>();
        int bi=1;
        for(int i=0; i<n; i++) {
            int boxId=order[i];
            if(!st.isEmpty() && st.peek()==boxId) {
                st.pop();
                ret++;
                continue;
            }
            while(bi<=n) {
                if(bi==boxId) break;
                st.add(bi);
                bi++;
            }
            if(bi>n) break;
            ret++;
            bi++;
        }
        
        return ret;
    }
}