import java.util.*;
class Solution {
    // 도넛: 단일 사이클 -> 사이클 1개
    // 막대: 사이클 X
    // 8자: 2개 이상의 사이클 -> 사이클이 언제나 자신의 부모가 아닌 정점을 향함.
    
    // 단방향 간선.
    // [생성된 정점 번호, 도넛그래프 개수, 막대그래프 개수, 8자 그래프 개수]
    int[] in;
    int[] out;
    // 정점 번호가 일정하지 않을 수 있으므로 Map 사용.
    Map<Integer, List<Integer>> adj;
    int added;
    
    int MAX_NODE = -1;
    
    public int[] solution(int[][] edges) {
        this.in = new int[1_000_001];
        this.out = new int[1_000_001];
		init(edges);
        
        for(int i = 0; i <= MAX_NODE; i++) {
            if(in[i] == 0 && out[i] > 1) {
                added = i;
                break;
            }
        }

        // 진입 진출 횟수를 통해 신규정점 판별.
        int[] ret = bfs();
        
        return ret;
    }
    
    
    // 신규정점에서 각 자식별로 탐색 진행.
    // cycle 1개면 [1]++
    // cycle 2개 이상이면 [3]++
    // cycle 0이면 [2]++
    // 다시 신규 정점으로 돌아올일은 없으므로 자식별로 visited 생성할 수 있음.
    int[] bfs() {
        int[] ret = new int[4];
        ret[0] = added;
        
        for(int start: adj.get(added)) {
            boolean[] visited = new boolean[MAX_NODE + 1];
            Queue<Integer> q = new LinkedList<>();
            q.add(start);
            visited[start] = true;
            visited[added] = true;
            
            // 큐 종료 후 find = false 면 막대 그래프.
            int cycleCount = 0;
            
            while(!q.isEmpty()) {
                int cur = q.poll();
                
                if(adj.get(cur) == null) continue;
                for(int next: adj.get(cur)) {
                    if(visited[next]) {
                        cycleCount++;
                        continue;
                    }
                    visited[next] = true;
                    q.add(next);
                }
            }
            if(cycleCount > 1) {
                ret[3]++;
            } else if(cycleCount == 0) {
				ret[2]++;
            } else ret[cycleCount]++;
            
        }
        return ret;
    }
    
    void init(int[][] edges) {
        this.adj = new HashMap<>();
        for(int[] e: edges) {
            int from = e[0];
            int to = e[1];
            in[to]++;
            out[from]++;
            if(adj.get(from) == null) adj.put(from, new ArrayList<>());
            adj.get(from).add(to);
            MAX_NODE = Math.max(MAX_NODE, Math.max(from, to));
        }
    }
}