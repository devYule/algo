import java.util.*;
class Solution {
    Map<Integer, List<Integer>> adj;
    // 0: 자식일 때 홀짝트리, 1: 자식일 때 역홀짝트리
    Map<Integer, Integer> type;
    public int[] solution(int[] nodes, int[][] edges) {
        init(nodes, edges);
        setType();

        return find(nodes);
    }
    
    int[] find(int[] nodes) {
        int[] ret=new int[2];
        Set<Integer> visited=new HashSet<>();

        for(int n: nodes) {
            if(visited.contains(n)) continue;
            int res=bfs(n, visited);
            if(res==-1) continue;
            if(res==2) {
                ret[0]++;
                ret[1]++;
                continue;
            }
            ret[res]++;
        }
        return ret;
    }
    
    int bfs(int start, Set<Integer> visited) {
        int ord=0;
        int inOrd=0;
        Queue<Integer> q=new LinkedList<>();
        q.add(start);
        visited.add(start);
        while(!q.isEmpty()) {
            int cur=q.poll();
            int t=type.get(cur);
            if(t==0) ord++;
            else inOrd++;
            for(int next: adj.get(cur)) {
                if(visited.contains(next)) continue;
                q.add(next);
                visited.add(next);
            }
        }
        
        return ord==1&&inOrd==1 ? 2 : ord==1 ? 1 : inOrd==1 ? 0 : -1;
    }
    
    
    void setType() {
        this.type=new HashMap<>();
        for(int node: adj.keySet()) {
            int whenSubChild=adj.get(node).size()-1;
            if(whenSubChild<0) type.put(node, (node+1)%2);
            else type.put(node, node%2==whenSubChild%2 ? 0 : 1);
        }
    }
    
    void init(int[] nodes, int[][] edge) {
        this.adj=new HashMap<>();
        for(int n: nodes) this.adj.put(n, new ArrayList<>());
        for(int[] e: edge) {
            int a=e[0];
            int b=e[1];
            
            this.adj.get(a).add(b);
            this.adj.get(b).add(a);
        }
    }
}