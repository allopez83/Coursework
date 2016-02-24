"""
Part I:

Define a class Employee where one object of class Employee represents one Employee. Each Employee object will have:

• instance variable for first name

• instance variable for last name

• instance variable for social security number

• instance variable for salary

•  __init__() instance method as the initializer/constructor

•  __str__() instance method to return the object's data in a string

• giveRaise() instance method that takes a double "percentRaise" as parameter and doesn't return anything. When you call this method and send it a "percentRaise", this method adds "percentRaise" percent of the current salary to the salary instance variable of the object that calls it, thus giving that Employee a raise in salary.

Test each method one at a time. Make sure that all variables and methods work as expected before proceeding.

Part II:

Define a second class named "Manager" that is a subclass of class Employee with 2 additional instance variables and two additional methods:

• instance variable for the title of the manager

• instance variable for the dollar amount of annual bonus

• __init__() instance method as the constructor

• __str__() instance method to return the object's data in a string

Do not define a method called giveRaise() in class Manager. Instead, test that you can call giveRaise() on objects of class Manager because it is a subclass of class Employee.

Part III:

Create a list and add Employee objects and Manager objects to the same list. Use a loop to give a raise() to every object in the list.

NOTES:

• Create a third file that contains the test program. Your test program contains enough lines to prove that every method works on both types of objects. If your test program becomes too long, split it up into separate methods that your test program can call when it is testing different features of the two classes.

• Make sure that you use mnemonic variable names

• You only need to define the method giveRaise() on the superclass Employee because it will work exactly the same way on objects of both classes. This is because Managers are Employees, so Manager objects can call Employee methods. Thoroughly test that the one giveRaise() method that you defined in class Employee works on both types of Objects: Employees and Managers.

• In order to earn the most points, make one final code review to make sure you have followed all program guidelines.

• Submit three files, employee.py, manager.py and whatever file you have your test program in.

"""