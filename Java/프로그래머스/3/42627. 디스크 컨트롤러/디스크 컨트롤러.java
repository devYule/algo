import java.util.*;
    /*
        번호/요청시각/소요시간
        소요시간 짧은것 -> 요청시간 빠른것 -> 작업번호 작은것 순.
        
        반환시간: $해당 작업의 종료시간 - 해당 작업의 요청시간$
    */
    // 반환시간의 평균 반환. (정수)
    // [0]: 요청시간, [1]: 소요시간, i: 작업번호
class Solution {
    public int solution(int[][] jobs) {
        for(int i=0; i<jobs.length; i++) jobs[i]=new int[] {jobs[i][0], jobs[i][1], i};
     
        Arrays.sort(jobs, (a, b) -> a[0]-b[0]);
        PriorityQueue<int[]> q=new PriorityQueue<>((a, b)->a[1]==b[1] ? a[0]==b[0] ? a[2]-b[2] : a[0]-b[0] : a[1]-b[1]);
        
        int time=0;
        int tot=0;
        int i=0;
        while(true) {
            while(i<jobs.length && jobs[i][0]<=time) q.add(jobs[i++]);
            if(q.isEmpty()) {
                if(i==jobs.length) break;
                time++;
                continue;
            }
            int[] job=q.poll();
            time+=job[1];
            tot+=time-job[0];
        }
        return tot/jobs.length;
    }
}