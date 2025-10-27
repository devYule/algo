import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			int n=Integer.parseInt(br.readLine());

			String[][] edge=new String[n][3];

			for(int i=0; i<n; i++) {
				edge[i]=br.readLine().split("\\s");
			}

			bw.write(
				String.valueOf(
					new Main().resolve(
						n, edge
					)
				)
			);
			bw.flush();
		}
	}

	int resolve(int n, String[][] edge) {

		Map<String, Integer> nodeNum=new HashMap<>();
		int ni=1;
		int[][] newEdge=new int[edge.length][3];
		for(int i=0; i<edge.length; i++) {
			String[] e=edge[i];
			String a=e[0], b=e[1];
			int flow=Integer.parseInt(e[2]);

			if(nodeNum.get(a)==null) {
				nodeNum.put(a, ni++);
			}
			if(nodeNum.get(b)==null) {
				nodeNum.put(b, ni++);
			}
			newEdge[i][0]=nodeNum.get(a);
			newEdge[i][1]=nodeNum.get(b);
			newEdge[i][2]=flow;
		}

		return new Flow(nodeNum.keySet().size(), newEdge).flow(nodeNum.get("A"), nodeNum.get("Z"));
	}

	class Flow {
		int head[], nxt[], to[], ei;
		int rest[], dist[], tmpHead[];
		int n;

		Flow(int n, int[][] edge) {
			this.n=n;
			this.head=new int[n+1];
			Arrays.fill(head, -1);
			int E=edge.length;
			this.nxt=new int[E*2];
			this.to=new int[E*2];
			this.ei=0;

			this.rest=new int[E*2];
			this.dist=new int[n+1];
			this.tmpHead=new int[n+1];

			for(int[] e: edge) {
				int a=e[0], b=e[1], flow=e[2];
				rest[ei]=flow;
				to[ei]=b;
				nxt[ei]=head[a];
				head[a]=ei++;

				rest[ei]=flow;
				to[ei]=a;
				nxt[ei]=head[b];
				head[b]=ei++;
			}

		}

		boolean bfs(int s, int t) {
			Arrays.fill(dist, -1);
			Queue<Integer> q=new ArrayDeque<>();
			q.add(s);
			dist[s]=0;

			while(!q.isEmpty()) {
				int a=q.poll();
				for(int i=head[a]; i!=-1; i=nxt[i]) {
					if(rest[i]>0 && dist[to[i]]==-1) {
						dist[to[i]]=dist[a]+1;
						q.add(to[i]);
					}
				}
			}

			return dist[t]!=-1;
		}

		int dfs(int a, int t, int flowed) {
			if(a==t) return flowed;

			for(int i=tmpHead[a]; i!=-1; i=nxt[i]) {
				if(rest[i]>0) {
					int b=to[i];
					if(dist[b]==dist[a]+1) {
						int pushed=dfs(b, t, Math.min(flowed, rest[i]));
						if(pushed>0) {
							rest[i]-=pushed;
							rest[i^1]+=pushed;
							return pushed;
						}
					}
				}
				tmpHead[a]=nxt[i];
			}
			return 0;
		}

		int flow(int s, int t) {
			int ret=0;
			while(bfs(s, t)) {
				System.arraycopy(head, 0, tmpHead, 0, head.length);
                while(true) {
		    		int pushed=dfs(s, t, Integer.MAX_VALUE);
	    			if(pushed==0) break;
    				ret+=pushed;
                }
			}
			return ret;
		}

	}

}
