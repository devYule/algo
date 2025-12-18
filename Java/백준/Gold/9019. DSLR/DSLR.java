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

	class Each {
		int value;
		StringBuilder sb;
		Each(int v) { this.value=v; sb=new StringBuilder(); }
		Each(int v, StringBuilder sb, String last) { this.value=v; this.sb=new StringBuilder().append(sb).append(last); }
	}

	String resolve(int a, int b) {
		PriorityQueue<Each> q=new PriorityQueue<>((ae, be) -> ae.sb.length()-be.sb.length());
		q.add(new Each(a));

		boolean[] vis=new boolean[10001];
		vis[a]=true;
		while(!q.isEmpty()) {
			Each e=q.poll();
			int v=e.value;
			if(v==b) return e.sb.toString();
			vis[v]=true;
			int dv=D(v);
			if(!vis[dv]) {
				vis[dv]=true;
				q.add(new Each(dv, e.sb, "D"));
			}
			int sv=S(v);
			if(!vis[sv]) {
				q.add(new Each(sv, e.sb, "S"));
			}
			int lv=L(v);
			if(!vis[lv]) {
				vis[lv]=true;
				q.add(new Each(lv, e.sb, "L"));
			}
			int rv=R(v);
			if(!vis[rv]) {
				vis[rv]=true;
				q.add(new Each(rv, e.sb, "R"));
			}
		}
		return "-1";
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
