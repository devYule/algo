import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            int s = Integer.parseInt(br.readLine());
            int[] scores = new int[s];
            for(int i = 0; i < s; i++) {
                int n = Integer.parseInt(br.readLine());
                scores[i] = n;
            }
            int r = new Main().solution(scores);
            bw.write(String.valueOf(r));
            bw.flush();
        }
    }
    
    int[][] memo;
    int[] score;
    
    int solution(int[] score) {
        int n = score.length;
        this.score = score;
        this.memo = new int[n][3];
        for(int i = 0; i < n; i++) Arrays.fill(memo[i], -1);
        return stairs(n - 1, 1);
    }
    
    int stairs(int n, int acc) {
        if(n == 0) return score[0];
        if(n < 0) return -1;
        if(memo[n][acc] != -1) return memo[n][acc];
        int ret = 0;
        if(acc < 2) ret = Math.max(ret, stairs(n - 1, acc + 1));
        ret = Math.max(ret, stairs(n - 2, 1));
        return memo[n][acc] = ret + score[n];
    } 
}