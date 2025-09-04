import java.util.*;
class Solution {
    /*
        n회 t분 간격, m명 탑승.
    */
    // r: 버스를 탈 수 있는 제일 늦은 대기줄 도착시간
    public String solution(int n, int t, int m, String[] timetable) {
        // n==0 인 순간에 탈 수 있는 인원중 마지막 사람의 시간보다 빨라야 한다.
        int[] tt=new int[timetable.length];
        for(int i=0; i<timetable.length; i++) tt[i]=tomin(timetable[i].split(":"));
        Arrays.sort(tt);
        int time=tomin(new String[] {"9", "0"});
        int cursor=0;
        int on=0;
        for(int i=0; i<n; i++) {
            on=0;
            while(cursor<tt.length && tt[cursor]<=time && on<m) {
                cursor++;
                on++;
            }
            if(i<n-1) time+=t;
        }
        // time: 마지막 차 도착시간.
        if(on==m) return totime(tt[cursor-1]-1);
        return totime(time);
    }
    
    int tomin(String[] hm) {
        return Integer.parseInt(hm[0])*60 + Integer.parseInt(hm[1]);
    }
    
    String totime(int t) {
        return String.format("%02d", t/60) + ":" + String.format("%02d", t%60);
    }
}