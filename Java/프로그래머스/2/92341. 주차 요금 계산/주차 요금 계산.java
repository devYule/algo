import java.util.*;
class Solution {
    List<int[]>[] hist;
    int BASE, FREE_TIME, PER_MINUTE, FEE;
    
    @SuppressWarnings("unchecked")
    public int[] solution(int[] fees, String[] records) {
        this.BASE=fees[1];
        this.FREE_TIME=fees[0];
        this.PER_MINUTE=fees[2];
        this.FEE=fees[3];
        
        hist=new ArrayList[10000];
        
        List<Integer> carList=new ArrayList<>();
        
        for(String ro: records) {
            String[] r=ro.split(" ");
            String[] times=r[0].split(":");
            int time=toMin(times[0], times[1]);
            int car=toInt(r[1]);
            boolean isEnter=isEnter(r[2]);
            
            if(hist[car]==null) {
                carList.add(car);
                hist[car]=new ArrayList<>();
            }
            
            if(isEnter) {
                enter(time, car);
            } else {
                out(time, car);
            }
        }
        
        carList.sort((a, b) -> a-b);
        int[] ret=new int[carList.size()];
        for(int i=0; i<carList.size(); i++) {
            int car=carList.get(i);
            int tot=0;
            for(int[] times: hist[car]) {
                if(times[1]==-1) tot+=maxTime(times[0]);
                else tot+=times[1];
            }
            ret[i]=calc(tot);
        }
        
        return ret;
    }
    
    void enter(int time, int car) {
        hist[car].add(new int[] {time, -1});
    }
    
    void out(int time, int car) {
        List<int[]> his=hist[car];
        int[] r=his.get(his.size()-1);
        r[1]=time-r[0];
    }
    
    
    int calc(int duration) {
        if(duration-FREE_TIME<=0) return BASE;
        boolean hasRest=(duration-FREE_TIME)%PER_MINUTE>0;
        return BASE+(((duration-FREE_TIME)/PER_MINUTE)+(hasRest?1:0))*FEE;
    }
    
    int toInt(String n) {
        return Integer.parseInt(n);
    }
    
    int toMin(String t, String m) {
        return toInt(t)*60+toInt(m);
    }
    
    boolean isEnter(String flag) {
        return "IN".equals(flag);
    }
    
    int maxTime(int enter) {
        return toMin("23", "59")-enter;
    }
}