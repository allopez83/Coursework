package assignment_08.mainpackage;


/**
 * Tests the new Employee class along with the public methods in its two
 * subclasses: Manager and Worker
 * 
 * @author HW
 */
public class Main {
	//TODO Set to false when turning in
	public static final boolean TESTING = false;
	
	public static void main(String args[]){
		if (TESTING) System.out.println("Begin testing\n");
		
		if (TESTING) System.out.println("\n/**** Manager testing ****/");
		Manager Andy = new Manager();
		System.out.println(Andy);
		Andy = new Manager("Andy", "101", 200.00, 50.00, "Project Supervisor");
		System.out.println(Andy);
		
		if (TESTING) System.out.println("/**** Worker testing ****/");
		Worker Bill = new Worker();
		System.out.println(Bill);
		Bill = new Worker("Bill", "102", 100.00, "Andy", "R&D");
		System.out.println(Bill + "\n");
		
		if (TESTING) System.out.println("\n/**** Raise testing ****/");
		Employee Carl = new Employee("Carl", "103", 100.00);
		
		System.out.println(Andy);
		Andy.raise(0.20);	// Raise by 20%
		System.out.println(Andy + "\n");
		
		System.out.println(Bill);
		Bill.raise(0.20);
		System.out.println(Bill + "\n");
		
		System.out.println(Carl);
		Carl.raise(0.20);
		System.out.println(Carl + "\n");
		
		if (TESTING) System.out.println("\n/**** Manager/Worker testing with equals() ****/");
		// All of these should be false
		System.out.println("Andy equals Bill: " + Andy.equals(Bill));
		System.out.println("Bill equals Carl: " + Bill.equals(Carl));
		System.out.println("Carl equals Andy: " + Carl.equals(Andy) + "\n");
		
		// All of these should be true
		Employee andyTest = new Manager(null, "101", 0.00, 0.00, null);
		System.out.println("Andy equals Manager with equivalent SSN: " + Andy.equals(andyTest));
		andyTest = null;
		andyTest = new Employee(null, "101", 0.00);
		System.out.println("Andy equals Employee with equivalent SSN: " + Andy.equals(andyTest) + "\n");
		andyTest = null;
		
		// All of these should be true
		Employee billTest = new Worker(null, "102", 0.00, null, null);
		System.out.println("Bill equals Worker with equivalent SSN: " + Bill.equals(billTest));
		billTest = null;
		billTest = new Employee(null, "102", 0.00);
		System.out.println("Bill equals Employee with equivalent SSN: " + Bill.equals(billTest) + "\n");
		billTest = null;
		//TODO finish equals testing
		
		System.out.println("Total Employee call counts: " + Employee.getCallCount());
		
		if (TESTING) System.out.print("\nTesting done");
	}
}

/************PASTE FROM CONSOLE************
 > Created Employee #1
Name: 'unknown_name', SSN: 000000000, Salary: 0.0, Bonus: 0.0, Title: unknown_title
 > Created Employee #2
Name: 'Andy', SSN: 101, Salary: 200.0, Bonus: 50.0, Title: Project Supervisor
 > Created Employee #3
Name: 'unknown_name', SSN: 000000000, Salary: 0.0, Boss: unknown_boss, Department: unknown_department
 > Created Employee #4
Name: 'Bill', SSN: 102, Salary: 100.0, Boss: Andy, Department: R&D

 > Created Employee #5
Name: 'Andy', SSN: 101, Salary: 200.0, Bonus: 50.0, Title: Project Supervisor
Name: 'Andy', SSN: 101, Salary: 240.0, Bonus: 50.0, Title: Project Supervisor

Name: 'Bill', SSN: 102, Salary: 100.0, Boss: Andy, Department: R&D
Name: 'Bill', SSN: 102, Salary: 120.0, Boss: Andy, Department: R&D

Name: 'Carl', SSN: 103, Salary: 100.0
Name: 'Carl', SSN: 103, Salary: 120.0

Andy equals Bill: false
Bill equals Carl: false
Carl equals Andy: false

 > Created Employee #6
Andy equals Manager with equivalent SSN: true
 > Created Employee #7
Andy equals Employee with equivalent SSN: true

 > Created Employee #8
Bill equals Worker with equivalent SSN: true
 > Created Employee #9
Bill equals Employee with equivalent SSN: true

Total Employee call counts: 9

**************END OF PASTE*****************/
