import java.util.*;
class Solution {
    // 서버 현황
    int[] server;
    // i 시점에 추가된 서버 대수
    int[] added;
    
    int[] players;
    int n, m, k;
    public int solution(int[] players, int m, int k) {
        this.n = players.length;
		this.m = m;
        this.k = k;
        this.players = players;
        this.server = new int[n];
        this.added = new int[n];
        Arrays.fill(added, -1);
		server[0] = 1;
        int ret = server(0);
        return ret;
    }
    
	int server(int idx) {
        if(idx < 0) return 0;
        if(idx == n) return 0;
        if(added[idx] != -1) return added[idx];
        
        // k 번째 전에 추가된 서버 대수 구하기.
        int dim = server(idx - k);
        // 현재 유효 서버 대수
        server[idx] = (idx == 0 ? 0 : server[idx - 1]) - dim;
        
        added[idx] = 0;
        // 서버 추가하기
        int need = players[idx] / m;
        if(need > server[idx]) {
            added[idx] = need - server[idx];
            server[idx] = need;
        }
        
        // 다음호출
        int afterAdded = server(idx + 1);
        
        return added[idx] + afterAdded;
    }
}