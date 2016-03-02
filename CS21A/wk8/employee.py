""" An employee object represents an employee at company, with a first name, last name, social security number, and salary
"""
class Employee:
    """ Create an employee with the given firstName and lastName string, social security number int as ssn, and salary double
    """
    def __init__(self, firstName, lastName, ssn, salary):
        self.firstName = firstName
        self.lastName = lastName
        self.ssn = ssn
        self.salary = salary

    """ Represents an employee object in string form, with first and last name, ssn, and salary, in that order.
    """
    def __str__(self):
        return self.firstName + " " + self.lastName + ", ssn: " + str(self.ssn) + ", salary: " + str(self.salary)

    """ Increases an employee's salary by a specified percentage, where 0.25 is 25%
    """
    def giveRaise(self, percentRaise):
        self.salary += self.salary * percentRaise

if __name__ == "__main__":
    e1 = Employee("Adam", "Last", 1111, 60000)
    print(e1)
    e1.giveRaise(.2)
    print(e1)
