""" Tests employee and manager to ensure magic methods are functioning properly
"""

import unittest
from employee import Employee
from manager import Manager

employeeList = []

# Generic alphabetical employees
employeeList.append(Employee("Adam", "Bobs"))
employeeList.append(Employee("Carly", "Davis"))
employeeList.append(Employee("Daniel", "Evans"))
employeeList.append(Manager("Fredrick", "Gall"))
employeeList.append(Manager("Harriot", "Truman"))
# Mixed employees
employeeList.append(Employee("ZZZ", "ZZZ"))
employeeList.append(Manager("Zachary", "Simmons"))
employeeList.append(Employee("David", "Gonzales"))
employeeList.append(Employee("GenericName", "Taylor"))
employeeList.append(Manager("Emma", "Taylor"))
employeeList.append(Employee("Andy", "Davis"))
employeeList.append(Employee("AAA", "AAA"))

print("Original Employees:")
for employee in employeeList:
    print(employee)
# Get tidy!
print("\nAfter sorting:")
employeeList.sort()
employeeList.sort()
employeeList.sort()
employeeList.sort()
for employee in employeeList:
    print(employee)
print("\nLast, first format to see it's sorted:")
for employee in employeeList:
    print(employee.lastName + ", " + employee.firstName)



""" Program Output
Original Employees:
Adam Bobs, ssn: 1111, salary: 10000
Carly Davis, ssn: 1111, salary: 10000
Daniel Evans, ssn: 1111, salary: 10000
Fredrick Gall, ssn: 1111, salary: 10000, title: manager, bonus: 1000
Harriot Truman, ssn: 1111, salary: 10000, title: manager, bonus: 1000
ZZZ ZZZ, ssn: 1111, salary: 10000
Zachary Simmons, ssn: 1111, salary: 10000, title: manager, bonus: 1000
David Gonzales, ssn: 1111, salary: 10000
GenericName Taylor, ssn: 1111, salary: 10000
Emma Taylor, ssn: 1111, salary: 10000, title: manager, bonus: 1000
Andy Davis, ssn: 1111, salary: 10000
AAA AAA, ssn: 1111, salary: 10000

After sorting:
AAA AAA, ssn: 1111, salary: 10000
Adam Bobs, ssn: 1111, salary: 10000
Andy Davis, ssn: 1111, salary: 10000
Carly Davis, ssn: 1111, salary: 10000
Daniel Evans, ssn: 1111, salary: 10000
Fredrick Gall, ssn: 1111, salary: 10000, title: manager, bonus: 1000
David Gonzales, ssn: 1111, salary: 10000
Zachary Simmons, ssn: 1111, salary: 10000, title: manager, bonus: 1000
Emma Taylor, ssn: 1111, salary: 10000, title: manager, bonus: 1000
GenericName Taylor, ssn: 1111, salary: 10000
Harriot Truman, ssn: 1111, salary: 10000, title: manager, bonus: 1000
ZZZ ZZZ, ssn: 1111, salary: 10000

Last, first format to see it's sorted:
AAA, AAA
Bobs, Adam
Davis, Andy
Davis, Carly
Evans, Daniel
Gall, Fredrick
Gonzales, David
Simmons, Zachary
Taylor, Emma
Taylor, GenericName
Truman, Harriot
ZZZ, ZZZ

"""