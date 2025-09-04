import java.util.*;
class Solution {
    int n;
    
    // [0]: 시작 시간, [1]: 시작 분, [2]: 끝 시간, [3]: 끝 분
    int[][] times;
    
    // 끝나는 시간, 분 만 기록. 오름차순.
    PriorityQueue<int[]> end;
    public int solution(String[][] book_time) {
        this.n=book_time.length;
        this.times=new int[n][];
        for(int i=0; i<n; i++) {
            String[] t=book_time[i];
            String[] from=t[0].split(":");
            String[] to=t[1].split(":");
            int[] ft=parse(from[0], from[1]);
            int[] tt=parse(to[0], to[1]);
            this.times[i]=new int[] {ft[0], ft[1], tt[0], tt[1]};
        }
        
        Arrays.sort(times, (a, b) -> a[0]==b[0] ? a[1]-b[1] : a[0]-b[0]);
        this.end=new PriorityQueue<>((a, b) -> a[0]==b[0] ? a[1]-b[1] : a[0]-b[0]);
        
        return getMin();
    }
    
    int getMin() {
        int[] first=times[0];
        end.add(new int[] {first[2], first[3]});
        int room=1;
        
        for(int i=1; i<n; i++) {
            int[] t=times[i];
            int hfrom=t[0];
            int mfrom=t[1];
            
            int[] fast=end.peek();
            int hf=fast[0];
            int mf=fast[1]+10;
            
            if(mf>=60) {
                mf%=60;
                hf++;
            }
            
            // 방 추가조건:
            // 대여 시작 시간이 가장 빠른 종료시간+청소 시간의
            //   1. 시간이 같고, 분은 더 이전인 경우.
            //   2. 시간이 더 이전인 경우.
            if((hfrom==hf && mfrom>=mf) || hfrom>hf) end.poll();
            else room++;
            
            // 종료시간 큐에 추가.
            end.add(new int[] {t[2], t[3]});
        }
        return room;
    }
    
    int[] parse(String h, String m) {
        return new int[] {Integer.parseInt(h), Integer.parseInt(m)};
    }
}