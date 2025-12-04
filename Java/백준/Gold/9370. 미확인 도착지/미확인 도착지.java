import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			int R=Integer.parseInt(br.readLine());

			for(int r=0; r<R; r++) {
				if(r!=0) bw.write("\n");

				StringTokenizer st=new StringTokenizer(br.readLine());

				int n=Integer.parseInt(st.nextToken());
				int m=Integer.parseInt(st.nextToken());
				int t=Integer.parseInt(st.nextToken());

				st=new StringTokenizer(br.readLine());

				int start=Integer.parseInt(st.nextToken());
				int g=Integer.parseInt(st.nextToken());
				int h=Integer.parseInt(st.nextToken());

				int[][] edge=new int[m][3];
				for(int i=0; i<m; i++) {
					st=new StringTokenizer(br.readLine());
					edge[i][0]=Integer.parseInt(st.nextToken());
					edge[i][1]=Integer.parseInt(st.nextToken());
					edge[i][2]=Integer.parseInt(st.nextToken());
				}
				int[] candi=new int[t];
				for(int i=0; i<t; i++) candi[i]=Integer.parseInt(br.readLine());
			


				bw.write(
					String.valueOf(
						new Main().resolve(
							n, m, start, g, h, t, edge, candi
						)
					)
				);
			}
			bw.flush();
		}
	}

	int head[], next[], to[], wt[], ghd, n, g, h;

	String resolve(int n, int m, int start, int g, int h, int t, int[][] edge, int[] candi) {
		this.n=n; this.g=g; this.h=h;
		init(n, m, edge);
		
		int[] fs=dijk(start);
		int[] fg=dijk(g);
		int[] fh=dijk(h);

		List<Integer> ret=new ArrayList<>();

		for(int c: candi) {
			if(fs[c]==(int)1e9 || fg[c]==(int)1e9 || fh[c]==(int)1e9) continue;
			int f2c=Math.min(
				fs[g]+ghd+fh[c],
				fs[h]+ghd+fg[c]
			);
			if(f2c==fs[c]) ret.add(c);
		}

		return ret.stream().sorted(Comparator.naturalOrder()).map(String::valueOf).collect(java.util.stream.Collectors.joining(" "));
	}
	int[] dijk(int start) {
		int[] dist=new int[n+1];
		Arrays.fill(dist, (int)1e9);
		PriorityQueue<int[]> q=new PriorityQueue<>((a, b) -> a[0]-b[0]);
		dist[start]=0;
		q.add(new int[] {0, start});
		while(!q.isEmpty()) {
			int[] as=q.poll();
			int a=as[1];
			int cost=as[0];
			if(dist[a]!=cost) continue;
			for(int ni=head[a]; ni!=-1; ni=next[ni]) {
				int b=to[ni];
				int nc=cost+wt[ni];
				if(dist[b]>nc) {
					dist[b]=nc;
					q.add(new int[] {nc, b});
				}
			}
		}
		return dist;
	}

	void init(int n, int m, int[][] edge) {
		head=new int[n+1];
		Arrays.fill(head, -1);
		next=new int[m*2];
		to=new int[m*2];
		wt=new int[m*2];
		int ei=0;
		for(int[] e: edge) {
			int a=e[0], b=e[1], d=e[2];
			wt[ei]=d;
			to[ei]=b;
			next[ei]=head[a];
			head[a]=ei++;

			wt[ei]=d;
			to[ei]=a;
			next[ei]=head[b];
			head[b]=ei++;

			if((a==g && b==h) || (a==h && b==g)) {
				ghd=d;
			}
		}
	}
}
