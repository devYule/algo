import java.util.*;
class Solution {
    List<Integer>[] adj;
    int V;
    public int solution(int n, int[][] edge) {
        init(n+1, edge);
        
        return bfs(1);
    }
    
    int bfs(int start) {
        int[] dist=new int[V];
        Queue<Integer> q=new LinkedList<>();
        Arrays.fill(dist, -1);
        dist[start]=0;
        q.add(start);
        
        while(!q.isEmpty()) {
            int cur=q.poll();
            
            for(int next: adj[cur]) {
                if(dist[next]!=-1) continue;
                dist[next]=dist[cur]+1;
                q.add(next);
            }
        }
        
        Arrays.sort(dist);
        int max=dist[dist.length-1];
        int ret=0;
        for(int i=V-1; i>=0; i--) {
            if(dist[i]!=max) break;
            ret++;
        }
        return ret;
    }
    
    @SuppressWarnings("unchecked")
    void init(int V, int[][] edge) {
        this.V=V;
        this.adj=new ArrayList[V];
        for(int i=1; i<V; i++) adj[i]=new ArrayList<>();
        for(int[] e: edge) {
            adj[e[0]].add(e[1]);
            adj[e[1]].add(e[0]);
        }
    }
}