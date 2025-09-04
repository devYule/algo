class Solution {
    int[][] A;
    int[] ret;
    public int[] solution(int[][] arr) {
        this.A=arr;
        this.ret=new int[2];
        int n=arr.length;
        int res=merge(0, n-1, 0, n-1);
        if(res!=-1) ret[res]++;
        return ret;
    }
    
    // t, b, l, r 로 구성.
    // -1: 처리됨
    // 1: 1로 병합됨
    // 0: 0으로 병합됨.
    int merge(int t, int b, int l, int r) {
        if(t==b) return A[t][l];
        int rmid=(t+b)>>>1;
        int cmid=(l+r)>>>1;
        
        int lt=merge(t, rmid, l, cmid);
        int tr=merge(t, rmid, cmid+1, r);
        int bl=merge(rmid+1, b, l, cmid);
        int br=merge(rmid+1, b, cmid+1, r);
        
        if(lt==tr && tr==bl && bl==br) return lt;
        
        if(lt!=-1) ret[lt]++;
        if(tr!=-1) ret[tr]++;
        if(bl!=-1) ret[bl]++;
        if(br!=-1) ret[br]++;
        
        return -1;
    }
}