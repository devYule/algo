import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int n = Integer.parseInt(br.readLine());
            int r = new Main().resolve(n);
            bw.write(String.valueOf(r));
            bw.flush();
        }
    }
    
    int[] memo;
    
    int resolve(int n) {
        this.memo = new int[n + 1];
        Arrays.fill(memo, -1);
        return block(n);
    }
    
    int block(int n) {
        if(memo[n] != -1) return memo[n];
        if(n == 1) return 1;
        if(n == 2) return 2;
        return memo[n] = (block(n - 1) + block(n - 2)) % 10007;
    }
}