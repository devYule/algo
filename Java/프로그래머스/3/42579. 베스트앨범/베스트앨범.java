import java.util.*;
class Solution {
    /*
        장르별 노래 2개씩 앨범에.
        
        순위:
            1. 가장 많이 재생된 장르.
            2. 장르 내에서 많이 재생된 노래.
            3. 고유번호가 낮은 노래.
    */
    // 앨범에 들어갈 노래의 우선순위 순서대로 고유번호 returns; (장르당 2곡, 1곡밖에 없으면 1곡만.)
    
    Map<String, Type> map;
    public int[] solution(String[] genres, int[] plays) {
        int n=genres.length;
        this.map=new HashMap<>();
        for(int i=0; i<n; i++) {
            Type t=map.computeIfAbsent(genres[i], _$->new Type());
            t.tot+=plays[i];
            t.music.add(new int[] {i, plays[i]});
        }
        
        return map.values()
            .stream()
            .sorted((a, b)->b.tot-a.tot)
            .flatMap(t->t.music.stream()
                     .sorted((a, b)->b[1]-a[1])
                     .limit(2)
                     .map(it->it[0]))
            .mapToInt(a->a).toArray();
    }
    
    static class Type {
        int tot;
        List<int[]> music=new ArrayList<>();
    }
}