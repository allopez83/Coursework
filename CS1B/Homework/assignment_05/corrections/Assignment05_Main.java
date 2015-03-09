package assignment_05.corrections;

import java.util.Collections;
import java.util.LinkedList;

/**
 * Tests the use of an Employee LinkedList by trying out different functions
 */
public class Assignment05_Main {
	private final static boolean TESTING = false;
	
	/**
	 * Calls LinkedList and tries using Employee objects with it
	 */
	public static void main(String[] args) {
		if (TESTING) System.out.println("Starting test\n");

		
		LinkedList<Employee> linkedEmployee = new LinkedList<Employee>();

		// NOTE THAT EMPLOYEE SSN ARE 4 DIGIT IN THIS TEST, REAL SSN IS 9 DIGITS
		Employee Joe, Rob, Eric, Harris;
		Joe = new Employee("Joe", "1117", 100.00);
		Rob = new Employee("Rob", "1115", 150.00);
		Eric = new Employee("Eric", "1113", 200.00);
		Harris = new Employee("Harris", "1111", 250.00);

		linkedEmployee.add(Joe);
		linkedEmployee.add(Rob);
		linkedEmployee.add(Eric);
		linkedEmployee.add(Harris);
		
		if (TESTING) System.out.println("As the Employee list is in the beginning");
		System.out.println(linkedEmployee);
		
		if (TESTING) System.out.println("\nAfter sorting");
		Collections.sort(linkedEmployee);
		System.out.println(linkedEmployee);
		
		if (TESTING) System.out.println("\nUser search for employee");
		System.out.println("Is there an employee with an SSN of '1117'? " + linkedEmployee.contains(new Employee(null, "1117", 0.00)));
		System.out.println("Is there an employee with an SSN of '1114'? " + linkedEmployee.contains(new Employee(null, "1114", 0.00)));
		
		if (TESTING) System.out.println("\nTest completed");
	}
}

/************ PASTE FROM CONSOLE ************
[Name: 'Joe', SSN: 1117, Salary: 100.0, Name: 'Rob', SSN: 1115, Salary: 150.0, Name: 'Eric', SSN: 1113, Salary: 200.0, Name: 'Harris', SSN: 1111, Salary: 250.0]
[Name: 'Eric', SSN: 1113, Salary: 200.0, Name: 'Harris', SSN: 1111, Salary: 250.0, Name: 'Joe', SSN: 1117, Salary: 100.0, Name: 'Rob', SSN: 1115, Salary: 150.0]
Is there an employee with an SSN of '1117'? true
Is there an employee with an SSN of '1114'? false

********************************************/