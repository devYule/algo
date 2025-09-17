import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			String[] split=br.readLine().split("\\s");

			bw.write(
				String.valueOf(
					new Main().resolve(
						Integer.parseInt(split[0]),
						Integer.parseInt(split[1]),
						Integer.parseInt(split[2]),
						Integer.parseInt(split[3]),
						Integer.parseInt(split[4]),
						Integer.parseInt(split[5])
					)
				)
			);
			bw.flush();
		}
	}

	double resolve(int x1, int y1, int x2, int y2, int x3, int y3) {
		/*
			점 A->B, C->D 를 고려할 경우
			: A->B 만큼의 거리를 C에서 이동한 지점에 점을 찍을 수 있다.
		*/

		if((y3-y1)*(x2-x1)==(y2-y1)*(x3-x1)) return -1.0D;

		int[][] ax=new int[4][2];
		ax[1][0]=y1; ax[1][1]=x1; ax[2][0]=y2; ax[2][1]=x2; ax[3][0]=y3; ax[3][1]=x3;
		Queue<List<Integer>> q=new ArrayDeque<>();
		permut(0, new ArrayList<>(), q);

		double max=Double.MIN_VALUE;
		double min=Double.MAX_VALUE;
		while(!q.isEmpty()) {
			List<Integer> order=q.poll();
			int a1=order.get(0);
			int a2=order.get(1);
			int b1=order.get(2);
			int b2=order.get(3);

			if(a1==4 || a2==4) {
				int tmp=a1;
				a1=b1;
				b1=tmp;
				tmp=a2;
				a2=b2;
				b2=tmp;
			}

			int ygap1=Math.abs(ax[a2][0]-ax[a1][0]);
			int xgap1=Math.abs(ax[a2][1]-ax[a1][1]);

			int ygap2=0;
			int xgap2=0;
			if(b1==4) {
				// a2<->b2
				ygap2=Math.abs(ax[a2][0]-ax[b2][0]);
				xgap2=Math.abs(ax[a2][1]-ax[b2][1]);
			} else {
				// a1<->b1
				ygap2=Math.abs(ax[a1][0]-ax[b1][0]);
				xgap2=Math.abs(ax[a1][1]-ax[b1][1]);
			}

			double dist1=0;
			double dist2=0;

			if(ygap1==0) dist1=xgap1;
			else if(xgap1==0) dist1=ygap1;
			else dist1=Math.sqrt(ygap1*ygap1+xgap1*xgap1);

			if(ygap2==0) dist2=xgap2;
			else if(xgap2==0) dist2=ygap2;
			else dist2=Math.sqrt(ygap2*ygap2+xgap2*xgap2);

			double sum=dist1+dist1+dist2+dist2;
			max=Math.max(max, sum);
			min=Math.min(min, sum);
		}
		return max-min;
	}

	void permut(int state, List<Integer> holder, Queue<List<Integer>> q) {
		if(state==0b1111) {
			q.add(new ArrayList<>(holder));
			return;
		}
		for(int i=0; i<4; i++) {
			if((state&1<<i)!=0) continue;
			holder.add(i+1);
			permut(state|1<<i, holder, q);
			holder.remove(holder.size()-1);
		}
	}
}
