import java.util.*;
import java.util.regex.*;
class Solution {
    /*
        기본점수: 내용에 검색어가 등장하는 횟수 (대소문자 무시)
        외부 링크 수: 외부 페이지로 연결된 링크의 개수
        링크점수: 자신으로의 링크가 달린 페이지들의 기본점수/외부 링크 수의 합.
        매칭점수: 기본점수+링크점수
    */
    /*
        내 URL: <head>의 <meta content=..> 의 값.
        외부 링크: <body> 의 <a href=..> 의 값.
        내용: body내 textContents.
    */
    // 매칭점수가 가장 "높은" 웹페이지의 index. (여러개라면 번호가 가장 작은것.)
    public int solution(String word, String[] pages) {
        Map<String, Double> trans=new HashMap<>();
        Info[] infos=new Info[pages.length];
        double[] score=new double[pages.length];
        
        for(int i=0; i<pages.length; i++) {
            String html=pages[i];
            Info info=find(html);
            infos[i]=info;
            
            int basicCnt=0;
            String cont=info.contents;
            for(String c: cont.split("[^a-zA-Z]+")) {
                if(word.equalsIgnoreCase(c)) basicCnt++;
            }
            
            score[i]=basicCnt;
        
            // 점수 노티.
            for(String l: info.link) {
                trans.computeIfAbsent(l, k->0d);
                trans.put(l, trans.get(l)+((double) basicCnt/info.link.size()));
            }
        }
        
        double maxScore=-1;
        int maxi=-1;
        // 점수 획득 및 최대점수 획득.
        for(int i=0; i<infos.length; i++) {
            Double s=trans.get(infos[i].murl);
            if(s!=null) score[i]+=s;
            trans.put(infos[i].murl, null);
            if(maxScore<score[i]) {
                maxScore=score[i];
                maxi=i;
            }
        }
        
        return maxi;
    }
    
    Info find(String o) {
        
        Matcher murl=Pattern.compile(Regex.myurl).matcher(o);
        Matcher mbody=Pattern.compile(Regex.body).matcher(o);
        String url=murl.find() ? murl.group() : "";
        String body=mbody.find() ? mbody.group() : "";
        Matcher mlink=Pattern.compile(Regex.link).matcher(body);
        
        Info ret=new Info();
        ret.murl=url;
        while(mlink.find()) {
            ret.link.add(mlink.group());
        }
        ret.contents=body.replaceAll(Regex.rmlink, " ");
        return ret;
    }
    
    static class Info {
        String murl;
        List<String> link=new ArrayList<>();
        String contents;
    }
    
    interface Regex {
        static final String myurl="(?si)(?<=<meta property=\"og:url\" content=\").*?(?=\")";
        static final String body="(?si)(?<=<body>).*?(?=</body>)";
        static final String link="(?si)(?<=<a href=\").*?(?=\")";
        static final String rmlink="(?si)<a.*?</a>";
    }
}