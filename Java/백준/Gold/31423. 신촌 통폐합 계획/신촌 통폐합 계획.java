import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			int n=Integer.parseInt(br.readLine());
			String[] school=new String[n];
			for(int i=0; i<n; i++) {
				school[i]=br.readLine();
			}
			int[][] edge=new int[n-1][2];
			for(int i=0; i<n-1; i++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				edge[i][0]=Integer.parseInt(st.nextToken());
				edge[i][1]=Integer.parseInt(st.nextToken());
			}

			bw.write(
				String.valueOf(
					new Main().resolve(
						n, school, edge
					)
				)
			);
			bw.flush();
		}
	}

	List<Integer>[] adj;
	@SuppressWarnings("unchecked")
	String resolve(int n, String[] sc, int[][] edge) {
		adj=new ArrayList[n];
		for(int i=0; i<n; i++) adj[i]=new ArrayList<>();

		int[] ind=new int[n];
		for(int[] e: edge) {
			e[0]--;
			e[1]--;
			adj[e[0]].add(e[1]);
			ind[e[1]]++;
		}

		int start=-1;
		int tot=0;
		for(int i=0; i<n; i++) {
			if(ind[i]==0) start=i;
			tot+=sc[i].length();
		}

		StringBuilder sb=new StringBuilder(tot);
		Stack<Integer> st=new Stack<>();
		st.add(start);

		while(!st.isEmpty()) {
			int cur=st.pop();
			sb.append(sc[cur]);

			for(int i=adj[cur].size()-1; i>=0; i--) {
				st.add(adj[cur].get(i));
			}
		}

		return sb.toString();
	}

	
}
