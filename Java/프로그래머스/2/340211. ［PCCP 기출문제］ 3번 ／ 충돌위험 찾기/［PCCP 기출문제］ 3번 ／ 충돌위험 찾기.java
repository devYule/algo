import java.util.*;
class Solution {
    // points 는 지점.
    // routes[i][0] 번 지점에서 routes[i][1] 번 지점을 향해 i번 로봇이 최단거리로 이동한다.
    // 경로가 중복되는 경로의 개수를 구한다.
    
    int[][] map;
    int[][] points, routes;
    
    @SuppressWarnings("unchecked")
    public int solution(int[][] points, int[][] routes) {
        // 100 * 100 지도.
        // 초기값: 0
        // 이동시 값이 0 이면 1으로 변경.
        //       값이 1이면 결과에 1더하고 -1으로 변경.
        
        this.map = new int[101][101];
        
        List<int[]>[] road = new ArrayList[routes.length];
        for(int i = 0; i < road.length; i++) road[i] = new ArrayList<>();
		int maxTime = 0;        
        // i: 로봇
        for(int i = 0; i < routes.length; i++) {
            for(int j = 1; j < routes[i].length; j++) {
                int from = routes[i][j - 1] - 1;
                int to = routes[i][j] - 1;
                go(points[from][0], points[from][1], points[to], road[i]);
                if(j < routes[i].length - 1) road[i].remove(road[i].size() - 1);
            }
            maxTime = Math.max(maxTime, road[i].size());
        }
        int dup = 0;
        
        
        // 
        for(int i = 0; i < maxTime; i++) {
            int time = i;
            List<int[]> bt = new ArrayList<>();
            for(int rob = 0; rob < routes.length; rob++) {
                if(road[rob].size() <= time) continue;
                int[] ra = road[rob].get(time);

                bt.add(ra);
                int ry = ra[0];
                int rx = ra[1];
                map[ry][rx]++;
            }
            for(int[] b: bt) {
                if(map[b[0]][b[1]] > 1) {
                    dup++;
                }
                map[b[0]][b[1]] = 0;
            }
        }
        //
        
        
        return dup;
    }
    
    // cur, to: 현재, 도착 좌표
    void go(int iy, int ix, int[] to, List<int[]> road) {
        int goaly = to[0];
        int goalx = to[1];
        road.add(new int[] {iy, ix});
        while(iy != goaly) {
            if(iy > goaly) iy--;
            else iy++;
            road.add(new int[] {iy, ix});
        }
        
        while(ix != goalx) {
            if(ix > goalx) ix--;
            else ix++;
            road.add(new int[] {iy, ix});
        }
    }
    

}