""" Manager object represents a manager, subclass of employee, with added title and bonus
"""

from employee import Employee

class Manager(Employee):
    """ Create manager object, requiring first and last name, ssn, and salary for employee parent class, as well as string title for manager's title and double bonus for the annual bonus received.
    """
    def __init__(self, firstName, lastName, ssn, salary, title, bonus):
        Employee.__init__(self, firstName, lastName, ssn, salary)
        self.title = title
        self.bonus = bonus

    """ Represents a manager object in string form, with first and last name, ssn, salary, title, and bonus
    """
    def __str__(self):
        return Employee.__str__(self) + ", title: " + self.title + ", bonus: " + str(self.bonus)

if __name__ == "__main__":
    m1 = Manager("Bob", "Last", 2222, 100000, "Project Lead", 5000)
    print(m1)
    m1.giveRaise(.2)
    print(m1)
