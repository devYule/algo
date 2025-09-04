import java.util.*;
class Solution {
    int[][] adj;
    int V;
    public int solution(int n, int[][] wires) {
        init(n+1, wires);
        int ret=(int)1e9;
        for(int i=0; i<V; i++) {
            for(int j=0; j<V; j++) {
                if(adj[i][j]==0) continue;
                adj[i][j]=0;
                boolean[] visited=new boolean[V];
                int a=count(i, visited);
                int b=count(j, visited);
                ret=Math.min(ret, Math.abs(a-b));
                adj[i][j]=1;
            }
        }
        return ret;
    }
    
    int count(int cur, boolean[] visited) {
        visited[cur]=true;
        int ret=1;
        for(int i=0; i<V; i++) {
            if(adj[cur][i]==0 || visited[i]) continue;
            ret+=count(i, visited);
        }
        return ret;
    }
    
    void init(int V, int[][] edge) {
        this.adj=new int[V][V];
        this.V=V;
        for(int[] e: edge) {
            adj[e[0]][e[1]]=1;
            adj[e[1]][e[0]]=1;
        }
    }
}