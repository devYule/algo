import java.util.*;
import java.util.stream.*;
class Solution {
    
    public int solution(int n, int k) {
        String st=toK(n, k);
        String[] candi=st.split("0");
        List<Long> nums=Arrays.stream(candi).filter(s->s!=null&&!s.isEmpty()).map(Long::parseLong).collect(Collectors.toList());
        nums.sort((a, b) -> Long.compare(b, a));
        int ret=0;
        for(long num: nums) {
            if(!isPrime(num)) continue;
            ret++;
        }
        
        return ret;
    }
    
    boolean isPrime(long num) {
        if(num<=1) return false;
        int sqrt=(int) Math.sqrt(num);
        for(int i=2; i<=sqrt; i++) {
            if(num%i==0) return false;
        }
        return true;
    }
    
    
    String toK(int num, int k) {
        List<Integer> nums=new ArrayList<>();
        while(true) {
            if(num<k) {
                nums.add(num);
                break;
            }
            nums.add(num%k);
            num/=k;
        }
        String ret="";
        for(int i=nums.size()-1; i>=0; i--) {
            ret+=nums.get(i);
        }
        return ret;
    }
}