import java.io.*;
import java.util.StringTokenizer;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringTokenizer st;
    private static int N;
    private static int m;
    private static int[][] graph;
    private static int MAX = Integer.MAX_VALUE >>> 1;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        graph = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (i == j) continue;
                graph[i][j] = MAX;
            }
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int f = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph[f][t] = Math.min(graph[f][t], v);
        }

        for (int z = 1; z <= N; z++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    graph[i][j] = Math.min(graph[i][j], graph[i][z] + graph[z][j]);
                }
            }
        }

        String r = "";
        for (int i = 1; i <= N; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 1; j <= N; j++) {
                sb.append((graph[i][j] == MAX ? 0 : graph[i][j]) + " ");
            }
            r += sb.toString().trim() + "\n";
        }
        System.out.println(r.trim());
    }


}

