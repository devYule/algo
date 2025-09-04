class Solution {
    public int solution(int[] a) {
        int n=a.length;
        boolean[] selected=new boolean[n];
        Segment sg=new Segment(a);
        selected[0]=true;
        selected[n-1]=true;
        for(int i=1; i<n-1; i++) {
            int left=sg.query(0, i-1);
            int right=sg.query(i+1, n-1);
            int center=i;
            if(a[center]<a[left] || a[center]<a[right]) selected[center]=true;
            selected[left]=true;
            selected[right]=true;
        }
        int ret=0;
        for(int i=0; i<n; i++) if(selected[i]) ret++;
        return ret;
    }
    
    static class Segment {
        int[] tree, o;
        int n;
        Segment(int[] o) {
            this.o=o;
            this.n=o.length;
            int tl=0;
            int p=0;
            while(true) {
                int pow=(int)Math.pow(2, p);
                tl+=pow;
                if(pow>n) break;
                p++;
            }
            this.tree=new int[tl];
            init(0, n-1, 1);
        }
        
        int init(int l, int r, int nd) {
            if(l==r) return tree[nd]=l;
            int mid=(l+r)>>>1;
            int lr=init(l, mid, nd*2);
            int rr=init(mid+1, r, nd*2+1);
            if(o[lr]<o[rr]) return tree[nd]=lr;
            return tree[nd]=rr;
        }
        
        int query(int l, int r) {
            return query(l, r, 0, n-1, 1);
        }
        
        int query(int bl, int br, int l, int r, int nd) {
            if(l>br || r<bl) return -1;
            if(l>=bl && r<=br) return tree[nd];
            int mid=(l+r)>>>1;
            int lr=query(bl, br, l, mid, nd*2);
            int rr=query(bl, br, mid+1, r, nd*2+1);
            if(lr==-1) return rr;
            if(rr==-1) return lr;
            if(o[lr]<o[rr]) return lr;
            return rr;
        }
    }
}