import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
           BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int n = Integer.parseInt(br.readLine());
            int[][] input = new int[n][3];
            for(int i=0; i<n; i++) {
                String line = br.readLine();
                StringTokenizer st = new StringTokenizer(line);
                for(int j=0; j<3; j++) {
                    input[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            
            int ret = new Main().resolve(n, input);
            bw.write(String.valueOf(ret));
        }
    }
    
    // [i] 번째 집을 선택하고, [j] 색을 칠했을 때, 이후 모든 집을 칠하는 최소값.
    int[][] memo;
    int N;
    
    int[][] cost;
    int resolve(int N, int[][] cost) {
        this.cost = cost;
        this.N = N;
        this.memo = new int[N+1][3];
        for(int i=0; i<N; i++) {
            Arrays.fill(memo[i], -1);
        }
        
        int ret = (int)1e9;
        for(int i=0; i<3; i++) {
            ret = Math.min(ret, getMin(0, i));
        }
        return ret;
    }
    
    // h: 집
    // c: 해당 집이 선택한 색.
    // 지금집에 대해 0, 1, 2색을 각각 칠한경우중 최소값
    int getMin(int h, int pc) {
        if(h==N) return 0;
        if(memo[h][pc] != -1) return memo[h][pc];
        
        int ret = (int)1e9;
        // 다음집에대해 3가지 색 모두칠해보기.
        for(int i=0; i<3; i++) {
            if(i==pc) continue;
            ret = Math.min(ret, getMin(h+1, i) + cost[h][pc]);
        }
        return memo[h][pc] = ret;
    }
}