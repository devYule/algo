import java.util.Scanner;

/*
카드는 8종류
카드팩의 형태로 구매.
카드팩은 카드 N 개가 포함된 카드팩으로 구성.
목표: N 개의 카드를 마련할 수 있는 최대 금액.
카드팩 가격은 i 개의 카드가 들어있으면 P[i]원.
카드팩은 목표와 완전히 동일해야 함. (넘치게 사고 버리기 X)
 */
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

        // dp[i] = i 개를 샀을 때 최대 금액
        for (int i = 1; i <= N; i++) {
            for (int j = i; j <= N; j++) {
                dp[j] = Math.max(dp[j - i] + nums[i], dp[j]);
            }
        }
        System.out.println(dp[N]);

    }
}
