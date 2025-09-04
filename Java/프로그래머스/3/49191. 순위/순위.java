import java.util.*;
class Solution {
    /*
            4 - 3
            | /
        1 - 2 - 5
    */
    int[][] adj;
    int V;
    public int solution(int n, int[][] results) {
        init(n+1, results);
        
        for(int k=1; k<V; k++) {
            for(int i=1; i<V; i++) for(int j=1; j<V; j++) {
                adj[i][j]=Math.min(adj[i][j], adj[i][k]+adj[k][j]);
            }
        }
        
        for(int i=0; i<V; i++) adj[i][i]=(int)1e9;
        
        int ret=0;
        for(int i=1; i<V; i++) {
            boolean flag=true;
            for(int j=1; j<V; j++) {
                if(i==j) continue;
                if(adj[i][j]==(int)1e9 && adj[j][i]==(int)1e9) {
                    flag=false;
                    break;
                }
            }
            if(flag) ret++;
        }
        return ret;
    }
    
    void init(int V, int[][] edge) {
        this.V=V;
        this.V=V;
        this.adj=new int[V][V];
        for(int i=0; i<V; i++) for(int j=0; j<V; j++) {
            if(i==j) continue;
            adj[i][j]=(int)1e9;
        }
        for(int[] e: edge) {
            adj[e[0]][e[1]]=1;
        }        
    }
}