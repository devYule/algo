class Solution {
    public int solution(String s) {
        int ret=1;
        for(int i=0; i<s.length(); i++) {
            int lo=i-1;
            int hi=i+1;
            int cnt=0;
            while(lo>=0 && hi<s.length()) {
                if(s.charAt(lo)!=s.charAt(hi)) break;
                cnt++; lo--; hi++;
            }
            ret=Math.max(ret, cnt*2+1);
            
            lo=i-1; hi=i; cnt=0;
            while(lo>=0 && hi<s.length()) {
                if(s.charAt(lo)!=s.charAt(hi)) break;
                cnt++; lo--; hi++;
            }
            ret=Math.max(ret, cnt*2);
        }
        return ret;
    }
}