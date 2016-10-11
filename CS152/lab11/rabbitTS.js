var Rabbit = (function () {
    function Rabbit(name) {
        this.name = name;
    }
    return Rabbit;
})();
// Do not use `let`
var r = new Rabbit("Python");
var name = "Monty";
console.log(name);
console.log(r.name);
