import java.util.*;
class Solution {
    @SuppressWarnings("unchecked")
    public int solution(int n, int[][] edge) {
        List<Integer>[] adj=new ArrayList[n+1];
        for(int i=1; i<=n; i++) adj[i]=new ArrayList<>();
        for(int[] e: edge) { adj[e[0]].add(e[1]); adj[e[1]].add(e[0]); }
        
        Deque<Integer> q=new ArrayDeque<>();
        q.add(1);
        boolean[] vis=new boolean[n+1];
        int round=0;
        vis[1]=true;
        while(!q.isEmpty()) {
            int size=q.size();
            for(int i=0; i<size; i++) {
                int cur=q.poll();
                
                for(int next: adj[cur]) {
                    if(!vis[next]) {
                        q.addLast(next);
                        vis[next]=true;
                    }
                }
            }
            round++;
            if(q.isEmpty()) return size;
        }
        return -1;
    }
}