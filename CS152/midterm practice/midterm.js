function mystery(n) {
    var curPos = 2, lst = [], i;
    for (i=curPos; i<n; i++) lst[i]=i;
    return function() {
        var x;
        while (lst[curPos] === undefined) {
            if (curPos > n) throw new Error("Nothing left");
            curPos++;
            console.log("curPos1: " + curPos);
        }
        x = curPos;
        while (x < n) {
            delete lst[x];
            x += curPos;
            console.log("curPos2: " + curPos);
        }
        return curPos;
    }
}

var next = mystery(25);
for (i=0; i<5; i++) {
    console.log(next());
}
