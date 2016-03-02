""" An employee object represents an employee at company, with a first name, last name, social security number, and salary
"""

import unittest
from functools import total_ordering

@total_ordering
class Employee:
    """ Create an employee with the given firstName and lastName string, social security number int as ssn, and salary double
    """
    def __init__(self, firstName="first", lastName="last", ssn=1111, salary=10000):
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

    """ Determines if first and last names of employees self and other are the same
    """
    def __eq__(self, other):
        return (self.firstName + self.lastName).casefold() == (other.firstName + other.lastName).casefold()

    """ Determines if name of self is alphabetically less than the name of other, last name taking precedence
    """
    def __lt__(self, other):
        if self.lastName.casefold() < other.lastName.casefold():
            return True
        elif self.firstName.casefold() < other.firstName.casefold():
            return True
        else:
            return False


""" Test magic methods for employee
"""
class EmployeeTests(unittest.TestCase):
    """ Tests __eq__ magic method
    """
    def testEq(self):
        orig = Employee(firstName="A", lastName="B")
        sameName = Employee(firstName="A", lastName="B")
        diffCase = Employee(firstName="a", lastName="b")
        diffLastName = Employee(firstName="A", lastName="C")
        diffFirstName = Employee(firstName="C", lastName="B")
        self.assertTrue(orig == sameName and orig == diffCase)
        self.assertFalse(orig == diffLastName)
        self.assertFalse(orig == diffFirstName)
    
    """ Tests __lt__ magic method
    """
    def testLt(self):
        lowName = Employee(firstName="B", lastName="B")
        highName = Employee(firstName="Y", lastName="Y")
        sameName = Employee(firstName="B", lastName="B")
        lowerFirstName = Employee(firstName="A", lastName="B")
        self.assertTrue(lowName < highName and highName > lowName)
        self.assertTrue(lowName <= sameName and lowName >= sameName and lowName == sameName)
        self.assertTrue(lowerFirstName < lowName and lowName > lowerFirstName)

if __name__ == "__main__":
    unittest.main()
