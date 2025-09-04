import java.util.*;
class Solution {
    /*
        부분수열 만들기
        해당 부분 수열이 다음의 특징을 가짐.
            1. 길이가 짝수
            2. 2개씩 짝지었을 때 서로 다르며, 각 그룹들에는 1개 이상의 공통원소 존재.
    */
    // 목표: 위 조건을 만족하는 가장 긴 수열의 길이.
    public int solution(int[] a) {
        // 연속되지 않은 숫자들의 개수 카운팅.
        int[] cnt=new int[a.length];
        List<Integer> desc=new ArrayList<>();
        cnt[a[0]]++;
        for(int i=0; i<a.length; i++) {
            desc.add(i);
            if(i>0 && a[i]!=a[i-1]) {
                cnt[a[i]]++;
            }
        }
        desc.sort((v1, v2) -> cnt[v2]-cnt[v1]);
        int best=desc.get(0);
        int ret=0;
        for(int i=0; i<desc.size(); i++) {
            if(desc.get(i)<best/2) break;
            ret=Math.max(ret, find(desc.get(i), a));
        }
        return ret;
    }
    
    int find(int target, int[] a) {
        int ret=0;
        int va=-1;
        for(int cur: a) {
            if(va==-1) va=cur;
            else {
                if(va==cur || (va!=target && cur!=target)) continue;
                ret+=2;
                va=-1;
            }
        }
        return ret;
    }
}
