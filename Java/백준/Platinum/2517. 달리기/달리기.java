import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			int n=Integer.parseInt(br.readLine());
			int[] score=new int[n];
			for(int i=0; i<n; i++) score[i]=Integer.parseInt(br.readLine());

			bw.write(
				String.valueOf(
					new Main().resolve(
						n, score
					)
				)
			);
			bw.flush();
		}
	}

	String resolve(int n, int[] score) {
		int[][] scoreIdx=new int[n][2];
		for(int i=0; i<n; i++) {
			scoreIdx[i][0]=score[i];
			scoreIdx[i][1]=i;
		}

		Arrays.sort(scoreIdx, (a, b) -> a[0]-b[0]);

		int order=1;
		for(int[] si: scoreIdx) {
			score[si[1]]=order++;
		}

		Seg seg=new Seg(order);

		
		StringBuilder sb=new StringBuilder();
		for(int i=0; i<n; i++) {
			if(i!=0) sb.append("\n");
			int s=score[i];
			int lower=seg.query(1, s-1);
			sb.append(String.valueOf(i+1-lower));
			seg.update(s, 1);
		}

		return sb.toString();
	}

	class Seg {
		int tree[], o[], n;
		Seg(int n) {
			this.n=n;
			this.tree=new int[4*n];
			this.o=new int[n];
		}

		void update(int idx, int value) {
			update(idx, value, 0, n-1, 1);
		}

		int update(int idx, int value, int l, int r, int node) {
			if(l>idx || r<idx) return tree[node];
			if(l==r) return tree[node]=value;
			int mid=(l+r)>>>1;
			return tree[node]=update(idx, value, l, mid, node*2)+update(idx, value, mid+1, r, node*2+1);
		}

		int query(int l, int r) {
			return query(l, r, 0, n-1, 1);
		}

		int query(int bl, int br, int l, int r, int node) {
			if(l>br || r<bl) return 0;
			if(l>=bl && r<=br) return tree[node];
			int mid=(l+r)>>>1;
			return query(bl, br, l, mid, node*2)+query(bl, br, mid+1, r, node*2+1);
		}
	}
}
