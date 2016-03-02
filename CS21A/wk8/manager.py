""" Manager object represents a manager, subclass of employee, with added title and bonus
"""

import unittest
from functools import total_ordering
from employee import Employee

@total_ordering
class Manager(Employee):
    """ Create manager object, requiring first and last name, ssn, and salary for employee parent class, as well as string title for manager's title and double bonus for the annual bonus received.
    """
    def __init__(self, firstName="first", lastName="last", ssn=1111, salary=10000, title="manager", bonus=1000):
        Employee.__init__(self, firstName, lastName, ssn, salary)
        self.title = title
        self.bonus = bonus

    """ Represents a manager object in string form, with first and last name, ssn, salary, title, and bonus
    """
    def __str__(self):
        return Employee.__str__(self) + ", title: " + self.title + ", bonus: " + str(self.bonus)

""" Test magic methods for Manager, should be inherited from Employee
"""
class ManagerTests(unittest.TestCase):
    """ Tests __eq__ magic method
    """
    def testEq(self):
        orig = Manager(firstName="A", lastName="B")
        sameName = Manager(firstName="A", lastName="B")
        diffCase = Manager(firstName="a", lastName="b")
        diffLastName = Manager(firstName="A", lastName="C")
        diffFirstName = Manager(firstName="C", lastName="B")
        self.assertTrue(orig == sameName and orig == diffCase)
        self.assertFalse(orig == diffLastName)
        self.assertFalse(orig == diffFirstName)

    """ Tests __lt__ magic method
    """
    def testLt(self):
        lowName = Manager(firstName="B", lastName="B")
        highName = Manager(firstName="Y", lastName="Y")
        sameName = Manager(firstName="B", lastName="B")
        lowerFirstName = Manager(firstName="A", lastName="B")
        self.assertTrue(lowName < highName and highName > lowName)
        self.assertTrue(lowName <= sameName and lowName >= sameName and lowName == sameName)
        self.assertTrue(lowerFirstName < lowName and lowName > lowerFirstName)

if __name__ == "__main__":
    unittest.main()
