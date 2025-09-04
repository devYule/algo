import java.util.*;
class Solution {
    public int[] solution(int[] prices) {
        int[] ret=new int[prices.length];
        Stack<Integer> st=new Stack<>();
        for(int i=0; i<prices.length; i++) {
            int numb=prices[i];
            while(!st.isEmpty() && prices[st.peek()]>numb) {
                int bigi=st.pop();
                ret[bigi]=i-bigi;
            }
            st.add(i);
        }
        
        int nm1=prices.length-1;
        while(!st.isEmpty()) {
            int resti=st.pop();
            ret[resti]=nm1-resti;
        }
        return ret;
    }
}