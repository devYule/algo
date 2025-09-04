function solution(cards1, cards2, goal) {
    
    for(const tk of goal) {
        if(cards1[0]===tk) cards1.shift();
        else if(cards2[0]===tk) cards2.shift();
        else return "No";
    }
    return "Yes";
}