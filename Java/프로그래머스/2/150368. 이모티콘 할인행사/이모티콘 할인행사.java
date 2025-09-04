import java.util.*;

class Solution {
    // 플러스 가입자가 최대이면서, 수익이 최대인 경우의 
    // {가입자, 수익}
    
    // 할인율: 10, 20, 30, 40 (%)
    // 사용자: users[0] 의 할인율 이상인 이모티콘을 구매함.
    //        users[1] 의 금액보다 구매하려는 이모티콘의 가격이 높으면 플러스를 가입함.
    // 각 이모티콘의 할인율은 서로 다를 수 있음.
    
    final int[] P={10, 20, 30, 40};
    int n,m;
    int[] emo;
    int[][] users;
    public int[] solution(int[][] users, int[] emoticons) {
        this.n=users.length;
        this.m=emoticons.length;
        this.emo=emoticons;
        this.users=users;
        // 모든 할인율을 적용한 이모티콘 가격 경우의 수: 4^7=16,384
        // 모든 경우의 수 마다 각 user의 이모티콘 최종 구매 금액 구하기 (여기서 기준수에 따라 플러스 가입여부가 결정.)
        // O(n4^m)=1,638,400
        
        // {{prices}, {percent}}
        List<List<int[]>> price=new ArrayList<>();
        getPrice(0, new ArrayList<>(), price);
        
        return getBest(price);
    }
    
    int[] getBest(List<List<int[]>> price) {
        int[] ret=new int[2];
        for(List<int[]> pr: price) {
            // 유저별 이모티콘 총 구매금액.
            int[] pay=new int[n];
            // 각 이모티콘의 [가격][할인율]
            for(int[] p: pr) {
                for(int i=0; i<n; i++) {
                    int[] u=users[i];
                    if(p[1]<u[0]) continue;
                    pay[i]+=p[0];
                }
            }
            
            int plus=0;
            int emo=0;
            for(int i=0; i<n; i++) {
                if(users[i][1]<=pay[i]) {
                    plus++;
                } else {
                    emo+=pay[i];
                }
            }
            if(ret[0]<plus || ret[0]==plus && ret[1]<emo) {
                ret[0]=plus;
                ret[1]=emo;
            }
        }
        return ret;
    }
    
    void getPrice(int idx, List<int[]> holder, List<List<int[]>> ret) {
        if(idx==m) {
            ret.add(new ArrayList<>(holder));
            return;
        }
        
        for(int i=0; i<4; i++) {
            int p=getPer(emo[idx], P[i]);
            holder.add(new int[] {p, P[i]});
            getPrice(idx+1, holder, ret);
            holder.remove(holder.size()-1);
        }
    }
    
    // price 에서 per만큼 할인된 금액 구하기
    int getPer(int price, int per) {
        return price-(int)(price*(per/100.0));
    }
    
}