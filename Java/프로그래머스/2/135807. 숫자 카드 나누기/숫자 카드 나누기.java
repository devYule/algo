import java.util.*;
class Solution {
    public int solution(int[] arrayA, int[] arrayB) {
        int n=Arrays.stream(arrayA).max().orElse(0);
        n=Math.max(n, Arrays.stream(arrayB).max().orElse(0));
        int[] prime=new int[n+1];
        for(int i=0; i<prime.length; i++) prime[i]=i;
        setPrime(prime);
        
        List<Integer> advs=getCommonDivs(arrayA, prime);
        List<Integer> bdvs=getCommonDivs(arrayB, prime);
        advs.sort((a, b) -> b-a);
        bdvs.sort((a, b) -> b-a);
        int ret=0;
        for(int d: advs) {
            boolean flag=true;
            for(int i=0; i<arrayB.length; i++) {
                if(arrayB[i]%d==0) {
                    flag=false;
                    break;
                }
            }
            if(flag) {
                ret=d;
                break;
            }
        }
        
        for(int d: bdvs) {
            boolean flag=true;
            for(int i=0; i<arrayA.length; i++) {
                if(arrayA[i]%d==0) {
                    flag=false;
                    break;
                }
            }
            if(flag) {
                ret=Math.max(ret, d);
                break;
            }
        }
        
        return ret;
    }
    
    // numb 의 공약수 모두 구하기.
    List<Integer> getCommonDivs(int[] numb, int[] prime) {
        // numb 정렬하기
        Arrays.sort(numb);
        // 가장 작은 수, numb[0] 의 약수들 구하기 (1제외)
        int base=numb[0];
        List<Integer> baseDivs=new ArrayList<>();
        baseDivs.add(1);
        getDivs(base, prime, baseDivs);
        
        // 해당 약수로 나누어 떨어지지 않는 숫자는 제외.
        List<Integer> ret=new ArrayList<>();
        for(int dvs: baseDivs) {
            if(dvs==1) continue;
            boolean flag=true;
            for(int i=1; i<numb.length; i++) {
                int target=numb[i];
                if(target%dvs!=0) {
                    flag=false;
                    break;
                }
            }
            if(flag) ret.add(dvs);
        }
        
        return ret;
    }
    
    // 모든 약수 구하기
    void getDivs(int cur, int[] prime, List<Integer> baseDivs) {
        if(cur==1) return;
        int n=cur;
        int p=prime[n];
        int e=0;

        while(n%p==0) {
            e++;
            n/=p;
        }
        
        getDivs(n, prime, baseDivs);
        
        int cnt=baseDivs.size();
        for(int i=1; i<=e; i++) {
            int curNum=(int) Math.pow(p, i);
            for(int j=0; j<cnt; j++) {
                baseDivs.add(curNum*baseDivs.get(j));
            }
        }
    }
    
    void setPrime(int[] prime) {
        int sqrt=(int) Math.sqrt(prime.length);
        for(int i=2; i<=sqrt; i++) {
            if(prime[i]!=i) continue;
            for(int j=i*i; j<prime.length; j+=i) {
                if(prime[j]!=j) continue;
                prime[j]=i;
            }
        }
    }
    
}