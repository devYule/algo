import java.util.*;
class Solution {
    /*
        하위 노드는 상위 노드에게 10%, 자신은 90% 를 가짐 (분배금)
        상위 노드는 자신의 수익과 분배금 모두 10% 만큼 상위에 전달해야함.
        원단위 절삭.
        
        amount*100=이익금 100%
    */
    
    /*
        각 자식에게 수금 -> 10%씩의 이익금 누적.
        수금의 90%를 자신의 수익에 기록.
        수익의 90%를 자신의 수익에 추가.
        수금의 10%+수익의10% 를 반환.
        **10%가 1원 미만일 시 0으로 치부.
    */
    
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        Map<String, Integer> idx=new HashMap<>();
        for(int i=0; i<enroll.length; i++) idx.put(enroll[i], i);
        Map<String, String> parent=new HashMap<>();
        
        for(int i=0; i<referral.length; i++) {
            String cur=enroll[i];
            String par=referral[i];
            parent.put(cur, par);
        }
        
        int[] ret=new int[enroll.length];
        for(int i=0; i<seller.length; i++) {
            String sel=seller[i];
            int amt=amount[i]*100;
            
            while(true) {
                String par=parent.get(sel);
                if(par==null) break;
                int amt10=amt/10;
                int amt90=amt-amt10;
                int isel=idx.get(sel);
                ret[isel]+=amt90;
                amt=amt10;
                sel=par;
                if(amt10==0 || "-".equals(par)) break;
            }
        }
        return ret;
    }
    
}