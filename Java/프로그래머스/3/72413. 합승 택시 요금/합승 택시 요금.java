import java.util.*;
class Solution {
    int[][] adj;
    int V;
    final int MAX=(int)1e9;
    public int solution(int n, int s, int a, int b, int[][] fares) {
        init(n+1, fares);
        
        fire();
        
        for(int i=1; i<V; i++) {
            adj[i][i]=0;
        }
        
        int ret=adj[s][a]+adj[s][b];
        for(int i=1; i<V; i++) {
            if(i==s) continue;
            if(adj[s][i]>=MAX || adj[i][a]>=MAX || adj[i][b]>=MAX) continue;
            ret=Math.min(ret, adj[s][i]+adj[i][a]+adj[i][b]);
        }
        return ret;
    }
    
    void fire() {
        for(int k=1; k<V; k++) {
            for(int i=1; i<V; i++) {
                for(int j=1; j<V; j++) {
                    adj[i][j]=Math.min(adj[i][j], adj[i][k]+adj[k][j]);
                }
            }
        }
    }
        
    void init(int V, int[][] edge) {
        this.V=V;
        this.adj=new int[V][V];
        for(int i=0; i<V; i++) Arrays.fill(adj[i], MAX);
        for(int[] e: edge) {
            int a=e[0];
            int b=e[1];
            int d=e[2];
            adj[a][b]=d;
            adj[b][a]=d;
        }
    }
}