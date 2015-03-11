package assignment_02.corrections;




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
		
		System.out.println( Employee.getCallCount() );
		
		System.out.print("End of Testing");
	}
}

/* Paste of run from console:
Begin Testing
Name: 'unknown_name', SSN: 0, Salary: 0.0
Name: 'Billy John', SSN: 123456789, Salary: 30.0
End of Testing
*/