import java.util.*;
class Solution {
    final int MAX=(int)1e9;
    List<int[]> canTry;
    int[][] P;
    boolean[] hasSolved;
    int maxalgo;
    int maxcod;
    int ret;
    int[][] memo;
    
    int amax, cmax;
    public int solution(int alp, int cop, int[][] problems) {
        this.P=problems;
        this.hasSolved=new boolean[P.length];
        
        for(int[] p: P) {
            int algo=p[0];
            int cod=p[1];
            maxalgo=Math.max(maxalgo, algo);
            maxcod=Math.max(maxcod, cod);
        }
        this.memo=new int[152][152];
        for(int i=0; i<memo.length; i++) Arrays.fill(memo[i], -1);
        
        this.canTry=new ArrayList<>();
        canTry.add(new int[] {1, 0, 1});
        canTry.add(new int[] {0, 1, 1});
        
        return solve(alp, cop);
    }
    
    // a 를 maxalgo 이상, c를 maxcod 이상으로 만드는 최소 문제 해결 시간.
    int solve(int a, int c) {
        if(a>=maxalgo && c>=maxcod) return 0;
        if(memo[a][c]!=-1) return memo[a][c];
        
        // 갱신된 점수로 새롭게 풀 수 있는 문제가 생겼는지 확인.
        List<Integer> back=new ArrayList<>();
        int prevSize=canTry.size();
        for(int i=0; i<P.length; i++) {
            if(hasSolved[i]) continue;
            int ndAlgo=P[i][0];
            int ndCode=P[i][1];
            
            if(a>=ndAlgo&&c>=ndCode) {
                hasSolved[i]=true;
                back.add(i);
                canTry.add(new int[] {P[i][2], P[i][3], P[i][4]});
            }
        }
        int afterSize=canTry.size();
        
        
        int ret=MAX;
        // 풀 수 있는 모든 문제를 한번씩 푸는 경우 시뮬레이션.
        for(int i=0; i<canTry.size(); i++) {
            int[] can=canTry.get(i);
            int addAlgo=can[0];
            int addCode=can[1];
            int dist=can[2];
            
            int nexta=a+addAlgo;
            int nextc=c+addCode;
            if(nexta>151) nexta=151;
            if(nextc>151) nextc=151;
            
            if(nexta==a && nextc==c) continue;
            
            ret=Math.min(ret, solve(nexta, nextc)+dist);
        }
        
        for(int i=afterSize-1; i>=prevSize; i--) {
            canTry.remove(i);
        }
        for(int b: back) {
            hasSolved[b]=false;
        }
        return memo[a][c]=ret;
    }
}