import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			bw.write(
				String.valueOf(
					new Main().resolve(
						Integer.parseInt(br.readLine())
					)
				)
			);
			bw.flush();
		}
	}


	@SuppressWarnings("unchecked")
	long resolve(int n) {
		if(n<=10) return n;

		Queue<Long>[] can=new Queue[10];
		Queue<Long>[] nextCan=new Queue[10];

		for(int i=1; i<10; i++) {
			can[i]=new LinkedList<>();
			nextCan[i]=new LinkedList<>();
		}

		for(int i=0; i<9; i++) {
			for(int j=i+1; j<10; j++) {
				can[j].add((long)i);
			}
		}

		int nextHead=2;
		int head=1;
		long mul=10;
		for(int i=10; i<=n; i++) {
			Queue<Long> candi=can[head];
			if(candi.isEmpty()) {
				if(head==9) {
					head=nextHead++;
					if(head==10) return -1;
					for(int j=head; j<10; j++) {
						can[j]=nextCan[j];
					}
					can=nextCan;
					nextCan=new LinkedList[10];
					for(int j=head+1; j<10; j++) nextCan[j]=new LinkedList<>();
					mul*=10;
				} else head++;
				candi=can[head];
			}

			long value=head*mul+candi.poll();
			for(int j=head+1; j<10; j++) {
				nextCan[j].add(value);
			}
			if(i==n) return value;
		}
		return -1;
	}
}