import java.util.*;
class Solution {
    
    /* 제약:
        0 <= coin <= n
        6 <= n(카드총수) < 1000 (6의 배수), 카드중복 X
        
    */
    
    /*
        1. 카드 n/3 장을 뽑음. 코인은 coin개 있음.
        2. 라운드:
            - 카드 2장을 덱에서 뽑음. (카드가 모자라면 종료.)
            - 2장중 원하면 코인과 1:1 교환으로 최종 획득.
            - 손에쥔 카드중 2장의 합이 n+1 이 되는 카드가 없으면 게임 종료.
    */

    
    int[][] C;
    int[] hand;
    int[] passed;
    int N, M;
    boolean flag;
    int maxRound;
    public int solution(int coin, int[] cards) {

        this.flag = true;
        N = cards.length;
        M = N/3;
        this.C = new int[(N - M) / 2][2];
        this.hand = new int[N + 1];
        this.passed = new int[N + 1];
        this.maxRound = C.length + 1;
        
        for(int i = 0; i < M; i++) hand[cards[i]] = 1;
        
        int ci = 0;
        for(int i = M; i < N; i+=2) {
            C[ci][0] = cards[i];
            C[ci++][1] = cards[i + 1];
        }
        
        return game(coin, 0);
        
    }
    
    int game(int coin, int round) {
        if(round == maxRound) return 0;
        if(round < C.length) {
            int[] c = C[round];
            passed[c[0]] = 1;
            passed[c[1]] = 1;
        }
        if(flag) {
            for(int i = 1; i <= hand.length / 2; i++) {
                if(hand[i] == 1 && hand[N+1-i] == 1) {
                    hand[i] = 0;
                    hand[N+1-i] = 0;
                    return game(coin, round+1) + 1;
                }
            }
        }
        if(flag) flag = false;
        
        if(coin >= 1) {
            for(int i = 1; i < passed.length; i++) {
                if(passed[i] == 1 && hand[N+1-i] == 1) {
                    passed[i] = 0;
                    hand[N+1-i] = 0;
                    return game(coin-1, round+1) + 1;
                }
            }
        }
        
        if(coin >= 2) {
            for(int i = 1; i <= passed.length / 2; i++) {
                if(passed[i] == 1 && passed[N+1-i] == 1) {
                    passed[i] = 0;
                    passed[N+1-i] = 0;
                    return game(coin-2, round+1) + 1;
                }
            }
        }
        
        return 1;
    }
}