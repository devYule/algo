import java.util.*;
class Solution {

    int V;
    List<Integer>[] adj;
    int[] info;
    int ret;
    public int solution(int[] info, int[][] edges) {
        init(info, edges);
        List<Integer> visitable=new ArrayList<>();
        visitable.add(0);
        visit(0, 1, 0, visitable);
        return ret;
    }
    
    void visit(int cur, int sheep, int wolf, List<Integer> visitable) {
        
        if(sheep<=wolf) return;
        this.ret=Math.max(ret, sheep);
        
        visitable.remove(Integer.valueOf(cur));
        
        for(int next: adj[cur]) {
            visitable.add(next);
        }
        
        for(int i=0; i<visitable.size(); i++) {
            int next=visitable.get(i);
            int addSheep=info[next]==0?1:0;
            int addWolf=info[next]==0?0:1;
            visit(next, sheep+addSheep, wolf+addWolf, new ArrayList<>(visitable));
        }
    }
    
    @SuppressWarnings("unchecked")
    void init(int[] info, int[][] edge) {
        this.info=info;
        this.V=info.length;
        this.adj=new ArrayList[V];
        for(int i=0; i<V; i++) adj[i]=new ArrayList<>();
        for(int[] e: edge) {
            adj[e[0]].add(e[1]);
        }
    }
    
}