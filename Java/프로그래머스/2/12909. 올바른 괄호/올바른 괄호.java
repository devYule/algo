import java.util.*;
class Solution {
    boolean solution(String s) {
        Stack<Integer> st=new Stack<>();
        
        for(int i=0; i<s.length(); i++) {
            char c=s.charAt(i);
            if(c=='(') st.add(0);
            else {
                if(st.isEmpty()) return false;
                st.pop();
            }
        }
        return st.isEmpty();
    }
}