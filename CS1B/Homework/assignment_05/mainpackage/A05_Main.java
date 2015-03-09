package assignment_05.mainpackage;

import java.util.Collections;
import java.util.LinkedList;

/**
 * Tests the use of an Employee LinkedList by trying out different functions
 */
public class A05_Main {
	public final static boolean TESTING = false;

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
		EmployeeConsoleView console = new EmployeeConsoleView();
		console.contains(linkedEmployee);
		
		if (TESTING) System.out.println("\nTest completed");
	}
}