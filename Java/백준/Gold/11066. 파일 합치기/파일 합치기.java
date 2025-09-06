
import java.util.Scanner;

public class Main {
    private static int CNT;
    private static int N;
    private static int[] nums;
    private static int[][] memo;

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        CNT = sc.nextInt();
        for (int i = 0; i < CNT; i++) {
            System.out.println(solution());
        }
    }

    private static int solution() {

        N = sc.nextInt();
        memo = new int[N][N];
        nums = new int[N];
        for (int i = 0; i < N; i++) {
            nums[i] = sc.nextInt();
        }

        return execute(0, N - 1);
    }

    private static int execute(int from, int to) {
        if (from == to) return 0;
        if (memo[from][to] != 0) return memo[from][to];

        int r = Integer.MAX_VALUE;
        int sum = 0;
        for (int i = from; i <= to; i++) {
            sum += nums[i];
        }
        for (int i = from; i < to; i++) {
            int le = execute(from, i);
            int ri = execute(i + 1, to);
            r = Math.min(r, le + ri + sum);
        }
        return memo[from][to] = r;
    }
}
