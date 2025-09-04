class Solution {
    /*
        시작: 시간ms - 경과시간 +1; (ms)
        끝 : lines에 있는 시간 (ms)
        
    */
    public int solution(String[] lines) {
        int n=lines.length;
        
        int maxTime=0;
        int[][] dur=new int[n][2];
        for(int i=0; i<n; i++) {
            String[] split=lines[i].split(" ");
            double latency=Double.parseDouble(split[2].substring(0, split[2].length()-1));
            int start=toMs(split[1].split(":"));
            dur[i][0]=(start-((int)(latency*1000))+1);
            dur[i][1]=start;
            maxTime=Math.max(maxTime, dur[i][1]);
        }
        

        int times[]=new int[maxTime+1];
        for(int i=0; i<n; i++) {
            int[] cur=dur[i];
            // 1초간의 처리시간 기록.
            times[Math.max(0, cur[0])]++;
            if(cur[1]+1000<times.length) times[cur[1]+1000]--;
        }
        
        for(int i=1; i<times.length; i++) times[i]+=times[i-1];
        
        return java.util.Arrays.stream(times).max().orElse(-1);
    }
    
    
    int toMs(String[] t) {
        String[] mss=t[2].split("\\.");
        return (toInt(t[0])*3600 + toInt(t[1])*60 + toInt(mss[0])) * 1000 + toInt(mss[1]);
    }
    
    int toInt(String n) {
        return Integer.parseInt(n);
    }
}