import java.util.*;
class Solution {
    
    public int solution(int[] queue1, int[] queue2) {
        int n=queue1.length;
        int m=queue2.length;
        int tn=n+m;
        int[] merge=new int[tn];
        int mi=0;
        for(int i=0; i<n; i++) merge[mi++]=queue1[i];
        for(int i=0; i<m; i++) merge[mi++]=queue2[i];
        
        long lsum=Arrays.stream(queue1).sum();
        long half=(lsum+Arrays.stream(queue2).sum());
        if(half%2!=0) return -1;
        half/=2;
        
        int cnt=0;
        int lo=0;
        int hi=n;
        while(hi<tn && lsum!=half) {
            while(hi<tn&&lsum<half) {
                lsum+=merge[hi++];
                cnt++;
            }
            while(lo<hi&&lsum>half) {
                lsum-=merge[lo++];
                cnt++;
            }
        }
        return lsum==half ? cnt : -1;
    }
}