import java.util.*;
class Solution {
    public int[] solution(int e, int[] starts) {
        // s[i]~e 사이에서 가장 많은 약수를 가진 수 중 가장 작은수.
        int[] ret=new int[starts.length];
        int[] mins=new int[e+1];
        // 각 숫자의 소인수 개수 저장.
        int[] minCnt=new int[e+1];
        
        for(int i=1; i<=e; i++) mins[i]=i;
        // 나누어지는 가장 작은수 구하기.
        eratos(mins);
        getMinCnt(mins, minCnt);
        minCnt[1]=1;
        minCnt[0]=0;
        
        Segment seg=new Segment(minCnt);
        
        for(int i=0; i<starts.length; i++) {
            int s=starts[i];
            ret[i]=seg.query(s, e);
        }
        return ret;
    }
    
    // 각 숫자의 소인수 개수 저장하기.
    void getMinCnt(int[] mins, int[] minCnt) {
        Arrays.fill(minCnt, -1);
        for(int i=minCnt.length-1; i>=2; i--) {
            if(minCnt[i]!=-1) continue;
            setMinCnt(i, mins, minCnt);
        }
    }
    
    int setMinCnt(int cur, int[] mins, int[] minCnt) {
        if(cur==1) return 1;
        if(minCnt[cur]!=-1) return minCnt[cur];
        int cnt=1;
        int div=mins[cur];
        int x=cur;
        while(x%div==0) {
            x/=div;
            cnt++;
        }
        int ret=setMinCnt(x, mins, minCnt);
        return minCnt[cur]=cnt*ret;
    }
    
    // nums: nums[i]의 약수중 가장 작은 수. (소수=자신)
    void eratos(int[] nums) {
        int sqrt=(int) Math.sqrt(nums.length);
        for(int i=2; i<=sqrt; i++) {
            if(nums[i]!=i) continue;
            for(int j=i*i; j<nums.length; j+=i) {
                if(nums[j]!=j) continue;
                nums[j]=i;
            }
        }
    }
    
    static class Segment {
        int[] o;
        int[] tree;
        int n;
        Segment(int[] o) {
            this.o=o;
            this.n=o.length;
            int ts=0;
            int pow=0;
            while(true) {
                ts+=Math.pow(2, pow);
                if(Math.pow(2, pow)>n) break;
                pow++;
            }
            this.tree=new int[ts];
            init(0, n-1, 1);
        }
        
        int init(int left, int right, int node) {
            if(left==right) return tree[node]=left;
            int mid=(left+right)>>>1;
            int lr=init(left, mid, node*2);
            int rr=init(mid+1, right, node*2+1);
            if(o[lr]==o[rr]) return tree[node]=Math.min(lr, rr);
            if(o[lr]>o[rr]) {
                return tree[node]=lr;
            }
            return tree[node]=rr;
        }
        
        int query(int left, int right) {
            return query(left, right, 0, n-1, 1);
        }
        
        int query(int left, int right, int nodeLeft, int nodeRight, int node) {
            if(left>nodeRight || right<nodeLeft) return -1;
            if(nodeLeft>=left && nodeRight<=right) return tree[node];
            int mid=(nodeLeft+nodeRight)>>>1;
            int lr=query(left, right, nodeLeft, mid, node*2);
            int rr=query(left, right, mid+1, nodeRight, node*2+1);
            if(lr==-1) return rr;
            if(rr==-1) return lr;
            if(o[lr]==o[rr]) return Math.min(lr, rr);
            if(o[lr]>o[rr]) return lr;
            return rr;
        }
    }
}