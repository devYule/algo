import java.util.*;
class Solution {
    public int[] solution(String[] operations) {
        TreeMap<Integer, Integer> tree=new TreeMap<>();
        for(String o: operations) {
            String[] split=o.split(" ");
            int numb=toNum(split[1]);
            if("I".equals(split[0])) {
                tree.put(numb, numb);
            } else if(numb==-1) {
                tree.pollFirstEntry();
            } else {
                tree.pollLastEntry();
            }
        }
        if(tree.isEmpty()) return new int[] {0, 0};
        return new int[] {tree.lastKey(), tree.firstKey()};
    }
    
    int toNum(String n) {
        return Integer.parseInt(n);
    }
}