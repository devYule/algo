function solution(name, yearning, photo) {
    const ret=new Array(photo.length).fill(0);
    for(let i=0; i<photo.length; i++) {
        let sum=0;
        for(let j=0; j<photo[i].length; j++) {
            const nm=photo[i][j];
            for(let k=0; k<name.length; k++) {
                if(name[k]===nm) {
                    sum+=yearning[k];
                    break;
                }
            }
        }
        ret[i]=sum;
    }
    return ret;
}