import java.util.*;
class Solution {
    List<Queue<int[]>> line;
    List<Integer> pre, post;
    public int[][] solution(int[][] nodeinfo) {
        for(int i=0; i<nodeinfo.length; i++) {
            nodeinfo[i]=new int[] {nodeinfo[i][0], nodeinfo[i][1], i+1};
        }
        
        Arrays.sort(nodeinfo, (a, b) -> a[1]==b[1] ? a[0]-b[0] : b[1]-a[1]);
        this.pre=new ArrayList<>();
        this.post=new ArrayList<>();
        this.line=new ArrayList<>();
        
        int prev=-1;
        for(int i=0; i<nodeinfo.length; i++) {
            int[] n=nodeinfo[i];
            if(prev!=n[1]) line.add(new LinkedList<>());
            line.get(line.size()-1).add(new int[] {n[0], n[2]});
            prev=n[1];
        }
        int[] root=line.get(0).poll();
        
        set(1, root, (int)1e9);
        
        int[][] ret=new int[2][nodeinfo.length];
        for(int i=0; i<nodeinfo.length; i++) {
            ret[0][i]=pre.get(i);
            ret[1][i]=post.get(i);
        }
        
        return ret;
    }
    
    void set(int linei, int[] parent, int limit) {
        if(parent==null) return;
        if(linei>=line.size()) {
            pre.add(parent[1]);
            post.add(parent[1]);
            return;
        }
        Queue<int[]> curl=line.get(linei);
        pre.add(parent[1]);
        int[][] lr=new int[2][];
        while(!curl.isEmpty() && curl.peek()[0]<limit) {
            int[] curs=curl.poll();
            if(curs[0]<parent[0]) lr[0]=curs;
            else lr[1]=curs;
        }
        
        set(linei+1, lr[0], parent[0]);
        set(linei+1, lr[1], limit);
        
        post.add(parent[1]);
    }
}