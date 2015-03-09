package assignment_03.mainpackage;

/**
 * Keeps track of employee information regarding their name, SSN, and salary
 * Information is stored into the object Employee
 */
public class Employee {
	private static int employeeCallCount;
	
	//Employee Name
	private String name;
	/**
	 * Return an employee's name
	 */
	private String name(){
		return name;
	}
	/**
	 * Sets an employee's name 
	 */
	private void name(String newName){
		name = newName;
	}
	
	//Employee SSN
	private String ssn;
	/**
	 * Return an employee's SSN
	 */
	private String ssn(){
		return ssn;
	}
	/**
	 * Sets an employee's SSN
	 */
	private void ssn(String newSSN){
		ssn = newSSN;
	}
	
	//Employee Salary
	private double salary;
	/**
	 * Return an employee's salary
	 */
	private double salary(){
		return salary;
	}
	/**
	 * Sets an employee's salary
	 */
	private void salary(double newSalary){
		salary = newSalary;
	}
	
	/**
	 * Creates an employee object with the given parameters
	 */
	void initialize(String initName, String initSSN, double initSalary){
		name(initName);
		ssn(initSSN);
		salary(initSalary);
	}
	/**
	 * Automatically returns data in an employee object as a string
	 */
	public String toString(){
		return ("Name: '" + name() + "', SSN: " + ssn() + ", Salary: " + salary());
	}
	/**
	 * Creates an employee object using default parameters
	 */
	public Employee(){
		initialize("unknown_name", "000-000-000", 0.00);
		employeeCallCount++;
	}
	/**
	 * Creates an employee object with provided parameters
	 */
	public Employee(String inputName, String inputSSN, double inputSalary){
		initialize(inputName, inputSSN, inputSalary);
		employeeCallCount++;
	}
	/**
	 * returns the number of times an employee object has been created
	 */
	public static int getCallCount() {
		return employeeCallCount;
	}
}