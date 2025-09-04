import java.util.*;
class Solution {
    /*
        가장 큰 점수 차이로 이기기.
        방법이 여러가지라면, 낮은 점수를 더 많이 맞힌 경우 선택.
    */
    /*
        화살 최대 10발. (n<=10)
    */
    
    
    int[] y;
    
    // [0]: 획득 점수
    // [1]: 맞춘 제일 낮은 점수.
    List<int[]> wins;
    List<Integer> score;
    public int[] solution(int n, int[] info) {
        this.y=info;
        wins=new ArrayList<>();
        this.score=new ArrayList<>();
        simul(0, n, new int[11]);
        
        List<Integer> rank=getRank();
        
        return wins.isEmpty() ? new int[] {-1} : wins.get(rank.get(0));
    }
    
    // rest: 남은 화살 수
    // m: 내 점수 현황
    // y: 상대 점수 현황
    void simul(int idx, int rest, int[] m) {
        // 기저
        if(rest==0) {
            // 점수 결산.
            int score1=0, score2=0;
            for(int i=0; i<11; i++) {
                if(m[i]==0 && y[i]==0) continue;
                if(m[i]>y[i]) score1+=10-i;
                else score2+=10-i;
            }
            
            // 내 점수가 더 높으면 "과녁판과, 점수의 차이를" 결과배열에 추가.
            if(score1>score2) {
                wins.add(Arrays.copyOf(m, m.length));
                score.add(score1-score2);
            }
            return;
        }
        // idx==10 이면 해당 칸에 다 쏘기.
        if(idx==10) {
            m[idx]+=rest;
            simul(idx+1, 0, m);
            m[idx]-=rest;
            return;
        } 
        
        // 이번칸 쏘는경우/쏘지 않는 경우 조합탐색.
        
        // - 쏘지 않는 경우
        simul(idx+1, rest, m);
        
        // - 쏘는 경우
        int needShot=Math.max(0, y[idx])+1;
        int shot=Math.min(rest, needShot);
        m[idx]+=shot;
        simul(idx+1, rest-shot, m);
        m[idx]-=shot;
    }

    // 정렬.
    List<Integer> getRank() {
        List<Integer> rank=new ArrayList<>();
        for(int i=0; i<wins.size(); i++) rank.add(i);
        
        rank.sort((a, b) -> {
            int ascore=score.get(a);
            int bscore=score.get(b);
            if(ascore!=bscore) return bscore-ascore;
            
            int idx=10;
            int[] ashot=wins.get(a);
            int[] bshot=wins.get(b);
            int ret=0;
            while(idx>=0) {
                if(ashot[idx]==bshot[idx]) idx--;
                else if(ashot[idx]>bshot[idx]) {
                    ret=-1;
                    break;
                } else {
                    ret=1;
                    break;
                }
            }
            return ret;
        });
        return rank;
    }
    
}