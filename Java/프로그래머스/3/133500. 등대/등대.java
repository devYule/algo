import java.util.*;
class Solution {
    /*
        자식은 2개이상일 수 있지만, 부모는 2개이상일 수 없음.
        따라서 부모가 켜지는게 가장 이상적.
    */
    
    int V;
    List<Integer>[] adj;
    int ret;
    public int solution(int n, int[][] lighthouse) {
        
        init(n+1, lighthouse);
        
        boolean[] visited=new boolean[V];
        for(int i=0; i<V; i++) {
            if(visited[i]) continue;
            count(i, visited);    
        }
        
        return ret;
    }
    
    boolean count(int cur, boolean[] visited) {
        visited[cur]=true;
        boolean needOn=false;
        for(int next: adj[cur]) {
            if(visited[next]) continue;
            boolean child=count(next, visited);
            if(!needOn && !child) needOn=true;
        }
        
        if(needOn) {
            ret++;
            return true;
        }
        return false;
    }
    
    @SuppressWarnings("unchecked")
    void init(int V, int[][] edge) {
        this.ret=0;
        this.V=V;
        this.adj=new ArrayList[V];
        for(int i=0; i<V; i++) adj[i]=new ArrayList<>();
        for(int[] e: edge) {
            int a=e[0];
            int b=e[1];
            adj[a].add(b);
            adj[b].add(a);
        }
    }
    
    void asrt(boolean f) {
        if(!f) throw new RuntimeException("assert");
    }
}