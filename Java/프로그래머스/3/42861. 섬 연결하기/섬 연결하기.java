class Solution {
    public int solution(int n, int[][] costs) {
        java.util.Arrays.sort(costs, (a, b)->a[2]-b[2]);
        DSU dsu=new DSU(n);
        int ret=0;
        for(int[] c: costs) {
            int a=c[0];
            int b=c[1];
            
            if(dsu.find(a)==dsu.find(b)) continue;
            
            dsu.union(a, b);
            ret+=c[2];
        }
        return ret;
    }
    
    static class DSU {
        int[] uf, rank;
        int n;
        DSU(int n) {
            this.n=n;
            this.uf=new int[n];
            this.rank=new int[n];
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