import java.util.*;
class Solution {
    public int solution(int[] elements) {
        int n=elements.length;
        int[] extended=new int[n*2];
        int cntMax=Arrays.stream(elements).sum();
        int[] cnt=new int[cntMax+1];
        for(int i=0; i<n; i++) extended[n+i]=extended[i]=elements[i];
        
        int ret=0;
        
        for(int i=0; i<n; i++) {
            if(cnt[extended[i]]==0) ret++;
            cnt[extended[i]]++;
            int acc=extended[i];
            for(int j=1; j<n; j++) {
                acc+=extended[i+j];
                if(cnt[acc]==0) ret++;
                cnt[acc]++;
            }
        }
        return ret;
    }
}