import java.util.*;
class Solution {
    int[] location;
    public int[][] solution(int n) {
        // n번의 현재 위치.
        // 1~n
        location=new int[n+1];
        
        List<int[]> ret=new ArrayList<>();
        simul(n, false, ret);
        
        return ret.stream().toArray(int[][]::new);
    }
    
    void simul(int n, boolean isRight, List<int[]> ret) {
        if(n==0) return;
        // 포화 이진트리의 중위순회 - 왼쪽
        simul(n-1, !isRight, ret);
        // 포화 이진트리의 중위순회 - 자신
        int next=move(location[n], isRight, ret);
        // n의 현재 위치 갱신.
        location[n]=next;
        // 포화 이진트리의 중위순회 - 오른쪽
        simul(n-1, !isRight, ret);
    }
    
    int move(int cur, boolean isRight, List<int[]> ret) {
        int[] fromTo=new int[2];
        fromTo[0]=cur+1;
        if(isRight) cur++;
        else cur--;
        if(cur==3) cur=0;
        if(cur==-1) cur=2;
        fromTo[1]=cur+1;
        ret.add(fromTo);
        return cur;
    }
}