/**
   This question uses JavaScript.

   In the preceding problem, you called 

   return as.filter(...).reduce(function(previous, current) { ... }, "")

   where function(previous, current) separated previous and current
   with ", ".

   What if we want a different separator? Write a function join that
   returns a function for joining elements with a given separator,
   and taking care of the special case where previous is "".

   For example, one should be able to solve the preceding problem as

   return as.filter(...).reduce(join(", "), "")

*/
function join(separator) {
   var posToString = function(as) {
       var result = as.filter(pos)
       .reduce(function(previous, current) {
               // console.log(previous);
               // console.log(current);
               return previous + separator + current;
           }, "");
       result = result.substring(2,result.length);
       return result;
   };
   return posToString
};

var pos = function(num) {
    return num > 0;
};

console.log(join(", ")("3", 4))
