import java.util.*;
import java.io.*;
public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int N = Integer.parseInt(br.readLine());

    int ret = new Main().resolve(N);
    bw.write(String.valueOf(ret));
    bw.flush();
    bw.close();
    br.close();
  }

  int memo[];
  final int MAX = (int) 1e9;

  int resolve(int N) {
    this.memo = new int[N + 1];
    Arrays.fill(memo, -1);
    int ret = dfs(N);
    return ret >= MAX ? -1 : ret;
  }

  int dfs(int n) {
    if(n == 0) return 0;
    if(memo[n] != -1) return memo[n];
    if(n < 3) return MAX;
    int ret = MAX;
    if(n >= 5) ret = Math.min(ret, dfs(n - 5));
    ret = Math.min(ret, dfs(n - 3));
    return memo[n] = ret + 1;
  }
}