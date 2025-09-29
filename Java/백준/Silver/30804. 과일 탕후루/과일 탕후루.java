import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			int n=Integer.parseInt(br.readLine());
			int[] candy=Arrays.stream(br.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();


			bw.write(
				String.valueOf(
					new Main().resolve(
						n, candy
					)
				)
			);
			bw.flush();
		}
	}

	int resolve(int n, int[] candy) {
		int left=0; 
		int right=0;
		int[] type=new int[10];
		int selCnt=0;
		int ret=0;
		while(right<n) {
			while(right<n && (selCnt<2 || type[candy[right]]!=0)) {
				if(type[candy[right]]==0) selCnt++;
				type[candy[right]]++;
				right++;
			}
			ret=Math.max(ret, right-left);
			while(selCnt>1 && left<right) {
				type[candy[left]]--;
				if(type[candy[left]]==0) selCnt--;
				left++;
			}
		}
		return ret;
	}


}
