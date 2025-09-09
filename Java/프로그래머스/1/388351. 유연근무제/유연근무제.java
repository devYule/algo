class Solution {
    public int solution(int[] schedules, int[][] timelogs, int startday) {
        int n=schedules.length;
        int[] p=new int[n];
        java.util.Arrays.fill(p, 1);
        startday--;
        for(int i=0; i<7; i++) {
            int today=(startday+i)%7;
            if(today>=5) continue;
            for(int j=0; j<n; j++) {
                if(p[j]==0 || isPass(schedules[j], timelogs[j][i])) continue;
                p[j]=0;
            }
        }
        return java.util.Arrays.stream(p).sum();
    }
    
    boolean isPass(int ref, int time) {
        if(ref<time) {
            ref+=10;
            if(ref%100>=60) { ref+=100; ref-=60; }
        }
        return time<=ref;
    }
}