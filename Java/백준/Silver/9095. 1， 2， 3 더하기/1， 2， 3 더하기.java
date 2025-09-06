import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int round = Integer.parseInt(br.readLine());
            StringBuilder sb = new StringBuilder();
            Main m = new Main();
            for(int i = 0; i < round; i++) {
                int n = Integer.parseInt(br.readLine());
                sb.append(String.valueOf(m.resolve(n)));
                if(i < round - 1) sb.append("\n");
            }
            bw.write(sb.toString());
        }
    }
    
    int[] memo;
    
    int resolve(int n) {
        this.memo = new int[n + 1];
        Arrays.fill(memo, -1);
        return dfs(n);
    }
    int dfs(int n) {
        if(memo[n] != -1) return memo[n];
        if(n == 0) return 1;
        int ret = 0;
        for(int i = 1; i <= 3; i++) {
            if(n - i < 0) continue;
            ret += dfs(n - i);
        }
        return memo[n] = ret;
    }
}