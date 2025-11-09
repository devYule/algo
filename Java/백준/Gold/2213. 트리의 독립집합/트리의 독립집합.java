import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			int n=Integer.parseInt(br.readLine());

			int[] nums=new int[n];
			StringTokenizer st=new StringTokenizer(br.readLine());
			for(int i=0; i<n; i++) {
				nums[i]=Integer.parseInt(st.nextToken());
			}

			int[][] edge=new int[n-1][2];
			for(int i=0; i<n-1; i++) {
				st=new StringTokenizer(br.readLine());
				edge[i][0]=Integer.parseInt(st.nextToken());
				edge[i][1]=Integer.parseInt(st.nextToken());
			}

			bw.write(
				String.valueOf(
					new Main().resolve(
						n, nums, edge
					)
				)
			);
			bw.flush();
		}
	}

	int head[], nxt[], to[];

	String resolve(int n, int[] nums, int[][] edge) {
		init(n, edge);

		int[] parent=new int[n+1];
		int[] order=new int[n];
		int oi=0;
		Arrays.fill(parent, -1);
		ArrayDeque<Integer> q=new ArrayDeque<>();
		q.add(1);
		parent[1]=1;
		order[oi++]=1;

		while(!q.isEmpty()) {
			int a=q.removeFirst();
			for(int ni=head[a]; ni!=-1; ni=nxt[ni]) {
				int b=to[ni];

				if(parent[b]==-1) {
					parent[b]=a;
					order[oi++]=b;
					q.add(b);
				}
			}
		}

		int[][] dist=new int[n+1][2];
		for(int i=oi-1; i>=0; i--) {
			int a=order[i];

			int conta=nums[a-1];
			int contb=0;

			for(int ni=head[a]; ni!=-1; ni=nxt[ni]) {
				int b=to[ni];

				if(b==parent[a]) continue;

				conta+=dist[b][0];
				contb+=Math.max(dist[b][1], dist[b][0]);
			}
			dist[a][1]=conta;
			dist[a][0]=contb;
		}

		List<Integer> ret=new ArrayList<>();

		int[] flags=new int[n+1];
		flags[1]=dist[1][0]>dist[1][1] ? 0 : 1;
		q.add(1);

		while(!q.isEmpty()) {
			int a=q.removeFirst();
			if(flags[a]==1) ret.add(a);
			for(int ni=head[a]; ni!=-1; ni=nxt[ni]) {
				int b=to[ni];

				if(b==parent[a]) continue;

				if(flags[a]==0) {
					if(dist[b][1]>dist[b][0]) {
						flags[b]=1;
					}
				}
				q.add(b);
			}
		}

		ret.sort(Comparator.naturalOrder());

		return Math.max(dist[1][0], dist[1][1]) + "\n" + ret.stream().map(String::valueOf).collect(java.util.stream.Collectors.joining(" "));

	}

	void init(int n, int[][] edge) {
		int E=edge.length;
		this.head=new int[n+1];
		Arrays.fill(head, -1);
		this.nxt=new int[E*2];
		this.to=new int[E*2];

		int ei=0;
		for(int[] e: edge) {
			int a=e[0], b=e[1];
			to[ei]=b;
			nxt[ei]=head[a];
			head[a]=ei++;

			to[ei]=a;
			nxt[ei]=head[b];
			head[b]=ei++;
		}
	}
}
