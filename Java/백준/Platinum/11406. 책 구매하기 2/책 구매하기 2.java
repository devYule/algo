import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			StringTokenizer st=new StringTokenizer(br.readLine());
			int n=Integer.parseInt(st.nextToken());
			int m=Integer.parseInt(st.nextToken());

			int[] manBuy=new int[n];

			st=new StringTokenizer(br.readLine());
			for(int i=0; i<n; i++) {
				manBuy[i]=Integer.parseInt(st.nextToken());
			}

			int[] sellerHave=new int[m];
			st=new StringTokenizer(br.readLine());
			for(int i=0; i<m; i++) {
				sellerHave[i]=Integer.parseInt(st.nextToken());
			}

			int[][] canBuy=new int[m][n];

			for(int i=0; i<m; i++) {
				st=new StringTokenizer(br.readLine());
				for(int j=0; j<n; j++) {
					canBuy[i][j]=Integer.parseInt(st.nextToken());
				}
			}


			bw.write(
				String.valueOf(
					new Main().resolve(
						n, m, manBuy, sellerHave, canBuy
					)
				)
			);
			bw.flush();
		}
	}

	int resolve(int man, int seller, int[] manBuy, int[] sellerHave, int[][] canBuy) {
		return new Flow(man, seller, manBuy, sellerHave, canBuy).flow();
	}

	class Flow {
		int head[], nxt[], to[], ei;
		int rest[], dist[], thead[];
		int n, s, t;

		Flow(int man, int seller, int[] manBuy, int[] sellerHave, int[][] canBuy) {
			this.n=man+seller+2;
			this.head=new int[n];
			Arrays.fill(head, -1);
			int E=man+seller+(man*seller);
			this.nxt=new int[E*2];
			this.to=new int[E*2];
			this.ei=0;

			this.rest=new int[E*2];
			this.dist=new int[n];
			this.thead=new int[n];

			this.s=man+seller;
			this.t=s+1;
			for(int i=0; i<seller; i++) {
				rest[ei]=sellerHave[i];
				to[ei]=i;
				nxt[ei]=head[s];
				head[s]=ei++;

				to[ei]=s;
				nxt[ei]=head[i];
				head[i]=ei++;
			}

			for(int i=0; i<man; i++) {
				int a=seller+i;
				rest[ei]=manBuy[i];
				to[ei]=t;
				nxt[ei]=head[a];
				head[a]=ei++;

				to[ei]=a;
				nxt[ei]=head[t];
				head[t]=ei++;
			}

			for(int i=0; i<canBuy.length; i++) {
				int si=i;
				for(int j=0; j<canBuy[i].length; j++) {
					int mi=j+seller;
					rest[ei]=canBuy[i][j];
					to[ei]=mi;
					nxt[ei]=head[si];
					head[si]=ei++;

					to[ei]=si;
					nxt[ei]=head[mi];
					head[mi]=ei++;
				}
			}
		}

		boolean bfs() {
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

		int dfs(int a, int flowed) {
			if(a==t) return flowed;

			for(int i=thead[a]; i!=-1; i=nxt[i]) {
				if(rest[i]>0) {
					int b=to[i];
					if(dist[b]==dist[a]+1) {
						int push=dfs(b, Math.min(flowed, rest[i]));
						if(push>0) {
							rest[i]-=push;
							rest[i^1]+=push;
							return push;
						}
					}
				}
				thead[a]=nxt[i];
			}
			return 0;
		}

		int flow() {
			int ret=0;
			while(bfs()) {
				System.arraycopy(head, 0, thead, 0, head.length);
				while(true) {
					int push=dfs(s, Integer.MAX_VALUE);
					if(push==0) break;
					ret+=push;
				}
			}
			return ret;
		}

	}
}
