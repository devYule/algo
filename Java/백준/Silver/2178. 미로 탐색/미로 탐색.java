import java.io.*;
import java.util.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringTokenizer st;

    private static int N, M;
    private static int[][] map;
    private static int[][] dist;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N + 1][M + 1];
        dist = new int[N + 1][M + 1];
        for (int i = 1; i <= N; i++) {
            String line = br.readLine();
            for (int j = 1; j <= M; j++) {
                map[i][j] = Integer.parseInt(String.valueOf(line.charAt(j - 1)));
            }
        }

        Queue<int[]> queue = new LinkedList<>();
        dist[1][1] = 1;
        queue.add(new int[]{1, 1});
        int[] rm = {0, 1, -1, 0};
        int[] cm = {1, 0, 0, -1};
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nextRow = cur[0] + rm[i];
                int nextCol = cur[1] + cm[i];
                if (nextRow < 0 || nextCol < 0 || nextRow > N || nextCol > M) continue;
                if (map[nextRow][nextCol] == 1 && dist[nextRow][nextCol] == 0) {
                    dist[nextRow][nextCol] = dist[cur[0]][cur[1]] + 1;
                    queue.add(new int[]{nextRow, nextCol});
                }
            }
        }
        System.out.println(dist[N][M]);

    }
}
