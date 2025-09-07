import java.util.*;
class Solution {
    /*
        양방향 그래프.
        한 거점에 머무를 수 있음.
        왔던길 되돌아 갈 수 있음.
        gps_log 는 시간별 이동 기록. 좌->우 순으로 이동함.
    */
    // 오류가 있는 데이터의 수정 최소 횟수.
    // 수정 불가: -1
    int[][] fld;
    int[][] adj;
    int V;
    int[] arr;
    int MAX;
    int ret;
    Map<String, Integer> vis;
    int goal;
    public int solution(int n, int m, int[][] edge_list, int k, int[] gps_log) {

        init(n+1, edge_list);
        this.vis=new HashMap<>();
        this.arr=gps_log;
        this.MAX=(int)1e9;
        this.goal=arr[arr.length-1];
        this.ret=MAX;
        getBest(arr[0], 1, 0);
        return ret==MAX ? -1 : ret;
    }
    
    void getBest(int cur, int nextIdx, int fixed) {
        if(arr.length-nextIdx<fld[cur][goal]) return;
            
        if(fixed>=ret) return;
        String key=nextIdx+"|"+cur;
        Integer mapfix=vis.get(key);
        if(mapfix==null || mapfix>fixed) {
            vis.put(key, fixed);
        } else return;
        
        if(nextIdx==arr.length) {
            if(cur==arr[arr.length-1]) ret=Math.min(ret, fixed);
            return;
        }
        
        int next=arr[nextIdx];
        for(int i=0; i<V; i++) {
            if(adj[cur][i]==0) continue;
            int fix=0;
            if(i!=next) fix=1;
            getBest(i, nextIdx+1, fixed+fix);
        }
        
    }
    
    void init(int V, int[][] edge) {
        this.V=V;
        this.adj=new int[V][V];
        this.fld=new int[V][V];
        for(int i=0; i<V; i++) {
            adj[i][i]=1;
            Arrays.fill(fld[i], (int)1e9);
            fld[i][i]=0;
        }
        for(int[] e: edge) {
            adj[e[0]][e[1]]=1;
            adj[e[1]][e[0]]=1;
            fld[e[0]][e[1]]=1;
            fld[e[1]][e[0]]=1;
        }
        
        for(int k=0; k<V; k++) {
            for(int i=0; i<V; i++) {
                if(fld[i][k]>=(int)1e9) continue;
                for(int j=0; j<V; j++) {
                    fld[i][j]=Math.min(fld[i][j], fld[i][k]+fld[k][j]);
                }
            }
        }
    }
}