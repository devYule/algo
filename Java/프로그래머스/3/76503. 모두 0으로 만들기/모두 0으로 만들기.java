import java.util.*;
class Solution {
    /*
        진입1, 진출1 인 정점을 찾음.
        해당 정점들은 무조건 자신을 0으로 만들도록 수행해야 함.
        
        bfs로 가까운 정점으로 이동시키며 기록.
    */
    List<Integer>[] adj;
    int V;
    long[] a;
    boolean[] visited;
    long ret;
    public long solution(int[] a, int[][] edges) {
        this.a=new long[a.length];
        for(int i=0; i<a.length; i++) {
            this.a[i]=a[i];
        }
        init(a.length, edges);
        this.visited=new boolean[V];
        visited[0]=true;
        dfs(0);
        return this.a[0]!=0 ? -1 : ret;
    }
    
    long dfs(int cur) {
        long trans=0;
        // for(int next: adj[cur]) {
        for(int i=0; i<adj[cur].size(); i++) {
            int next=adj[cur].get(i);
            if(visited[next]) continue;
            visited[next]=true;
            long subret=dfs(next);
            this.ret+=Math.abs(subret);
            trans+=subret;
        }
        this.a[cur]=this.a[cur]+trans;
        return this.a[cur];
    }
    
    void init(int V, int[][] edge) {
        this.V=V;
        this.adj=new ArrayList[V];
        for(int i=0; i<V; i++) adj[i]=new ArrayList<>();
        for(int[] e: edge) {
            adj[e[0]].add(e[1]);
            adj[e[1]].add(e[0]);
        }
        
    }
}