import java.util.*;
class Solution {
    /*
        D x: 아래로(오른쪽으로) x칸 이동
        U x: 위로(왼쪽으로) x칸 이동
        C  : 선택 라인 삭제
        Z  : 실행 취소
    */
    
    Line cur;
    Stack<Line> st;
    boolean[] isDel;
    public String solution(int n, int k, String[] cmd) {
        this.isDel=new boolean[n];
        this.st=new Stack<>();
        Line prev=new Line(0);
        cur=prev;
        for(int i=1; i<n; i++) {
            Line l=new Line(i);
            prev.next=l;
            l.prev=prev;
            prev=l;
            if(k==i) this.cur=l;
        }
        
        for(String c: cmd) {
            if(c.length()==1) {
                delOrUndo(c);
            } else {
                String[] split=c.split(" ");
                move(split[0], toInt(split[1]));
            }
        }
        StringBuilder ret=new StringBuilder();
        for(int i=0; i<n; i++) {
            ret.append(isDel[i] ? "X" : "O");
        }
        return ret.toString();
    }
    
    void move(String flag, int nth) {
        int count=0;
        if("U".equals(flag)) {
            while(count++<nth && cur.prev!=null) cur=cur.prev;
        } else {
            while(count++<nth && cur.next!=null) cur=cur.next;
        }
    }
    
    void delOrUndo(String flag) {
        if("C".equals(flag)) {
            // 삭제하기
            Line c=cur;
            isDel[c.num]=true;
            // 0번행이 아닌 경우.
            // 지금 행을 제거하고, 이전 행을 우선 선택한다. (마지막 행일 경우에만 유효.)
            if(c.prev!=null) {
                c.prev.next=c.next;
                cur=c.prev;
            }
            // 마지막 행이 아닌 경우
            // 지금 행을 제거하고 다음 행을 선택한다.
            if(c.next!=null) {
                c.next.prev=c.prev;
                cur=c.next;
            }
            st.add(c);
        } else {
            // 복원하기
            Line d=st.pop();
            if(d.prev!=null) d.prev.next=d;
            if(d.next!=null) d.next.prev=d;
            isDel[d.num]=false;
        }
    }
    
    int toInt(String s) {
        return Integer.parseInt(s);
    }
    
    static class Line {
        Line next, prev;
        int num;
        Line(int num) {
            this.num=num;
        }
    }
    
}