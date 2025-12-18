import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			int T=Integer.parseInt(br.readLine());
			for(int t=0; t<T; t++) {
				if(t!=0) bw.write("\n");

				StringTokenizer st=new StringTokenizer(br.readLine());

				bw.write(
					String.valueOf(
						new Main().resolve(
							Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())
						)
					)
				);
			}
			bw.flush();
		}
	}



	String resolve(int a, int b) {
		ArrayDeque<Integer> q=new ArrayDeque<>();
		q.add(a);

		int[] prev=new int[10001];
		int[] select=new int[10001];
		Arrays.fill(prev, -1);
		Arrays.fill(select, -1);
		prev[a]=a;
		while(!q.isEmpty()) {
			int cur=q.removeFirst();
			if(cur==b) {
				break;
			}
			int d=D(cur);
			int s=S(cur);
			int l=L(cur);
			int r=R(cur);

			if(prev[d]==-1) {
				prev[d]=cur;
				select[d]=0;
				q.add(d);
			}
			if(prev[s]==-1) {
				prev[s]=cur;
				select[s]=1;
				q.add(s);
			}
			if(prev[l]==-1) {
				prev[l]=cur;
				select[l]=2;
				q.add(l);
			}
			if(prev[r]==-1) {
				prev[r]=cur;
				select[r]=3;
				q.add(r);
			}
		}

		String[] s=new String[] {"D", "S", "L", "R"};
		StringBuilder sb=new StringBuilder();
		int cur=b;
		while(true) {
			if(cur==a) break;
			sb.append(s[select[cur]]);
			cur=prev[cur];
		}
		return sb.reverse().toString();
	}

	int D(int a) {
		return a*2%10000;
	}
	int S(int a) {
		a--;
		return a==-1 ? 9999 : a;
	}
	int L(int a) {
		int head=a/1000;
		int tail=a%1000;
		return tail*10+head;
	}
	int R(int a) {
		int tail=a%10;
		int head=a/10;
		return tail*1000+head;
	}

}
