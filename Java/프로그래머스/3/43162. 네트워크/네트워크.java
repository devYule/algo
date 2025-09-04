import java.util.*;
class Solution {
    public int solution(int n, int[][] computers) {
        DSU dsu=new DSU(n);
        for(int i=0; i<computers.length; i++) {
            for(int j=0; j<computers.length; j++) {
                if(i==j || computers[i][j]==0) continue;
                if(dsu.find(i)==dsu.find(j)) continue;
                dsu.union(i, j);
            }
        }
        int cnt=0;
        int[] mark=new int[n];
        for(int i=0; i<n; i++) {
            mark[dsu.find(i)]++;
        }
        
        int ret=0;
        for(int i=0; i<n; i++) {
            if(mark[i]!=0) ret++;
        }
        
        return ret;
    }
    
    static class DSU {
        int[] rank, uf;
        DSU(int n) {
            this.rank=new int[n];
            this.uf=new int[n];
            for(int i=1; i<n; i++) uf[i]=i;
        }
        
        int find(int a) {
            if(uf[a]==a) return a;
            return uf[a]=find(uf[a]);
        }
        
        void union(int a, int b) {
            int fa=find(a);
            int fb=find(b);
            if(fa==fb) return;
            if(rank[fa]<rank[fb]) {
                int tmp=fa;
                fa=fb;
                fb=tmp;
            }
            if(rank[fa]==rank[fb]) rank[fa]++;
            uf[fb]=fa;
        }
    }
}