// Part 2) Object-oriented programming in JavaScript.
// Create a 'Student' constructor, like we did for Cat in class.
// It should have the following fields:
// *firstName
// *lastName
// *studentID
// *display -- A function that prints out the firstName, lastName, and studentID number.
//       To invoke it, you should call `student.display()`.

// Create an array of new students.
// Add a 'graduated' property to just one of your students.

// Now create another student **without** using the constructor.
// (In other words, use the object literal `{}` syntax).
// Set the prototype chain manually using the __proto__ field.
// Make sure the display method still works (without you having to add it to the object explicitly).

print = console.log
print("Hello World")

function student(firstName, lastName, studentID, display) {
    this.firstName = firstName
    this.lastName = lastName
    this.studentID = studentID
    this.display = function(){
        print(lastName + ', ' + firstName + ' #' + studentID)
    }
}

var s = new student("First", "Last", 123)

print(s.firstName)

students = []
for(var i = 0; i < 5; i++) {
    students[i] = new student("First", "Last", 100 + i)
}
students[2].graduated = true

students[5] = {
    "firstName": "First",
    "lastName": "Last",
    "studentID": 105,
    // display: function(){ print("stuff") },
    __proto__: student
}

for(var i = 0; i < students.length; i++) { students[i].display() }