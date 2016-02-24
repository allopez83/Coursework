from employee import Employee
from manager import Manager

employeeList = []
employeeList.append(Employee("Adam", "Bobs", 1111, 60000))
employeeList.append(Employee("Carly", "Davis", 2222, 70000))
employeeList.append(Employee("Daniel", "Evans", 3333, 80000))
employeeList.append(Manager("Fredrick", "Gall", 4444, 90000, "Project Manager", 10000))
employeeList.append(Manager("Harriot", "Truman", 5555, 100000, "Division Head", 20000))
print("Original Employees:")
for employee in employeeList:
    print(employee)
# Oh boy, everyone is getting rich today!
for employee in employeeList:
    employee.giveRaise(.2)
print("\nAfter raise of 20% for everyone:")
for employee in employeeList:
    print(employee)

""" Program Output
Original Employees:
Adam Bobs, ssn: 1111, salary: 60000
Carly Davis, ssn: 2222, salary: 70000
Daniel Evans, ssn: 3333, salary: 80000
Fredrick Gall, ssn: 4444, salary: 90000, title: Project Manager, bonus: 10000
Harriot Truman, ssn: 5555, salary: 100000, title: Division Head, bonus: 20000

After raise of 20% for everyone:
Adam Bobs, ssn: 1111, salary: 72000.0
Carly Davis, ssn: 2222, salary: 84000.0
Daniel Evans, ssn: 3333, salary: 96000.0
Fredrick Gall, ssn: 4444, salary: 108000.0, title: Project Manager, bonus: 10000
Harriot Truman, ssn: 5555, salary: 120000.0, title: Division Head, bonus: 20000
"""
