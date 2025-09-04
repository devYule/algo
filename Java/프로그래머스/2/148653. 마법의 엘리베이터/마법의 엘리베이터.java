class Solution {
    public int solution(int storey) {
        int ret=0;
        while(storey>0) {
            int rest=storey%10;
            storey/=10;
            int nrest=storey%10;
            if(10-rest<rest || (rest==5 && nrest>=5)) {
                ret+=10-rest;
                storey+=1;
            } else ret+=rest;
        }
        return ret;
    }
    
}