import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			StringTokenizer st=new StringTokenizer(br.readLine());

			int n=Integer.parseInt(st.nextToken());
			int m=Integer.parseInt(st.nextToken());

			int[][] info=new int[m][2];
			for(int i=0; i<m; i++) {
				st=new StringTokenizer(br.readLine());
				info[i][0]=Integer.parseInt(st.nextToken());
				info[i][1]=Integer.parseInt(st.nextToken());
			}

			bw.write(
				String.valueOf(
					new Main().resolve(
						n, m, info
					)
				)
			);
			bw.flush();
		}
	}

	int head[], to[], nxt[], ind[];

	String resolve(int n, int m, int[][] info) {
		init(n, info);

		ArrayDeque<Integer> q=new ArrayDeque<>();
		for(int i=1; i<=n; i++) {
			if(ind[i]==0) q.add(i);
		}
		boolean on=false;
		StringBuilder sb=new StringBuilder();
		while(!q.isEmpty()) {
			int a=q.poll();
			if(on) sb.append(" ");
			sb.append(String.valueOf(a));
			on=true;
			for(int i=head[a]; i!=-1; i=nxt[i]) {
				int b=to[i];
				if(--ind[b]==0) {
					q.add(b);
				}
			}

		}
		return sb.toString();
	}

	void init(int n, int[][] info) {
		this.ind=new int[n+1];
		this.head=new int[n+1];
		Arrays.fill(head, -1);
		int E=info.length;
		this.to=new int[E];
		this.nxt=new int[E];

		int ei=0;
		for(int[] e: info) {
			int a=e[0], b=e[1];
			ind[b]++;

			to[ei]=b;
			nxt[ei]=head[a];
			head[a]=ei++;
		}
	}

}
