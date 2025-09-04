import java.util.*;
class Solution {
    /*
        n*n
        기둥: 바닥위에 있거나, 다른 기둥 위에 있거나, 보 위에 있어야 함.
        보 : 한 점이 기둥위에 있거나, 양끝이 다른 보와 연결되어 있어야 함.
        
        불가능한 건설은 무시됨.
        
        최종 구조물의 상태 반환.
          - [가로 좌표, 세로 좌표, 타입]
          - x좌표 기준 오름차순, y좌표 기준 오름차순.
          - 좌표 같다면, 기둥이 먼저.
    */
    /*
        기둥은 위로, 보는 오른쪽으로 건설됨.
    */
    // build_frame[i]= [x좌표, y좌표, 기둥: 0 | 보: 1, 삭제: 0 | 설치: 1]
    
    // 한 점에 기둥, 보 모두 설치되는 경우 존재 가능.
    // [y][x][0]: 기둥 설치 여부, [y][x][1]: 보 설치 여부
    int[][][] map;
    public int[][] solution(int n, int[][] build_frame) {
        // scale
        this.map=new int[n+1][n+1][2];
        
        simul(build_frame);
        
        /*
            최종 구조물의 상태 반환.
              - [가로 좌표, 세로 좌표, 타입]
              - x좌표 기준 오름차순, y좌표 기준 오름차순.
              - 좌표 같다면, 기둥이 먼저.
        */
        List<int[]> ret=new ArrayList<>();
        for(int i=0; i<=n; i++) {
            for(int j=0; j<=n; j++) {
                if(map[i][j][0]>0) {
                    ret.add(new int[] {j, i, 0});
                    // 제거.
                    map[i][j][0]--;
                }
                if(map[i][j][1]>0) {
                    ret.add(new int[] {j, i, 1});
                    // 제거.
                    map[i][j][1]--;
                }
            }
        }
        
        ret.sort((a, b) -> {
            if(a[0]!=b[0]) return a[0]-b[0];
            if(a[1]!=b[1]) return a[1]-b[1];
            return a[2]-b[2];
        });
        
        return ret.stream().toArray(int[][]::new);
    }
    
    void simul(int[][] bf) {
        
        for(int[] b: bf) {
            int y=b[1];
            int x=b[0];
            int type=b[2];
            boolean isBuild=b[3]==1;
            if(isBuild) {
                // 건설
                if(!valid(y, x, type)) continue;
                map[y][x][type]=1;
            } else {
                // 제거
                int back=map[y][x][type];
                map[y][x][type]=0;
                
                List<int[]> candi=new ArrayList<>();
                // 기둥: 확장점에 닿아있는 모든 기둥/보 확인.
                if(type==0) {
                    candi.add(new int[] {y+1, x, 0});
                    candi.add(new int[] {y+1, x, 1});
                    candi.add(new int[] {y+1, x-1, 1});
                }
                // 보: 원점 위의 기둥, 확장점 위의 기둥, 원점의 좌측 & 확장점 의 보 모두 확인
                else {
                    candi.add(new int[] {y, x, 0});
                    candi.add(new int[] {y, x+1, 0});
                    candi.add(new int[] {y, x-1, 1});
                    candi.add(new int[] {y, x+1, 1});
                }
                
                for(int[] c: candi) {
                    int cy=c[0];
                    int cx=c[1];
                    int ctype=c[2];
                    // 존재하지 않으면 체크할 필요 없음.
                    if(cx<0 || cx>=map.length || cy>=map.length || map[cy][cx][ctype]==0) continue;
           
                    // 어느 하나라도 유지가 불가능하면,
                    if(!valid(cy, cx, ctype)) {
                        // 원복.
                        map[y][x][type]=back;
                        break;
                    }
                }
            }
        }
    }
    
    // type 에 따른 정합성 검사.
    boolean valid(int y, int x, int type) {
        // 기둥일 경우
        // 원점이 바닥인경우 || 원점 아래가 기둥인 경우 || 원점이 보(좌우) 라면 가능.
        if(type==0) {
            if(y==0) return true;
            if(y>0 && map[y-1][x][0]==1) return true;
            if(map[y][x][1]==1) return true;
            if(x>0 && map[y][x-1][1]==1) return true;
            return false;
        }
        // 보 일 경우
        // 원점 아래 또는 확장점 아래가 기둥이면 가능. || 원점의 좌측, 원점의 우측이 모두 보라면 가능.
        if(type==1) {
            if(y>0 && map[y-1][x][0]==1) return true;
            if(y>0 && x+1<map.length && map[y-1][x+1][0]==1) return true;
            if(x>0 && x+1<map.length && map[y][x-1][1]==1 && map[y][x+1][1]==1) return true;
        }
        return false;
    }
}