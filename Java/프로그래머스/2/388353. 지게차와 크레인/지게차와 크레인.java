import java.util.*;
class Solution {
    char[][] map;
    int[] rg = {-1, 0, 1, 0};
    int[] cg = {0, 1, 0, -1};
    int n, m;
    int[] rest;
    public int solution(String[] storage, String[] requests) {
        
		n = storage.length;
        m = storage[0].length();
        this.rest = new int[('Z' - 'A') + 1];
        
        int totCnt = 0;
		this.map = new char[n][m];
        for(int i = 0; i < n; i++) for(int j = 0; j < m; j++) {
            map[i][j] = storage[i].charAt(j);
            rest[map[i][j] - 'A']++;
            totCnt++;
        }
        for(String r: requests) {
            char target = r.charAt(0);
            if(rest[target - 'A'] <= 0) continue;
            
            if(r.length() == 1) {
                List<int[]> candi = new ArrayList<>();
                for(int i = 0; i < n; i++) {
                    for(int j = 0; j < m; j++) {
						if(map[i][j] == target && canRemove(i, j, new boolean[n][m])) candi.add(new int[] {i, j});
                    }
                }
				for(int i = 0; i < candi.size(); i++) {
                    int[] c = candi.get(i);
                    rest[map[c[0]][c[1]] - 'A']--;
                    totCnt--;
                    map[c[0]][c[1]] = '.';
                }
            } else {
                for(int i = 0; i < n; i++) {
                    for(int j = 0; j < m; j++) {
						if(map[i][j] == target) {
                            rest[map[i][j] - 'A']--;
                            totCnt--;
                            map[i][j] = '.';
                        }
                    }
                }
            }
        }

        return totCnt;
    }
    
    boolean canRemove(int y, int x, boolean[][] visited) {
       if(y == 0 || x == 0 || y == n - 1 || x == m - 1) return true;
       visited[y][x] = true;
       for(int i = 0; i < 4; i++) {
           int ny = y + rg[i];
           int nx = x + cg[i];
           if(ny >= 0 && nx >= 0 && ny < n && nx < m &&
              (visited[ny][nx] ||
              map[ny][nx] >= 'A' && map[ny][nx] <= 'Z')) continue;
           
           if(canRemove(ny, nx, visited)) return true;
       }
       return false;
    }
    
}