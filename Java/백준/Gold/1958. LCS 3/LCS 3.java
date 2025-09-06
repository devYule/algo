import java.io.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static char[][] chars;
    private static int[][][] dp; // i, j, k 로 구성된 문자열의 최대공통 수열의 수.

    public static void main(String[] args) throws IOException {
        chars = new char[3][0];
        for (int i = 0; i < 3; i++) {
            chars[i] = br.readLine().toCharArray();
        }

        dp = new int[chars[0].length + 1][chars[1].length + 1][chars[2].length + 1];

        for (int i = 1; i <= chars[0].length; i++) {
            for (int j = 1; j <= chars[1].length; j++) {
                for (int k = 1; k <= chars[2].length; k++) {
                    if (chars[0][i - 1] == chars[1][j - 1] && chars[0][i - 1] == chars[2][k - 1]) {
                        dp[i][j][k] = dp[i - 1][j - 1][k - 1] + 1;
                    } else {
                        dp[i][j][k] = Math.max(dp[i - 1][j][k], Math.max(dp[i][j - 1][k], dp[i][j][k - 1]));
                    }
                }
            }
        }

        bw.write(dp[chars[0].length][chars[1].length][chars[2].length] + "");
        bw.flush();
        bw.close();
        br.close();

    }

}
