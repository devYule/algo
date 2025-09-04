import java.util.*;

public class Solution {
    public int[] solution(int []arr) {
        int n=arr.length;
        List<Integer> ret=new ArrayList<>();
        int last=-1;
        for(int num: arr) {
            if(last==num) continue;
            ret.add(num);
            last=num;
        }

        return ret.stream().mapToInt(a -> a).toArray();
    }
}