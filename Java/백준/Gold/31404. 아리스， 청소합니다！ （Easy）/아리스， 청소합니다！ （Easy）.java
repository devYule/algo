import java.io.*;
import java.util.*;
public class Main {
   public static void main(String[] args) throws IOException {
      try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
         BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

         StringTokenizer st=new StringTokenizer(br.readLine());
         int n=Integer.parseInt(st.nextToken());
         int m=Integer.parseInt(st.nextToken());
         st=new StringTokenizer(br.readLine());
         int r=Integer.parseInt(st.nextToken());
         int c=Integer.parseInt(st.nextToken());
         int d=Integer.parseInt(st.nextToken());

         int[][] A=new int[n][m];
         for(int i=0; i<n; i++) {
            A[i]=Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
         }

         int[][] B=new int[n][m];
         for(int i=0; i<n; i++) {
            B[i]=Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
         }

         bw.write(String.valueOf(new Main().resolve(
            n, m, r, c, d, A, B
         )));
         bw.flush();
      }
   }

   int[] rg={-1, 0, 1, 0};
   int[] cg={0, 1, 0, -1};
   int by, bx;
   int n, m, version, vis[][][], A[][], B[][];
   boolean[][] clean;
   int resolve(int n, int m, int r, int c, int d, int[][] A, int[][] B) {
      this.n=n; this.m=m;
      this.A=A; this.B=B;
      this.clean=new boolean[n][m];
      this.vis=new int[n][m][4];   
      this.version=0;
      int y=r, x=c;
      int ret=0;
      
      while(true) {
         if(!clean[y][x]) {
            clean[y][x]=true;
            version++;
            d=(d+A[y][x])%4;
            int ny=y+rg[d], nx=x+cg[d];
         		ret++;
         		if(!valid(ny, nx)) break;
         		y=ny; x=nx;
         } else {
         		this.by=y;
         		this.bx=x;
            int[] res=walk(y, x, d);
            if(res==null) break;
            y=res[0];
            x=res[1];
            d=res[2];
            ret+=res[3];
         }
      }

      return ret;
   }
   
   int[] walk(int y, int x, int d) {
   	if(!valid(y, x)) return null;
   	if(!clean[y][x]) return new int[] {y, x, d, 0};
   	if(vis[y][x][d]==version) return null;

   	vis[y][x][d]=version;

   	int nd=(d+B[y][x])%4;
   	int ny=y+rg[nd];
   	int nx=x+cg[nd];
   	int[] res=walk(ny, nx, nd);
   	if(res==null) return null;
   	res[3]+=1;
   	return res;
   }

   boolean valid(int y, int x) {
      return y>=0 && x>=0 && y<n && x<m;
   }
}
