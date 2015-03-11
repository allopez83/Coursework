package assignment_08.mainpackage;

import java.io.Serializable;

/**
 * Keeps track of data regarding an employee
 * Information is stored into the object Employee
 * 
 * @author HW
 */
public class Employee implements Comparable<Employee>, Serializable {
	//TODO Set to false when turning in
	private static final boolean TESTING_CONSTRUCTOR = false,
								 TESTING_DEFAULT_CONSTRUCTOR = false,
								 TESTING_CALLCOUNT = true,
								 TESTING_EQUALS = false,
			 					 TESTING_COMPARETO = false,
			 					 TESTING_RAISE = false;
	/**
	 * Serial Version ID for Employee class
	 */
	protected static final long serialVersionUID = 61885L;
	
	private static int employeeCallCount;
	
	private String name;
	private String ssn;
	private double salary;
	
	//Employee Name
	/**
	 * Return an employee's name
	 * @return name of an employee
	 */
	protected String name(){
		return name;
	}
	/**
	 * Sets an employee's name
	 * @param newName name that an employee will change to
	 */
	protected void name(String newName){
		name = newName;
	}
	
	//Employee SSN
	/**
	 * Return an employee's SSN
	 * @return SSN of an employee
	 */
	protected String ssn(){
		return ssn;
	}
	/**
	 * Sets an employee's SSN
	 * @param newSSN ssn that an employee will change to
	 */
	protected void ssn(String newSSN){
		ssn = newSSN;
	}
	
	//Employee Salary
	/**
	 * Return an employee's salary
	 * @return salary of an Employee
	 */
	protected double salary(){
		return salary;
	}
	/**
	 * Sets an employee's salary
	 * @param newSalary salary that an employee will change to
	 */
	protected void salary(double newSalary){
		salary = newSalary;
	}
	
	/**
	 * Creates an Employee object with the provided parameters
	 * @param initName name of employee
	 * @param initSSN SSN of employee
	 * @param initSalary salary of employee
	 */
	void initialize(String initName, String initSSN, double initSalary){
		name(initName);
		ssn(initSSN);
		salary(initSalary);
	}
	/**
	 * Creates an Employee object using default parameters
	 */
	public Employee(){
		if (TESTING_DEFAULT_CONSTRUCTOR) System.out.println(" > Employee default constructor reached");
		initialize("unknown_name", "000000000", 0.00);
		employeeCallCount++;
		if (TESTING_CALLCOUNT) System.out.println(" > Created Employee #" + employeeCallCount);
	}
	/**
	 * Creates an Employee object with provided parameters
	 * @param initName name of employee
	 * @param initSSN SSN of employee
	 * @param initSalary salary of employee
	 */
	public Employee(String inputName, String inputSSN, double inputSalary){
		if (TESTING_CONSTRUCTOR) System.out.println(" > Employee constructor reached");
		initialize(inputName, inputSSN, inputSalary);
		employeeCallCount++;
		if (TESTING_CALLCOUNT) System.out.println(" > Created Employee #" + employeeCallCount);
	}

	/**
	 * Automatically returns data in an Employee object as a string
	 */
	public String toString(){
		return ("Name: '" + name() + "', SSN: " + ssn() + ", Salary: " + salary());
	}
	/**
	 * Returns the number of times an Employee object has been created
	 * @return number of times an Employee object has been created
	 */
	public static int getCallCount() {
		return employeeCallCount;
	}
	
	/**
	 * Compares two Employee objects by their name and returns an int representation of the comparison
	 */
	public int compareTo(Employee empl) {
		if (TESTING_COMPARETO) System.out.println("Current (this.name): " + this.name + "\nTarget (empl.name): " + empl.name + "\nComparison: " + (this.name()).compareTo(empl.name()));
		return (this.name()).compareTo(empl.name());
	}
	/**
	 * Checks if two Employee objects match by comparing their SSN
	 */
	public boolean equals(Object obj) {
		if (TESTING_EQUALS) System.out.println("Current subject (this.ssn): " + this.ssn + "\nTarget (searchEmpl.ssn): " + ((Employee)obj).ssn);
		return ((this.ssn).equals(((Employee)obj).ssn));
	}
	/**
	 * Creates a deep copy of an existing Employee object
	 */
	public Object clone(){
		return new Employee(name, ssn, salary);
	}
	
	public void raise(double raisePercent){
		if (TESTING_RAISE) System.out.println(" > Employee.raise() reached with raise value: " + raisePercent);
		salary += raisePercent*salary;
	}
}
