package assignment_04.examples;

import assignment_04.mainpackage.Employee;
import assignment_04.mainpackage.EmployeeList;

/**
 * Call all public methods in EmployeeList to confirm they work
 */

public class TestEmployeeList {
	public static void main(String[] args) {
		System.out.println("Start TestEmployeeList:\n");
		
		EmployeeList TestList = new EmployeeList(5);
		Employee Adam = new Employee("Adam", "123456789", 30000.00);
		Employee Bob = new Employee("Bob", "987654321", 40000.00);
		Employee Carl = new Employee("Carl", "014725836", 50000.00);
		Employee Daniel = new Employee("Daniel", "741852963", 60000.00);
		Employee Eric = new Employee("Eric", "322654887", 70000.00);
		
		TestList.add(Adam);
		TestList.add(Bob);
		TestList.add(Carl);
		TestList.add(Daniel);
		TestList.add(Eric);
		
		System.out.println(TestList);
		
		Employee Frank = new Employee("Frank", "482512598", 80000.00);
		
		System.out.println("Attempt to add sixth employee, overflowing the array");
		TestList.add(Frank);
		
		System.out.println("\nEnd TestEmployeeList");
	}
}

/***************PASTE FROM CONSOLE***************
Start TestEmployeeList:

Name: 'Adam', SSN: 123456789, Salary: 30000.0
Name: 'Bob', SSN: 987654321, Salary: 40000.0
Name: 'Carl', SSN: 014725836, Salary: 50000.0
Name: 'Daniel', SSN: 741852963, Salary: 60000.0
Name: 'Eric', SSN: 322654887, Salary: 70000.0

Attempt to add sixth employee, overflowing the array
Error: array has been filled!

End TestEmployeeList

***************End TestEmployeeList**************/