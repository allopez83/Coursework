package assignment_06.export;

public class A06_Main {
	public static final boolean TESTING = false;	//Clean up when turning in

	public static void main(String[] args) {
		if (TESTING) System.out.println("Begin testing\n");

		EmployeeList empList = new EmployeeList(15);

		Employee Albert, Bill, Calvin, David, Danny, Joe1, Rob, Eric, Harris, Joe2;
		David = new Employee("David", "101", 500.00);
		Albert = new Employee("Albert", "102", 100.00);
		Danny = new Employee("Danny", "103", 400.00);
		Joe1 = new Employee("Joe", "104", 802.00);
		Bill = new Employee("Bill", "105", 200.00);
		Calvin = new Employee("Calvin", "106", 300.00);
		Rob = new Employee("Rob", "107", 900.00);
		Eric = new Employee("Eric", "108", 600.00);
		Harris = new Employee("Harris", "109", 700.00);
		Joe2 = new Employee("Joe", "110", 801.00);

		/*
		 * Note that their add order is the same as their SSN order. Their
		 * salary is ordered the same as their name is alphabetically, which is
		 * the intended sort order. Feel free to use this when comparing their
		 * original and sorted order.
		 */
		empList.add(David);
		empList.add(Albert);
		empList.add(Danny);
		empList.add(Joe1);
		empList.add(Bill);
		empList.add(Calvin);
		empList.add(Rob);
		empList.add(Eric);
		empList.add(Harris);
		empList.add(Joe2);

		if (TESTING) System.out.println("/**** Original EmployeeList ****/");	// empList is shown as it is originally
		System.out.println(empList);
		
		if (TESTING) System.out.println("/**** Equals Test ****/");		// First two should be true, last two false
		System.out.println(Albert.equals(new Employee(null, "102", 0.00)));
		System.out.println(Albert.equals(Albert));
		
		System.out.println(Albert.equals(new Employee(null, "999", 0.00)));
		System.out.println(Albert.equals(Harris) + "\n");
		
		if (TESTING) System.out.println("/**** Contains Test ****/");	// First two should be true, last two false
		System.out.println(empList.contains(new Employee(null, "105", 0.00)));
		System.out.println(empList.contains(Calvin));

		Employee Kevin = new Employee();
		System.out.println(empList.contains(new Employee(null, "999", 0.00)));
		System.out.println(empList.contains(Kevin) + "\n");
		
		if (TESTING) System.out.println("/**** Sort Test ****/");	// empList should be sorted alphabetically
		empList.sort();
		System.out.println(empList);
		
		if (TESTING) System.out.print("\nTesting Done");
	}
}

/************PASTE FROM CONSOLE************
Name: 'David', SSN: 101, Salary: 500.0
Name: 'Albert', SSN: 102, Salary: 100.0
Name: 'Danny', SSN: 103, Salary: 400.0
Name: 'Joe', SSN: 104, Salary: 802.0
Name: 'Bill', SSN: 105, Salary: 200.0
Name: 'Calvin', SSN: 106, Salary: 300.0
Name: 'Rob', SSN: 107, Salary: 900.0
Name: 'Eric', SSN: 108, Salary: 600.0
Name: 'Harris', SSN: 109, Salary: 700.0
Name: 'Joe', SSN: 110, Salary: 801.0

true
true
false
false

true
true
false
false

Name: 'Albert', SSN: 102, Salary: 100.0
Name: 'Bill', SSN: 105, Salary: 200.0
Name: 'Calvin', SSN: 106, Salary: 300.0
Name: 'Danny', SSN: 103, Salary: 400.0
Name: 'David', SSN: 101, Salary: 500.0
Name: 'Eric', SSN: 108, Salary: 600.0
Name: 'Harris', SSN: 109, Salary: 700.0
Name: 'Joe', SSN: 104, Salary: 802.0
Name: 'Joe', SSN: 110, Salary: 801.0
Name: 'Rob', SSN: 107, Salary: 900.0


**************END OF TEST RUN*************/