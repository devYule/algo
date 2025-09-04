import java.util.*;
class Solution {
    
    final int MAX=(int)1e9;
    int V;
    List<Integer>[] adj;
    
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        init(n+1, roads);
        int[] dist=getDist(destination);
        int[] ret=new int[sources.length];
        for(int i=0; i<sources.length; i++) {
            int dst=dist[sources[i]];
            ret[i]=dst;
        }
        return ret;
    }
    
    int[] getDist(int start) {
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
        return dist;
    }
    
    @SuppressWarnings("unchecked")
    void init(int V, int[][] edge) {
        this.V=V;
        this.adj=new ArrayList[V];
        for(int i=1; i<V; i++) adj[i]=new ArrayList<>();
        for(int[] e: edge) {
            int a=e[0];
            int b=e[1];
            adj[a].add(b);
            adj[b].add(a);
        }
    }
}