import java.util.*;
class Solution {
    public String[] solution(String[][] plans) {
        int N = plans.length;
        
        String[] ret = new String[N];
        int ri = 0;
        PriorityQueue<Obj> q = new PriorityQueue<>((a, b) -> a.start - b.start);
        
        for(int i = 0; i < N; i++) {
            String[] p = plans[i];
            Obj o = new Obj();
            o.name=p[0];
            int[] times = getTime(p[1], p[2]);
            o.start=times[0];
            o.rest=times[1];
            q.add(o);
        }
        Stack<Obj> st = new Stack<>();
        
        Obj cur = q.poll();
        int time = cur.start;
        while(!q.isEmpty()) {
            Obj next = q.poll();
            System.out.println(cur.name);
            if(time+cur.rest <= next.start) {
                ret[ri++] = cur.name;
                time+=cur.rest;
                while(time < next.start && !st.isEmpty()) {
                    Obj prev = st.pop();
                    if(time + prev.rest <= next.start) {
                        ret[ri++] = prev.name;
                        time+=prev.rest;
                    } else {
                        prev.rest -= next.start-time;
                        time = next.start;
                        st.add(prev);
                    }
                }
            } else {
                cur.rest -= next.start-cur.start;
                st.add(cur);
            }
            time = next.start;
            cur = next;
        }
        
        ret[ri++] = cur.name;
        while(!st.isEmpty()) {
            ret[ri++] = st.pop().name;
        }
        
        return ret;
    }
    
    int getTime(String start) {
        String[] ss = start.split(":");
        return Integer.parseInt(ss[0])*60 + Integer.parseInt(ss[1]);
    }
    int[] getTime(String start, String dur) {
        int duration = Integer.parseInt(dur);
        return new int[] {getTime(start), duration};
    }
    static class Obj {
        String name;
        int rest;
        int start;
    }
    
}