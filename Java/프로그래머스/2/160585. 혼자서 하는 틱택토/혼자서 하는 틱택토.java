class Solution {
    /*
        잘못된 게임판:
            1. 가로,세로 기준 O가 연속 3개이면서, X도 연속 3개일 경우.
                (어느 하나라도 대각으로 3개라면, 이런 일이 발생할 수 없다.)
            2. X의 개수가 O의 개수보다 2개 이상 적거나, 1개이상 많을 경우.
                (O의 개수는 언제나 X보다 하나 많거나, 같아야 한다.)
            3. O가 승리했는데, O-X 가 1개가 아닌경우 || X가 승리했는데, O-X 가 0이 아닌 경우.
                (둘 모두 승리한 경우까지 포함됨.)
            4. 특정 말이 승리했을 때, 승리횟수가 2초과인 경우.
                (오목의 33처럼 하나를 놓아 두줄이 3으로 완성되는 경우도 존재할 수 있다.)
    */
    
    int[] rg = {1, 0, 1, 1};
    int[] cg = {0, 1, 1, -1};
    
    String[] board;
    
    public int solution(String[] board) {
        this.board = board;
                
        return resolve();
    }

    int resolve() {
        int[] count = new int[2];
        int[] wins = new int[2];
        
        for(int i=0; i<3; i++) {
            for(int j=0; j<3; j++) {
                int type = type(i, j);
                if(type==-1) continue;
                count[type]++;
                
                for(int k=0; k<4; k++) {
                    int cont = 0;
                    int y=i, x=j;
                    while(valid(y, x) && type(y,x) == type) {
                        cont++;
                        y+=rg[k];
                        x+=cg[k];
                    }
                    if(cont==3) wins[type]++;
                }
            }
        }
        
        int gap = count[0]-count[1];
        return wins[0]>2 || 
            wins[1]>2 || 
            (wins[0]==1 && gap!=1) ||
            (wins[1]==1 && gap!=0) ||
            (gap!=0 && gap!=1) ? 0 : 1;
    }
    
    boolean valid(int y, int x) {
        return y>=0 && x>=0 && y<3 && x<3;
    }
    
    int type(int y, int x) {
        return board[y].charAt(x) == '.' ? -1 : board[y].charAt(x) =='O' ? 0 : 1;
    }
}