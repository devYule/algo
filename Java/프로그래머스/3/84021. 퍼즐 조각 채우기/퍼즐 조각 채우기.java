import java.util.*;
class Solution {
    // 회전 가능.
    // 게임판은 50이하.
    
    // 블록이 완벽하게 들어맞아야함.
    
    int[] rg={-1, 0, 1, 0};
    int[] cg={0, 1, 0, -1};
    
    // [i]: i개로 이루어진 블록.
    // 원소: 시작점부터 각 블록으로 이동하는 상대적 좌표.
    int n;
    int[][] map, table;
    List<List<int[]>>[] puzz;
    
    @SuppressWarnings("unchecked")
    public int solution(int[][] game_board, int[][] table) {
        int ret=0;
        this.n=game_board.length;
        this.map=game_board;
        this.table=table;
        this.puzz=new ArrayList[7];
        
        setId(this.table);
        
        for(int r=0; r<4; r++) {
            boolean[][] visited=new boolean[n][n];
            for(int i=0; i<n; i++) {
                for(int j=0; j<n; j++) {
                    if(this.table[i][j]==0 || visited[i][j]) continue;
                    List<int[]> holder=new ArrayList<>();
                    save(this.table, holder, i, j, visited, 0, this.table[i][j]);
                    int size=holder.size();
                    if(size>6) continue;
                    if(puzz[size]==null) puzz[size]=new ArrayList<>();
                    puzz[size].add(holder);
                    holder.sort((a, b) -> a[0]==b[0] ? a[1]-b[1] : a[0]-b[0]);
                }
            }
            this.table=rotate(this.table);
        }
        
        List<List<int[]>>[] zero=new ArrayList[7];
        
        boolean[][] visited=new boolean[n][n];
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                if(map[i][j]==1 || visited[i][j]) continue;
                List<int[]> holder=new ArrayList<>();
                save(map, holder, i, j, visited, 1, 0);
                int size=holder.size();
                if(zero[size]==null) zero[size]=new ArrayList<>();
                zero[size].add(holder);
                holder.sort((a, b) -> a[0]==b[0] ? a[1]-b[1] : a[0]-b[0]);
            }
        }
        
        for(int i=0; i<zero.length; i++) {
            if(zero[i]==null) continue;
            List<List<int[]>> empty=zero[i];
            for(int j=0; j<empty.size(); j++) {
                List<int[]> all=empty.get(j);
                if(puzz[i]==null) break;
                int rmid=-1;
                for(int k=0; k<puzz[i].size(); k++) {
                    List<int[]> candi=puzz[i].get(k);
                    boolean flag=true;
                    for(int l=0; l<all.size(); l++) {
                        if(all.get(l)[0] != candi.get(l)[0] || all.get(l)[1] != candi.get(l)[1]) {
                            flag=false;
                            break;
                        }
                    }
                    if(flag) {
                        rmid=candi.get(0)[2];
                        for(int rm=puzz[i].size()-1; rm>=0; rm--) {
                            if(puzz[i].get(rm).get(0)[2]==rmid) puzz[i].remove(rm);
                        }
                        break;
                    }
                }
                if(rmid!=-1) {
                    ret+=i;
                }
            }
        }
        
        return ret;
    }
    
    
    void setId(int[][] table) {
        int id=1;
        boolean[][] visited=new boolean[n][n];
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                if(table[i][j]==1 && !visited[i][j]) set(id++, i, j, visited);
            }
        }
    }
    
    void set(int id, int y, int x, boolean[][] visited) {
        visited[y][x]=true;
        this.table[y][x]=id;
        for(int i=0; i<4; i++) {
            int ny=y+rg[i];
            int nx=x+cg[i];
            if(!valid(ny, nx) || visited[ny][nx] || table[ny][nx]!=1) continue;
            set(id, ny, nx, visited);
        }
    }
    
    
    // table 전체 회전.
    int[][] rotate(int[][] orin) {
        int[][] ret=new int[n][n];
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                ret[j][n-i-1]=orin[i][j];
            }
        }
        return ret;
    }
    
    
    void save(int[][] base, List<int[]> holder, int y, int x, boolean[][] visited, int falFlag, int id) {
        Queue<int[]> q=new LinkedList<>();
        q.add(new int[] {y, x});
        visited[y][x]=true;
        
        while(!q.isEmpty()) {
            int[] curs=q.poll();
            int cury=curs[0];
            int curx=curs[1];
            if(id==0) holder.add(new int[] {cury-y, curx-x});
            else holder.add(new int[] {cury-y, curx-x, id});
            
            for(int i=0; i<4; i++) {
                int ny=cury+rg[i];
                int nx=curx+cg[i];
                if(!valid(ny, nx) || base[ny][nx]==falFlag || visited[ny][nx]) continue;
                q.add(new int[] {ny, nx});
                visited[ny][nx]=true;
            }
        }
    }
    
    boolean valid(int y, int x) {
        return y>=0 && x>=0 && y<n && x<n;
    }
}