function roHandler(obj) {
  return {
    delete:       function(name) { return obj[name]; },
    set:          function(receiver, name, val) { return true; },
    has:          function(name) { return name in obj; },
    hasOwn:       function(name) { return Object.prototype.hasOwnProperty.call(obj, name); },
    get:          function(receiver, name) { return obj[name]; },
    enumerate:    function() {
      var result = [];
      for (name in obj) { result.push(name); };
      return result;
    },
    keys: function() { return Object.keys(obj) }
  };
}

// An object representing information we want to make ready-only
var constantVals = {
  pi: 3.14,
  e: 2.718,
  goldenRatio: 1.30357
};


var p = Proxy.create(roHandler(constantVals));
console.log(p.pi);

delete p.pi;
console.log(p.pi);

p.pi = 3;
console.log(p.pi);

