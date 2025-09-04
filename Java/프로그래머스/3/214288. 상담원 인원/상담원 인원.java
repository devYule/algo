import java.util.*;
class Solution {
    // 멘토: n명
    // 상담유형 1~n로 분류
    
    // 각 멘토는 k개의 "상담 유형" 중 하나만 담당.
    // 각 멘토는 동시에 한 명과만 상담 가능.
    
    // 참가자의 상담 신청 -> 유형 일치 멘토가 상담.
    // 멘토가 남아있지 않으면 대기.
    // ㄴ> 대기 시간: 상담 요청 시간~상담 시작 시간
    // 상담 대기자는 상담 요청 선착순.
    
    // 목표: 유형별로 멘토 인원을 정했을 때 "대기시간의 합이 최소값".
    // ㄴ> 각 유형별로 멘토 인원은 최소 1명 이상.
    
    List<int[]>[] R;
    @SuppressWarnings("unchecked")
    public int solution(int k, int n, int[][] reqs) {
        R=new ArrayList[k+1];
        for(int i=1; i<=k; i++) R[i]=new ArrayList<>();
        
        for(int[] r: reqs) {
            int type=r[2];
            int from=r[0];
            int to=r[1];
            R[type].add(new int[] {from, to});
        }
        
        int max=n-k;
        
        return bf(max, 1, k);
    }
    
    int bf(int max, int type, int k) {
        if(type>k) return 0;
        int ret=(int)1e9;
        for(int i=0; i<=max; i++) {
            int cur=calc(1+i, type);
            ret=Math.min(ret, cur+bf(max-i, type+1, k));
        }
        return ret;
    }
    
    int calc(int cnt, int type) {
        List<int[]> p=R[type];
        int rest=cnt;
        int wait=0;
        PriorityQueue<Integer> q=new PriorityQueue<>((a, b) -> a-b);
        for(int[] info: p) {
            if(rest>0) {
                q.add(info[0]+info[1]);
                rest--;
            } else {
                int from=info[0];
                int fastEnd=q.poll();
                if(fastEnd>from) {
                    wait+=fastEnd-from;
                    q.add(fastEnd+info[1]);
                } else q.add(from+info[1]);
            }
        }
        return wait;
    }

}