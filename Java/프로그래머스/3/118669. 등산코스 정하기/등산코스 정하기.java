import java.util.*;
class Solution {

    final int MAX=(int)1e9;
    int V;
    List<int[]>[] adj;
    
    boolean[] isGoal;
    boolean[] isTop;
    
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        // 그래프 초기화.
        init(n+1, paths);
        this.isGoal=new boolean[V];
        this.isTop=new boolean[V];
        for(int i=0; i<summits.length; i++) {
            int top=summits[i];
            isTop[top]=true;
        }
        for(int j=0; j<gates.length; j++) {
            int goal=gates[j];
            if(!isGoal[goal]) isGoal[goal]=true;
        }

        // 각 출발지에서 모든 다익스트라.
        return dijk(gates);
    }
    
    int[] dijk(int[] start) {
        int[] dist=new int[V];
        Arrays.fill(dist, MAX);
         
        PriorityQueue<int[]> q=new PriorityQueue<>((a, b) -> a[0]-b[0]);
        
        for(int s: start) {
            dist[s]=0;
            q.add(new int[] {0, s});
        }
        
        while(!q.isEmpty()) {
            int[] curs=q.poll();
            int cur=curs[1];
            int cost=curs[0];
            if(dist[cur]<cost) continue;
            if(isTop[cur]) continue;
            for(int[] nexts: adj[cur]) {
                int next=nexts[1];
                int nextDist=Math.max(cost, nexts[0]);
                if(dist[next]<=nextDist||isGoal[next]) continue;
                dist[next]=nextDist;
                q.add(new int[] {nextDist, next});
            }
        }
        
        int tdist=MAX;
        int top=MAX;
        
        for(int i=V-1; i>=0; i--) {
            if(!isTop[i]) continue;
            if(tdist>=dist[i]) {
                tdist=dist[i];
                top=i;
            }
        }
        
        return new int[] {top, tdist};
    }
    
    @SuppressWarnings("unchecked")
    void init(int V, int[][] edge) {
        this.V=V;
        this.adj=new ArrayList[V];
        for(int i=1; i<V; i++) adj[i]=new ArrayList<>();
        for(int[] e: edge) {
            int a=e[0];
            int b=e[1];
            int d=e[2];
            adj[a].add(new int[] {d, b});
            adj[b].add(new int[] {d, a});
        }
    }
}