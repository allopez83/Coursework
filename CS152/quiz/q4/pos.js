/**
   This question uses JavaScript.

   You are given an array of numbers. Return a string consisting of 
   all POSITIVE numbers, separated by ", " (a comma and a space).
   DO NOT use loops. Use filter and reduce.

   Hint: In JavaScript, like in Java, you can turn a number n into 
   a string by concatenating with an empty string, "" + n.
*/
function posToString(as) {
    var result = as.filter(pos)
    .reduce(function(previous, current) {
            // console.log(previous);
            // console.log(current);
            return previous + ", " + current;
        }, "");
    result = result.substring(2,result.length);
    return result;
};

var pos = function(num) {
    return num > 0;
};

// console.log(pos(1))
console.log(posToString([-1]));
