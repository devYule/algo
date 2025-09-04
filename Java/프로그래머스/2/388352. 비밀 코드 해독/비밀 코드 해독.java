import java.util.*;
class Solution {
    List<List<Integer>> candi;
    public int solution(int n, int[][] q, int[] ans) {
        this.candi = new ArrayList<>();
        getAll(1, n, 5, new ArrayList<>());
        int r = clearNot(q, ans);
        
        return r;
    }
    
    int clearNot(int[][] q, int[] ans) {
        int ret = 0;
        
        for(List<Integer> c: candi) {
            boolean sati = true;
            for(int i = 0; i < q.length; i++) {
                // 만족 횟수.
                int cnt = 0;
                if(c.get(0) == 3 && c.get(1) == 4 && c.get(2) == 7 && c.get(3) == 9 && c.get(4) == 10) {
					System.out.println("ans[i]: " + ans[i]);
                }
                for(int j = 0; j < q[i].length; j++) {
                    int target = q[i][j];
                    int lo = 0;
                    int hi = 4;

                    while(lo <= hi) {
                        int mid = (lo + hi) >>> 1;
						if(c.get(mid) < target) lo = mid + 1;
                        else if(c.get(mid) > target) hi = mid - 1;
                        else {
                            cnt++;
                            break;
                        }
                    }
                    if(cnt > ans[i]) {
                        sati = false;
                        break;
                    }
                }
                if(!sati) break;
                if(cnt != ans[i]) {
                    sati = false;
                    break;
                }
            }
            if(sati) ret++;
        }
        
        return ret;
    }
    
    void getAll(int from, int to, int M, List<Integer> holder) {
        if(holder.size() == M) {
            candi.add(new ArrayList<>(holder));
            return;
        }
        
        for(int i = from; i <= to; i++) {
            holder.add(i);
            getAll(i + 1, to, M, holder);
            holder.remove(holder.size() - 1);
        }
    }
}