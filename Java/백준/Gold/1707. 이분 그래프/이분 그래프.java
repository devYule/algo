import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			int T=Integer.parseInt(br.readLine());
			for(int r=0; r<T; r++) {
				if(r!=0) bw.write("\n");

				StringTokenizer st=new StringTokenizer(br.readLine());

				int n=Integer.parseInt(st.nextToken());
				int m=Integer.parseInt(st.nextToken());

				int[][] edge=new int[m][2];
				for(int i=0; i<m; i++) {
					st=new StringTokenizer(br.readLine());
					edge[i][0]=Integer.parseInt(st.nextToken());
					edge[i][1]=Integer.parseInt(st.nextToken());
				}
				bw.write(
					String.valueOf(
						new Main().resolve(
							n, m, edge
						)
					)
				);
			}
			bw.flush();
		}
	}

	int head[], to[], next[];
	String resolve(int n, int m, int[][] edge) {
		init(n, edge);

		ArrayDeque<Integer> q=new ArrayDeque<>();
		int[] pair=new int[n+1];
		Arrays.fill(pair, -1);
		for(int i=1; i<=n; i++) {
			if(pair[i]!=-1) continue;
			pair[i]=0;
			q.add(i);
			while(!q.isEmpty()) {
				int a=q.removeFirst();
				int ep=(pair[a]+1)%2;
				for(int ni=head[a]; ni!=-1; ni=next[ni]) {
					int b=to[ni];
					if(pair[b]==pair[a]) return "NO";
					else if(pair[b]==-1) {
						pair[b]=ep;
						q.add(b);
					}
				}
			}
		}

		return "YES";
	}

	void init(int n, int[][] edge) {
		int E=edge.length;
		head=new int[n+1];
		Arrays.fill(head, -1);
		to=new int[E*2];
		next=new int[E*2];
		int ei=0;
		for(int[] e: edge) {
			int a=e[0], b=e[1];
			to[ei]=b;
			next[ei]=head[a];
			head[a]=ei++;

			to[ei]=a;
			next[ei]=head[b];
			head[b]=ei++;
		}
	}

}
