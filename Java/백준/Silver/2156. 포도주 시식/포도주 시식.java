import java.util.Scanner;

public class Main {

    private static int N;
    private static int[] nums;
    private static int[] dp;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        nums = new int[N + 1];
        dp = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            nums[i] = sc.nextInt();
        }

        if (N == 1 || N == 2) {
            int r = 0;
            for (int i = 1; i <= N; i++) {
                r += nums[i];
            }
            System.out.println(r);
            return;
        }


        dp[1] = nums[1];
        dp[2] = nums[1] + nums[2];
        for (int i = 3; i <= N; i++) {
            dp[i] = Math.max(dp[i - 1], Math.max(
                    dp[i - 2] + nums[i],
                    dp[i - 3] + nums[i] + nums[i - 1]
            ));
        }
        System.out.println(dp[N]);
        sc.close();
    }

}
