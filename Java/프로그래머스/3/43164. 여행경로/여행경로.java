import java.util.*;
class Solution {
    Map<String, List<String>> adj;
    List<String> ret;
    
    public String[] solution(String[][] tickets) {
        init(tickets);
        
        adj.keySet().forEach(k -> adj.get(k).sort(Comparator.reverseOrder()));
        
        this.ret=new ArrayList<>();
        dfs("ICN");
        Collections.reverse(ret);
        return ret.stream().toArray(String[]::new);
    }
    
    void dfs(String cur) {
        while(adj.get(cur)!=null && !adj.get(cur).isEmpty()) {
            int idx=adj.get(cur).size()-1;
            String next=adj.get(cur).get(idx);
            adj.get(cur).remove(idx);
            dfs(next);
        }
        ret.add(cur);
    }
    
    void init(String[][] tickets) {
        this.adj=new HashMap<>();
        for(String[] tk: tickets) {
            String f=tk[0];
            String t=tk[1];
            adj.computeIfAbsent(f, _$ -> new ArrayList<>());
            adj.get(f).add(t);
        }
    }
}