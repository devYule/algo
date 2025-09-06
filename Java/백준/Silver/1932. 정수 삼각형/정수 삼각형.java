
import java.io.*;
import java.util.StringTokenizer;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringTokenizer st;

    private static final int[] cm = {-1, 0};
    private static int N;
    private static int[][] nums;
    private static int[][] dp;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        nums = new int[N][N];
        dp = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j <= i; j++) {
                nums[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        dp[0][0] = nums[0][0];
        for (int i = 1; i < N; i++) {
            for (int j = 0; j <= i; j++) {
                for (int k = 0; k < 2; k++) {
                    if (j + cm[k] < 0 || j + cm[k] > i) continue;
                    dp[i][j] = Math.max(dp[i][j], nums[i][j] + dp[i - 1][j + cm[k]]);
                }
            }
        }


        int r = -1;
        for (int i = 0; i < N; i++) {
            r = Math.max(r, dp[N - 1][i]);
        }
        bw.write(r + "");
        bw.flush();
        bw.close();
        br.close();

    }


}
