import java.util.*;
class Solution {
    /*
        모든 0~9 에서 현재 i부터 모든 숫자로 이동하는 최단거리 구함.
          (각 숫자의 인접한 정점들 인접행렬로 만들고 플로이드 수행.)
        현재 양손 4, 6 에서부터 최단거리 선택.
          만약 최단거리가 같다면 왼손을 고르는 경우, 오른손을 고르는 경우 모두 시뮬레이션?
          중복부분문제? 
            ㄴ> 존재.
          최적 부분 구조?
            ㄴ> 성립.
          dp사용.
    */

    final int MAX=(int)1e9;
    final int V=12;
    int[][] P={
        {1, 2, 3},
        {4, 5, 6},
        {7, 8, 9},
        {10, 0, 11}
    };
    
    int[] rg={0, -1, 0, 1, 0, -1, -1, 1, 1};
    int[] cg={0, 0, 1, 0, -1, -1, 1, 1, -1};
    int[] dist={1, 2, 2, 2, 2, 3, 3, 3, 3};
    int[][] adj;
    
    int N;
    char[] numb;
    
    int[][][] memo;
    public int solution(String numbers) {
        init();
        this.N=numbers.length();
        numb=numbers.toCharArray();
        
        this.memo=new int[N][10][10];
        for(int i=0; i<N; i++) {
            for(int j=0; j<10; j++) {
                Arrays.fill(memo[i][j], -1);
            }
        }
        
        return getBestDist(0, 4, 6);
    }
    
    // idx: 현재 입력할 숫자의 인덱스 (by numb)
    // lh: 왼손 현재 위치
    // rh: 오른손 현재 위치
    // 기저: idx==N
    // idx 최대: 100,000 | lh 최대: 13 | rh 최대: 13  (단위: 개)
    // 100_000*10*10=10_000_000
    int getBestDist(int idx, int lh, int rh) {
        if(idx==N) return 0;
        if(memo[idx][lh][rh]!=-1) return memo[idx][lh][rh];
        int tn=numb[idx]-'0';
        if(lh==tn||rh==tn) {
            return memo[idx][lh][rh]=getBestDist(idx+1, lh, rh)+1;
        }

        // min(왼손이 움직일 경우, 오른손이 움직일 경우)
        return memo[idx][lh][rh]=Math.min(getBestDist(idx+1, tn, rh)+adj[lh][tn], getBestDist(idx+1, lh, tn)+adj[rh][tn]);
    }

    void init() {
        // 플로이드 알고리즘 사용을 위한 인접 행렬과, MAX로의 초기화.
        this.adj=new int[V][V];
        for(int i=0; i<V; i++) {
            for(int j=0; j<V; j++) {
	              if(i==j) continue;
                adj[i][j]=MAX;
            }
        }
        
        // 모든 인접한 가중치 기록.
        for(int i=0; i<4; i++) {
            for(int j=0; j<3; j++) {
                int cur=P[i][j];
                for(int k=0; k<dist.length; k++) {
                    int ny=i+rg[k];
                    int nx=j+cg[k];
                    if(!valid(ny, nx)) continue;
                    int target=P[ny][nx];
                    adj[cur][target]=dist[k];
                }
            }
        }
        // 플로이드 알고리즘 사용.
        // 인접한 가중치들을 모든 정점간 최단거리로 확장.
        for(int k=0; k<V; k++) {
            for(int i=0; i<V; i++) {
                for(int j=0; j<V; j++) {
                    adj[i][j]=Math.min(adj[i][j], adj[i][k]+adj[k][j]);
                }
            }
        }
        
    }
    
    boolean valid(int y, int x) {
        return y>=0 && x>=0 && y<4 && x<3;
    }
}