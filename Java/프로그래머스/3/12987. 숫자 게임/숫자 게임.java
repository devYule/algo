import java.util.*;
class Solution {
    /*
        숫자가 크면 승리. (승점 1)
        A: A팀이 부여받은 숫자 (출전 순서대로 정렬.)
        B: 정렬되지 않은 부여받은 숫자.
    */
    // return: B팀을 적절히 정렬해서 얻을 수 있는 최대 승점
    public int solution(int[] A, int[] B) {
        PriorityQueue<Integer> aq=new PriorityQueue(Comparator.reverseOrder());
        PriorityQueue<Integer> bq=new PriorityQueue(Comparator.reverseOrder());
        for(int i=0; i<A.length; i++) { aq.add(A[i]); bq.add(B[i]); }
        int ret=0;
        while(!aq.isEmpty() && !bq.isEmpty()) {
            if(aq.peek()<bq.peek()) { ret++; bq.poll(); }
            aq.poll();
        }
        return ret;
    }
}