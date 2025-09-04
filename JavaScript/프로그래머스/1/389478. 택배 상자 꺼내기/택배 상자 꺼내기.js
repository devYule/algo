function solution(n, w, num) {
    const height=parseInt(n/w) + (n%w!==0 ? 1 : 0);
    
    const map=Array.from({length: height}, () => Array(w).fill(0));
    let order=1;
    let from=[];
    for(let i=0; i<height; i++) {
        for(let j=0; j<w; j++) {
            let mv=j;
            if(i%2!==0) mv=w-j-1;
            map[i][mv]=order++;
            if(map[i][mv]===num) {
                from[0]=i;
                from[1]=mv;
            }
            if(order>n) break;
        }
        if(order>n) break;
    }
    
    let ret=0;
    for(let i=from[0]; i<height; i++) {
        if(map[i][from[1]]==0) break;
        ret++;
    }
    return ret;
}