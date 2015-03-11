package assignment_02.mainpackage;





/**
 * Tests the public methods in the Employee class
 */
public class TestEmployee {
	public static void main(String[] args){
		System.out.println("Begin Testing");
		
		Employee employeeDefaultValue;
		employeeDefaultValue = new Employee();
		System.out.println(employeeDefaultValue);
		
		Employee BillyJohn;
		BillyJohn = new Employee("Billy John", 123456789, 30.0);
		System.out.println(BillyJohn);
		
		
		
		System.out.println( Employee.callCount() );
		
		System.out.print("End of Testing");
	}
}

/* Paste of run from console:
Begin Testing
Name: 'unknown_name', SSN: 0, Salary: 0.0
Name: 'Billy John', SSN: 123456789, Salary: 30.0
End of Testing
*/

/*
The class name should describe what ONE object of the class represents. One object of class Employee represents ONE employee, so the class name should not be plural

I don't see the need for the return statement in the default constructor

Otherwise good job
*/