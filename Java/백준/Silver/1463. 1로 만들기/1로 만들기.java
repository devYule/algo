import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int n = Integer.parseInt(br.readLine());

            int ret = new Main().resolve(n);
            bw.write(String.valueOf(ret));
        }
    }
    int[] memo;
    final int MAX = (int) 1e9;
    int resolve(int n) {
        this.memo = new int[n + 1];
        Arrays.fill(memo, -1);
        int ret = dfs(n);
        return ret >= MAX ? -1 : ret;
    }
    int dfs(int n) {
        if(n == 1) return 0;
        if(memo[n] != -1) return memo[n];
        int a = MAX;
        int b = MAX;
        if(n % 3 == 0) a = dfs(n / 3);
        if(n % 2 == 0) b = dfs(n / 2);
        return memo[n] = Math.min(dfs(n - 1), Math.min(a, b)) + 1;
    }
}