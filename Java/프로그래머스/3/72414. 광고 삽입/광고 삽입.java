import java.util.*;
class Solution {
    /*
        HH:MM:SS
        (HH*60+MM)*60+SS
        최대 359_999초 (99:59:59)
    */
    
    // 목표: 가장 빠른 "시작 시간".
    public String solution(String play_time, String adv_time, String[] logs) {
        int n=logs.length;
        
        String[] pts=play_time.split(":");
        String[] ats=adv_time.split(":");
        int pt=tosec(pts[0], pts[1], pts[2]);
        int at=tosec(ats[0], ats[1], ats[2]);
        int[] timeline=new int[pt+1];
        for(int i=0; i<n; i++) {
            String[] split=logs[i].split("-");
            String[] from=split[0].split(":");
            int fromint=tosec(from[0], from[1], from[2]);
            
            String[] to=split[1].split(":");
            int toint=tosec(to[0], to[1], to[2]);
            
            timeline[fromint]++;
            timeline[toint]--;
        }
        for(int i=1; i<timeline.length; i++) timeline[i]+=timeline[i-1];
        
        return totime(window(0, at, timeline));
    }
    
    int window(int left, int right, int[] timeline) {
        long bestCnt=0;
        int bestStart=0;
        for(int i=left; i<=right; i++) {
            bestCnt+=timeline[i];
        }

        
        long cur=bestCnt;
        while(right+1<timeline.length) {
            // left 포인터 지점 빼고,
            cur-=timeline[left];
            // 포인터 올리고,
            left++;
            right++;
            
            // cur 가 bestCnt보다 큰 경우만 갱신 (같으면 빠른게 우선.)
            if(bestCnt<cur) {
                bestCnt=cur;
                bestStart=left;
            }
            // right 포인터 지점 더하기
            cur+=timeline[right];
        }
        
        return bestStart;
    }
    
    // --
    int tosec(String h, String m, String s) {
        return (toInt(h)*60+toInt(m))*60+toInt(s);
    }
    String totime(int sec) {
        int h=sec/3600;
        sec%=3600;
        int m=sec/60;
        sec%=60;
        int s=sec;
        return String.format("%02d:%02d:%02d", h, m, s);
    }
    int toInt(String s) {
        return Integer.parseInt(s);
    }
}