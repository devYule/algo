import java.util.*;
class Solution {
    int[] uf;
    String[] table;
    List<String> ret;
    public String[] solution(String[] commands) {
        this.ret=new ArrayList<>();
        this.table=new String[51*51];
        this.uf=new int[51*51];
        int id=0;
        for(int i=0; i<uf.length; i++) {
            uf[i]=i;
            table[i]="";
        }
        for(String c: commands) {
            String[] comm=c.split(" ");
            String flag=comm[0];
            try {
                int r1=toInt(comm[1]);
                int c1=toInt(comm[2]);
            
                if("UPDATE".equals(flag)) {
                    if(comm.length==4) update(r1, c1, comm[3]);
                    else update(comm[1], comm[2]);
                } else if("MERGE".equals(flag)) {
                    int r2=toInt(comm[3]);
                    int c2=toInt(comm[4]);
                    merge(r1, c1, r2, c2);
                } else if("UNMERGE".equals(flag)) {
                    unmerge(r1, c1);
                } else {
                    print(r1, c1);
                }
            } catch(NumberFormatException ignore) {
                update(comm[1], comm[2]);
            }
        }
        
        return ret.stream().toArray(String[]::new);
    }
    
    void update(int r, int c, String value) {
        int fc=find(r*51+c);
        table[fc]=value;
    }
    
    void update(String value1, String value2) {
        for(int i=0; i<uf.length; i++) {
            int fi=find(i);
            if(value1.equals(table[fi])) {
                table[fi]=value2;
            }
        }
    }
    
    void merge(int r1, int c1, int r2, int c2) {
        int a=r1*51+c1;
        int b=r2*51+c2;
        
        int fa=find(a);
        int fb=find(b);
        
        if(fa==fb) return;
        
        if(table[fa].isEmpty() && !table[fb].isEmpty()) {
            uf[fa]=fb;
        } else uf[fb]=fa;
    }
    
    int find(int a) {
        if(uf[a]==a) return a;
        return uf[a]=find(uf[a]);
    }
    
    
    void unmerge(int r, int c) {
        int inputi=r*51+c;
        int fi=find(inputi);
        String os=table[fi];
        
        List<Integer> mem=new ArrayList<>();
        for(int i=0; i<uf.length; i++) {
            if(find(i)==fi) mem.add(i);
        }
        for(int i=0; i<mem.size(); i++) {
            uf[mem.get(i)]=mem.get(i);
            table[mem.get(i)]="";
        }
        uf[inputi]=inputi;
        table[inputi]=os;
    }
    
    void print(int r, int c) {
        int i=r*51+c;
        int fi=find(i);
        String o=table[fi];
        this.ret.add(o.isEmpty() ? "EMPTY" : o);
    }
 
    
    int toInt(String s) {
        return Integer.parseInt(s);
    }

}