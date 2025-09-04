import java.util.*;
class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        int n=progresses.length;
        int[] days=new int[n];
        for(int i=0; i<n; i++) {
            days[i]=100-progresses[i];
            int add=days[i]%speeds[i] == 0 ? 0 : 1;
            days[i]/=speeds[i];
            days[i]+=add;
        }
        
        List<Integer> ret=new ArrayList<>();
        Stack<Integer> st=new Stack<>();
        int max=0;
        for(int i=0; i<n; i++) {
            if(!st.isEmpty() && days[i]>max) {
                ret.add(st.size());
                st.clear();
                max=0;
            }
            max=Math.max(max, days[i]);
            st.add(i);
        }
        
        if(!st.isEmpty()) ret.add(st.size());
        
        return ret.stream().mapToInt(a->a).toArray();
    }
}