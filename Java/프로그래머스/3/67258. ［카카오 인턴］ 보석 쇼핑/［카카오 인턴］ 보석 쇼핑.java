import java.util.*;
class Solution {
    // 연속구간
    // 구간 내 모든 종류를 1개이상 포함하는 가장 짧은 구간.
    // 짧은 구간이 같다면 시작 인덱스가 더 빠른것 반환.
    // returns: [시작인덱스+1, 끝 인덱스+1]
    public int[] solution(String[] gems) {
        int n=gems.length;
        Map<String, Integer> selected=new HashMap<>();
        int cnt=0;
        for(String g: gems) {
            if(selected.get(g)==null) {
                cnt++;
                selected.put(g, 1);
            }
        }
        selected=new HashMap<>();
        int[] ret=new int[2];
        int bestSel=(int)1e9;
        int left=0; int right=0; int sel=0;
        while(left<n || right<n) {
            while(right<n && sel<cnt) {
                if(selected.get(gems[right])==null) {
                    selected.put(gems[right], 1);
                    sel++;
                } else selected.put(gems[right], selected.get(gems[right])+1);
                right++;
            }
            if(right==n && sel<cnt) break;
            int len=right-left;
            if(len<bestSel) {
                bestSel=len;
                ret[0]=left+1;
                ret[1]=right;
            }
            int del=selected.get(gems[left]);
            if(del==1) {
                selected.put(gems[left], null);
                sel--;
            } else selected.put(gems[left], selected.get(gems[left])-1);
            left++;
        }
        return ret;
    }
}