class Solution {
    final int MAX=(int)1e9;
    int[][] memo;
    public int[] solution(int target) {
        this.memo=new int[target+1][];
        int[] ret=dart(target);
        return ret;
    }
    
    int[] dart(int cur) {
        if(cur==0) return new int[2];
        if(cur<0) return new int[] {MAX, 0};
        if(cur<=20) return new int[] {1, 1};
        if(memo[cur]!=null) return memo[cur];
        // 불
        int[] ret=new int[] {MAX, 0};
        int[] bcandi=dart(cur-50);
        ret[0]=bcandi[0];
        ret[1]=bcandi[1];
        ret[0]++;
        ret[1]++;
        
        // 싱글 더블 트리플
        for(int i=1; i<=3; i++) {
            int[] immut=null;
            int hi=i*20;
            if(cur>hi) {
                immut=dart(cur-hi);
            } else {
                int next=cur%i;
                immut=dart(next);
            }
            int[] candi=new int[] {immut[0], immut[1]};
            candi[0]++;
            if(i==1) candi[1]++;
            if(candi[0]<ret[0]||(candi[0]==ret[0]&&candi[1]>ret[1])) {
                ret=candi;
            }
        }
        return memo[cur]=ret;
    }
}