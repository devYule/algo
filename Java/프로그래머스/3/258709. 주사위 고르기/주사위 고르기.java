import java.util.*;
class Solution {
    // A가 승리
    // A가 먼저 주사위의 절반개를 가져감.
    
    // 주사위는 2 <= n <= 10 의 짝수
    // 면은 6개.
    // 주사위 숫자는 1 <= k <= 100 로 구성
    
    // 목표: A가 승리하기 위해 골라야하는 주사위 번호 오름차순 정렬 반환.
    
    // score[][] = [A가 선택한 주사위 mask][승, 무, 패]

    // 6^5 * (10개에서 5개를 고르는 경우의 수.)
    // A와 B는 별도로 계산되므로, + 로 시간복잡도 더해짐.
    int[][] score;
    int[][] dice;
    int N;
    int M;
    int[][] bmemo;
    public int[] solution(int[][] dice) {
        this.dice = dice;
        this.N = dice.length;
        this.M = N / 2;
        this.score = new int[1 << N][];
        
        getScore(0);
        
        int maxIdx = -1;
        double maxPer = -1D;
        for(int i = 0; i < score.length; i++) {
            if(score[i] == null) continue;
            System.out.println(Arrays.toString(score[i]));
            
            int[] s = score[i];
            double game = s[0] + s[1] + s[2];
            double per = s[0] / game;
            if(per > maxPer) {
                maxPer = per;
                maxIdx = i;
            }
        }
        int[] ret = new int[M];
        int ri = 0;
        for(int i = 0; i < N; i++) {
            if((maxIdx & 1 << i) == 0) continue;
            ret[ri++] = i + 1;
        }
        
        return ret;
    }
    
    // select: A가 선택한 주사위.
    void getScore(int select) {
        if(Integer.bitCount(select) == M) {
            // 모든 선택 조합에 따른 승/패/무 기록.
            if(score[select] == null) {
                score[select] = new int[3];
                // 현재 주사위로 얻을 수 있는 b의 모든 점수.
                List<Integer> bsc = new ArrayList<>();
                bscore(~select & ((1<<N) - 1), 0, bsc);
                // 오름차순. (binary search 용도)
                bsc.sort((a, b) -> a - b);
                
                // A의 해당 주사위로 만들 수 있는 모든 점수 계산.
                // 미리 계산된 B의 현재 주사위로 만들 수 있는 점수들 전체에대해 binary search 로 승/무/패 계산.
                setScore(select, 0, select, bsc);
            }
            
            // 이미 계산된 경우 곧바로 return
            return;
        }
        
        for(int i = 0; i < N; i++) {
            if((select & 1 << i) != 0) continue;
            getScore(select | 1 << i);
        }
    }
    
    // A 가 mask 의 주사위를 골랐을 때의 승/패/무 를 score[mask] 에 기록.
    // mask: 남은 선택 가능한 주사위
    // ap: A의 점수.
    // asel: A가 선택한 주사위 원본
    void setScore(int mask, int ap, int asel, List<Integer> bsc) {
        if(mask == 0) {
            
            // binary search.
            // ap와 같은 가장 낮은 p1, 가장 높은 p2 를 구함.
            // p1 = 승수
            // bsc.size() - p2 = 패수
            // p2 - p1 = 무승부 수
            int bn = bsc.size() - 1;
            
            int p1 = getLeftEnd(0, bn, ap, bsc);
            int p2 = getRightEnd(0, bn, ap, bsc);
            
            score[asel][0] += p1;
            score[asel][1] += p2 - p1;
            score[asel][2] += bsc.size() - p2;
            
            return;
        }
        
        // 최초 1: 선택 주사위.
        int pos = 0;
        while((mask & 1 << pos) == 0) {
            pos++;
        }
        
        
        int[] d = dice[pos];
        for(int i = 0; i < 6; i++) {
            setScore(mask & (mask - 1), ap + d[i], asel, bsc);
        }
        
    }
    
    
    // B의 선택한 주사위 조합에 대한 모든 점수 미리 구해놓기 (중복 연산 방지)
    // 최적부분구조 성립 안하므로 dp 불가.
    void bscore(int mask, int bp, List<Integer> scores) {
        if(Integer.bitCount(mask) == 0) {
            scores.add(bp);
            return;
        }
        
        int pos = 0;
        while((mask & 1 << pos) == 0) {
            pos++;
        }
        
        int[] d = dice[pos];
        for(int i = 0; i < 6; i++) {
            bscore(mask & (mask - 1), bp + d[i], scores);
        }
    }
    
    int getLeftEnd(int lo, int hi, int target, List<Integer> bsc) {
        while(lo <= hi) {
            int mid = (lo + hi) >>> 1;
            if(bsc.get(mid) >= target) {
                hi = mid - 1;
            } else lo = mid + 1;
        }
        return lo;
    }
    
    int getRightEnd(int lo, int hi, int target, List<Integer> bsc) {
        while(lo <= hi) {
            int mid = (lo + hi) >>> 1;
            if(bsc.get(mid) <= target) {
                lo = mid + 1;
            } else hi = mid - 1;
        }
        return hi;
    }
}