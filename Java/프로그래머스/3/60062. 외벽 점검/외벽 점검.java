import java.util.*;
class Solution {
    public int solution(int n, int[] weak, int[] dist) {
        List<List<Integer>> candi=new ArrayList<>();
        permut(dist.length, 0, new ArrayList<>(), candi);
        
        boolean[] isWeak=new boolean[n];
        for(int w: weak) isWeak[w]=true;
        
        int ret=(int)1e9;
        for(int i=0; i<weak.length; i++) {
            for(List<Integer> da: candi) {
                int ltrgoal=i==0 ? weak[weak.length-1] : n+weak[i-1];
                int rtlgoal=i==weak.length-1 ? weak[0] : weak[i+1]-n;

                int curltr=weak[i];
                int currtl=weak[i];

                for(int j=0; j<da.size(); j++) {
                    int d=dist[da.get(j)];
                    // 시계방향
                    curltr+=d;
                    if(curltr>=ltrgoal) {
                        ret=Math.min(ret, j+1);
                        break;
                    }
                    curltr++;
                    // 다음 가까운 취약지점.
                    while(!isWeak[curltr%n]) curltr++;
                }
            }
        }
        return ret==(int)1e9 ? -1 : ret;
    }
    void permut(int r, int mask, List<Integer> holder, List<List<Integer>> ret) {
        if(Integer.bitCount(mask)==r) {
            ret.add(new ArrayList<>(holder));
            return;
        } 
        for(int i=0; i<r; i++) {
            if((mask&1<<i)!=0) continue;
            holder.add(i);
            permut(r, mask|1<<i, holder, ret);
            holder.remove(holder.size()-1);
        }
    }
}