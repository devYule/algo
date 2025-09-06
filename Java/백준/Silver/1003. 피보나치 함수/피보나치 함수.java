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
          int[] ret = m.resolve(n);
          sb.append(String.valueOf(ret[0])).append(" ").append(ret[1]);
          if(i < round - 1) sb.append("\n");
        }
        bw.write(sb.toString());
      }
    }
    int[][] memo;
    int[] resolve(int n) {
        this.memo = new int[n + 1][];
        return fibo(n);
    }

    int[] fibo(int n) {
        if(memo[n] != null) return memo[n];
        if(n == 0) return new int[] {1, 0};
        if(n == 1) return new int[] {0, 1};
        int[] reta = fibo(n - 1);
        int[] retb = fibo(n - 2);
        return memo[n] = new int[] {reta[0] + retb[0], reta[1] + retb[1]};
    }
}