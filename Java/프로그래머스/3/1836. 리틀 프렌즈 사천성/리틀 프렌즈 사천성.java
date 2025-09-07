import java.util.*;
class Solution {
    /*
        D B A
        C * A
        C D B
    */
    final String IMP="IMPOSSIBLE";
    int[][][] pair;
    char[][] map;
    List<Character> alpha;
    public String solution(int m, int n, String[] board) {

        this.pair=new int[26][2][];
        this.map=new char[board.length][board[0].length()];
        this.alpha=new ArrayList<>();
        for(int i=0; i<board.length; i++) {
            for(int j=0; j<board[i].length(); j++) {
                map[i][j]=board[i].charAt(j);
                char c=map[i][j];
                if(c<'A' || c>'Z') continue;
                if(pair[c-'A'][0]==null) { pair[c-'A'][0]=new int[] {i, j}; alpha.add(c); }
                else pair[c-'A'][1]=new int[] {i, j};
            }
        }
        String ret="";
        
        alpha.sort(Comparator.reverseOrder());
        while(true) {
            boolean changed=false;
            for(int i=alpha.size()-1; i>=0; i--) {
                char c=alpha.get(i);
                if(pop(c)) {
                    ret+=c;
                    alpha.remove(i);
                    changed=true;
                    break;
                }
            }
            if(alpha.isEmpty()) break;
            if(!changed) break;
        }
        return alpha.isEmpty() ? ret : IMP;
    }
    
    boolean pop(char c) {
        int[] upper=pair[c-'A'][0];
        int[] lower=pair[c-'A'][1];
        int y1=upper[0], x1=upper[1], y2=lower[0], x2=lower[1];
        int ydir=y1==y2 ? 0 : 1;
        int xdir=x1==x2 ? 0 : x1<x2 ? 1 : -1;
        
        int cury=y1, curx=x1;
        boolean flag=false;
        
        // 행 우선.
        while(cury!=y2) {
            cury+=ydir;
            if(map[cury][curx]!='.' && map[cury][curx]!=c) {
                cury-=ydir;
                break;
            }
        }
        
        while(cury==y2 && curx!=x2) {
            curx+=xdir;
            if(map[cury][curx]!='.' && map[cury][curx]!=c) {
                curx-=xdir;
                break;
            }
        }
        
        flag=cury==y2 && curx==x2;
        if(flag) { 
            map[y1][x1]='.'; map[y2][x2]='.'; 
            return true;
        }
        
        // 열 이동
        cury=y1; curx=x1; flag= false;
        while(curx!=x2) {
            curx+=xdir;
            if(map[cury][curx]!='.' && map[cury][curx]!=c) {
                curx-=xdir;
                break;
            }
        }
        while(curx==x2 && cury!=y2) {
            cury+=ydir;
            if(map[cury][curx]!='.' && map[cury][curx]!=c) {
                cury-=ydir;
                break;
            }
        }
        
        flag=cury==y2 && curx==x2;
        
        if(flag) { map[y1][x1]='.'; map[y2][x2]='.'; }

        return flag;
    }
}