class Solution {
    final int MAX=(int)1e9;
    String target;
    public int solution(String begin, String target, String[] words) {
        this.target=target;
        int ret=find(begin, 0, words);
        return ret==MAX ? 0 : ret;
    }
    
    int find(String cur, long vis, String[] words) {
        if(cur.equals(target)) return 0;
        if(Long.bitCount(vis)==words.length) return MAX;
        int ret=MAX;
        for(int i=0; i<words.length; i++) {
            if((vis&1<<i)!=0) continue;
            String w=words[i];
            int ci=0;
            int wi=0;
            int diff=0;
            while(ci<cur.length() && wi<w.length()) {
                if(cur.charAt(ci)!=w.charAt(wi)) diff++;
                if(diff>1) break;
                ci++; wi++;
            }
            if(diff==1) ret=Math.min(ret, find(w, vis|1<<i, words)+1);
        }
        return ret;
    }
}