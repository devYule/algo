class Solution {
    /*
      14 6 5 11 3 9 2 10
    */
    int[] arr;
    int[][] memo;
    int[] limits;
    public int solution(int sticker[]) {
        int n=sticker.length;
        this.arr=sticker;
        this.limits=new int[] {n-1, n-2};
        this.memo=new int[n][2];
        return Math.max(find(0, 0), find(1, 1)+arr[n-1]);
    }
                        
    int find(int idx, int limiti) {
        if(idx>=limits[limiti]) return 0;
        if(memo[idx][limiti]!=0) return memo[idx][limiti];
        
        int ret=arr[idx]+find(idx+2, limiti);
        if(idx+1<limits[limiti]) ret=Math.max(ret, arr[idx+1]+find(idx+3, limiti));
        return memo[idx][limiti]=ret;
    }
}