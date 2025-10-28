import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			int n=Integer.parseInt(br.readLine());

			int[][] edge=new int[n-1][2];
			for(int i=0; i<n-1; i++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				edge[i][0]=Integer.parseInt(st.nextToken());
				edge[i][1]=Integer.parseInt(st.nextToken());
			}

			int m=Integer.parseInt(br.readLine());

			int[][] queries=new int[m][2];
			for(int i=0; i<m; i++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				queries[i][0]=Integer.parseInt(st.nextToken());
				queries[i][1]=Integer.parseInt(st.nextToken());
			}

			bw.write(
				String.valueOf(
					new Main().resolve(
						n, edge, m, queries
					)
				)
			);
			bw.flush();
		}
	}

	int to[], nxt[], head[], ei;
	int node2flat[];
	List<Integer> depth, flat;
	int n;

	String resolve(int n, int[][] edge, int m, int[][] Q) {
		init(n, edge);

		flat(1, 1, -1);

		Segment seg=new Segment(flat, depth);
		List<Integer> ret=new ArrayList<>();
		for(int[] q: Q) {
			int a=node2flat[q[0]];
			int b=node2flat[q[1]];
			if(a>b) {
				int tmp=a;
				a=b;
				b=tmp;
			}
			ret.add(flat.get(seg.query(a, b)));
		}
		return ret.stream().map(String::valueOf).collect(java.util.stream.Collectors.joining("\n"));
	}

	static class Segment {
		int[] tree;
		int n;
		List<Integer> flat, depth;
		Segment(List<Integer> flat, List<Integer> depth) {
			this.flat=flat; 
			this.depth=depth;
			this.n=flat.size();
			this.tree=new int[n*4];
			init(0, n-1, 1);
		}

		int init(int l, int r, int node) {
			if(l==r) return tree[node]=l;
			int mid=(l+r)>>>1;
			int lr=init(l, mid, node*2);
			int rr=init(mid+1, r, node*2+1);

			if(depth.get(lr)<depth.get(rr)) return tree[node]=lr;
			return tree[node]=rr;
		}

		int query(int l, int r) {
			return query(l, r, 0, n-1, 1);
		}

		int query(int bl, int br, int l, int r, int node) {
			if(l>br || r<bl) return -1;
			if(l>=bl && r<=br) return tree[node];
			int mid=(l+r)>>>1;
			int lr=query(bl, br, l, mid, node*2);
			int rr=query(bl, br, mid+1, r, node*2+1);
			if(lr==-1) return rr;
			if(rr==-1) return lr;
			if(depth.get(lr)<depth.get(rr)) return lr;
			return rr;
		}

	}

	void flat(int a, int dep, int parent) {
		node2flat[a]=flat.size();
		depth.add(dep);
		flat.add(a);

		for(int i=head[a]; i!=-1; i=nxt[i]) {
			int b=to[i];

			if(b==parent) continue;

			flat(b, dep+1, a);
			depth.add(dep);
			flat.add(a);
		}
	}

	void init(int n, int[][] edge) {
		this.n=n;
		int E=edge.length;

		this.to=new int[E*2];
		this.nxt=new int[E*2];
		this.head=new int[n+1];
		Arrays.fill(head, -1);
		this.ei=0;

		this.node2flat=new int[n+1];

		this.depth=new ArrayList<>();
		this.flat=new ArrayList<>();

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
