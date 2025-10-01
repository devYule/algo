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

	boolean[] rule;
	String resolve(int n) {
		this.rule=new boolean[25];
		rule[2]=rule[7]=rule[10]=rule[11]=rule[12]=rule[13]=rule[14]=rule[16]=rule[17]=rule[18]=rule[21]=rule[23]=true;

		int[][] d=draw(n);
		StringBuilder sb=new StringBuilder();
		for(int i=0; i<d.length; i++) {
			for(int j=0; j<d.length; j++) {
				if(d[i][j]==1) sb.append("*");
				else sb.append(" ");
			}
			sb.append("\n");
		}
		return sb.toString();
	}

	int[][] draw(int n) {
		if(n==0) return new int[][] {{1}};
		int[][] prev=draw(n-1);

		int[][] newChar=new int[prev.length*5][prev.length*5];

		int rr=0, cr=0;
		for(int i=0; i<newChar.length; i+=prev.length) {
			for(int j=0; j<newChar.length; j+=prev.length) {
				if(rule[cell(rr, cr)]) {
					for(int k=0; k<prev.length; k++) {
						for(int l=0; l<prev.length; l++) {
							newChar[i+k][j+l]=prev[k][l];
						}
					}
				}
				cr++;
			}
			rr++;
			cr=0;
		}
		return newChar;
	}

	int cell(int y, int x) {
		return y*5+x;
	}
}
