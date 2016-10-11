class Rabbit {
    name: string;
    constructor(name: string) {
        this.name = name;
    }
}

// Do not use `let` or it conflicts with Rabbit.name
var r = new Rabbit("Python");
var name: string = "Monty";

console.log(name);
console.log(r.name);
