import java.util.Arrays;
import java.util.Scanner;

public class Main {

    private static int N;
    private static int[] nums;
    private static int[] dp;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        nums = new int[N];
        dp = new int[N];

        for (int i = 0; i < N; i++) {
            nums[i] = sc.nextInt();
        }

        for (int i = 0; i < N; i++) {
            dp[i] = nums[i];

            for (int j = 0; j < i; j++) {
                if (nums[i] <= nums[j]) continue;
                dp[i] = Math.max(dp[i], dp[j] + nums[i]);
            }
        }

        int r = -1;
        for (int i = 0; i < N; i++) {
            r = Math.max(r, dp[i]);
        }
        System.out.println(r);
    }

}
