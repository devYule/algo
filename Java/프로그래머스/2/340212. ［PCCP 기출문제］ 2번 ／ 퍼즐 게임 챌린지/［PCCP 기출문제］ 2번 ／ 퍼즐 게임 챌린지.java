class Solution {
    // 지금 문제는 diff - level 번 틀림.
    // 지금 문제를 틀리면 이전문제를 풀고와야함.
    // 제한시간 내에 퍼즐을 모두 해결하기 위한 숙련도의 최소값.
	/*
      diffs.length = times.length = n <= 300_000
      diffs[i] <= 100_000, times[i] <= 10_000
      
      diffs[i] 의 최대값 - 최소값 의 차 = m 일 때 
      O(NlogM)
    */
    int n;
    int[] diffs, times;
    long limit;
    public int solution(int[] diffs, int[] times, long limit) {
        this.n = diffs.length;
        this.diffs = diffs;
        this.times = times;
        this.limit = limit;
		int min = (int) 1e9;
        int max = -1;
        
        for(int i = 0; i < n; i++) {
            min = Math.min(min, diffs[i]);
            max = Math.max(max, diffs[i]);
        }
        
        return dec(min, max);
    }
    
    
    // 해당 레벨로 시뮬레이션 진행.
    boolean simulate(int level) {
        long dur = 0;
        for(int i = 0; i < n; i++) {
            dur += times[i];
            if(diffs[i] > level) {
                dur += getAddTime(i, diffs[i] - level);
            }
            if(dur > limit) return false;
        }
        return true;
    }
    
    // 결정문제
    // limit 이하로 문제를 해결할 수 있는 최소레벨.
    int dec(int min, int max) {
        int lo = min;
        int hi = max;
        while(lo <= hi) {
            int mid = (lo + hi) >>> 1;
            // 통과할 경우
            if(simulate(mid)) {
                // 레벨 내려보기
                hi = mid - 1;
            } else lo = mid + 1;
        }
        return lo;
    }
    
    // i번 문제를 w번 틀렸을 경우 획득.
    int getAddTime(int i, int w) {
        if(i == 0) return times[i] * w;
        return (times[i - 1] + times[i]) * w;
    }
}