import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    private static int N, K;
    private static int[] memo;
    private static int[] cnt;

    public static void main(String[] args) {


        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        K = sc.nextInt();
        memo = new int[1000001];
        cnt = new int[1000001];
        for (int i = 0; i < memo.length; i++) memo[i] = -1;
        cnt[N] = 1;
        memo[N] = 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(N);
        while (!queue.isEmpty()) {
            int cur = queue.poll();

            int[] oper = {cur + 1, cur - 1, cur * 2};
            for (int i = 0; i < 3; i++) {
                int target = oper[i];
                if (target < 0 || target >= memo.length) continue;
                if (memo[target] == -1) {
                    memo[target] = memo[cur] + 1;
                    cnt[target] = cnt[cur];
                    queue.add(target);
                } else if (memo[target] == memo[cur] + 1) {
                    cnt[target] += cnt[cur];
                }
            }
        }


        System.out.println(memo[K]);
        System.out.println(cnt[K]);


    }


}
