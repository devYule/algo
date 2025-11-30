import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

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
			bw.flush();
		}
	}

	int head[], to[], next[], rank[], irank;
	boolean[] cut;

	String resolve(int n, int m, int[][] edge) {
		init(n, edge);

		for(int i=1; i<=n; i++) {
			if(rank[i]==0) {
				find(i, -1, true);
			}
		}

		List<Integer> rets=new ArrayList<>();
		for(int i=1; i<=n; i++) {
			if(cut[i]) rets.add(i);
		}

		rets.sort(Comparator.naturalOrder());

		return rets.size() + "\n" + rets.stream().map(String::valueOf).collect(java.util.stream.Collectors.joining(" "));
	}

	int find(int a, int parent, boolean isRoot) {
		rank[a]=irank++;
		int min=rank[a];
		int childCnt=0;
		for(int ni=head[a]; ni!=-1; ni=next[ni]) {
			int b=to[ni];
			if(rank[b]!=0) min=Math.min(min, rank[b]);
			else {
				childCnt++;
				int submin=find(b, a, false);
				if(submin>=rank[a]) cut[a]=true;
				min=Math.min(min, submin);
			}
		}
		if(isRoot) cut[a]=childCnt>=2;

		return min;
	}

	void init(int n, int[][] edge) {
		int E=edge.length;
		head=new int[n+1];
		Arrays.fill(head, -1);
		to=new int[E*2];
		next=new int[E*2];

		rank=new int[n+1];
		irank=1;

		cut=new boolean[n+1];

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
