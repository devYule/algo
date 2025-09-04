class Solution {
    public int solution(String[] want, int[] number, String[] discount) {
        int n=discount.length;
        int m=want.length;
        int left=0;
        int right=10;
        
        int[] things=toIdx(want, discount);
        
        int ret=0;
        while(left+m<n) {
            int[] canbuy=new int[m];
            for(int i=left; i<right; i++) {
                int t=things[i];
                if(t==-1) continue;
                canbuy[t]++;
            }
            
            boolean flag=true;
            for(int i=0; i<m; i++) {
                if(number[i]>canbuy[i]) {
                    flag=false;
                }
            }
            if(flag) ret++;
            left++;
            if(right<n) right++;
        }
        return ret;
    }
    
    int[] toIdx(String[] w, String[] d) {
        int[] ret=new int[d.length];
        for(int i=0; i<d.length; i++) {
            String dt=d[i];
            ret[i]=-1;
            for(int j=0; j<w.length; j++) {
                if(w[j].equals(dt)) {
                    ret[i]=j;
                }
            }
        }
        return ret;
    }
}