import java.io.*;
import java.util.*;
public class Main {
  public static void main(String[] agrs) throws IOException {
    try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {
      int n=Integer.parseInt(br.readLine());
      int[] nums=Arrays.stream(br.readLine().split(" ")).map(Integer::parseInt).mapToInt(a->a).toArray();
      bw.write(String.valueOf(new Main().resolve(n, nums)));
      bw.flush();
    }
  }


  int resolve(int n, int[] nums) {
    int[] dp=new int[n];
    int ret=1;
    Arrays.fill(dp, 1);
    for(int i=1; i<n; i++) {
      for(int j=0; j<i; j++) {
          if(nums[i]>nums[j]) dp[i]=Math.max(dp[i], dp[j]+1);
      }
      ret=Math.max(ret, dp[i]);
    }
    return ret;
  }
}