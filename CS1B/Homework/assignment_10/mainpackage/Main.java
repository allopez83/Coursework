package assignment_10.mainpackage;

public class Main {
	private static final boolean TESTING = false;

	public static void main(String[] args) {
		if (TESTING) System.out.println("Testing start\n");
		
		if (TESTING) System.out.println("/**** Adding Employee Objects Test ****/");
		LinkedList linkedEmployees = new LinkedList();
		Employee alex = new Employee("Alex", "1001", 50.00),
				 brian = new Employee("Brian", "1002", 50.00),
				 charlie = new Employee("Charlie", "1003", 50.00);
		linkedEmployees.addAtEnd(alex);
		linkedEmployees.addAtEnd(brian);
		linkedEmployees.addAtEnd(charlie);

		Manager daniel = new Manager("Daniel", "2001", 100.00, 20.00, "CEO"),
				eric = new Manager("Eric", "2002", 100.00, 20.00, "Project Manager"),
				fred = new Manager("Fred", "2003", 100.00, 20.00, "Department Head");
		linkedEmployees.addAtEnd(daniel);
		linkedEmployees.addAtEnd(eric);
		linkedEmployees.addAtEnd(fred);

		Worker george = new Worker("George", "3001", 40.00, "Jerry", "R&D"),
			   harry = new Worker("Harry", "3002", 40.00, "Kevin", "Product Survivability"),
			   ivan = new Worker("Ivan", "3003", 40.00, "Larry", "Advertising");
		linkedEmployees.addAtEnd(george);
		linkedEmployees.addAtEnd(harry);
		linkedEmployees.addAtEnd(ivan);
		
		System.out.println(linkedEmployees);

		if (TESTING) System.out.println("/**** Applying 10% Raise ****/");
		linkedEmployees.raise(0.10);
		System.out.println(linkedEmployees);


		if (TESTING) System.out.println("/**** LinkedList Expandability Example ****/");
		for (int i = 0; i < 30; i++) {
			linkedEmployees.addAtEnd(new Employee());
			System.out.println("Added Employee #" + (i + 1));
		}
		System.out.println("\nLinkedList now has 30 employees");

		if (TESTING) System.out.println("\nTesting End");
		
		/*
		if (TESTING) System.out.println("\nGUI Start");
		
		new GUIApp();
		
		if (TESTING) System.out.println("\nGUI End");
		*/
	}

}

/************PASTE FROM CONSOLE************
Name: 'Alex', SSN: 1001, Salary: 50.0
Name: 'Brian', SSN: 1002, Salary: 50.0
Name: 'Charlie', SSN: 1003, Salary: 50.0
Name: 'Daniel', SSN: 2001, Salary: 100.0, Bonus: 20.0, Title: CEO
Name: 'Eric', SSN: 2002, Salary: 100.0, Bonus: 20.0, Title: Project Manager
Name: 'Fred', SSN: 2003, Salary: 100.0, Bonus: 20.0, Title: Department Head
Name: 'George', SSN: 3001, Salary: 40.0, Boss: Jerry, Department: R&D
Name: 'Harry', SSN: 3002, Salary: 40.0, Boss: Kevin, Department: Product Survivability
Name: 'Ivan', SSN: 3003, Salary: 40.0, Boss: Larry, Department: Advertising

Name: 'Alex', SSN: 1001, Salary: 55.0
Name: 'Brian', SSN: 1002, Salary: 55.0
Name: 'Charlie', SSN: 1003, Salary: 55.0
Name: 'Daniel', SSN: 2001, Salary: 110.0, Bonus: 20.0, Title: CEO
Name: 'Eric', SSN: 2002, Salary: 110.0, Bonus: 20.0, Title: Project Manager
Name: 'Fred', SSN: 2003, Salary: 110.0, Bonus: 20.0, Title: Department Head
Name: 'George', SSN: 3001, Salary: 44.0, Boss: Jerry, Department: R&D
Name: 'Harry', SSN: 3002, Salary: 44.0, Boss: Kevin, Department: Product Survivability
Name: 'Ivan', SSN: 3003, Salary: 44.0, Boss: Larry, Department: Advertising

Added Employee #1
Added Employee #2
Added Employee #3
Added Employee #4
Added Employee #5
Added Employee #6
Added Employee #7
Added Employee #8
Added Employee #9
Added Employee #10
Added Employee #11
Added Employee #12
Added Employee #13
Added Employee #14
Added Employee #15
Added Employee #16
Added Employee #17
Added Employee #18
Added Employee #19
Added Employee #20
Added Employee #21
Added Employee #22
Added Employee #23
Added Employee #24
Added Employee #25
Added Employee #26
Added Employee #27
Added Employee #28
Added Employee #29
Added Employee #30

LinkedList now has 30 employees

**************END OF PASTE*****************/
