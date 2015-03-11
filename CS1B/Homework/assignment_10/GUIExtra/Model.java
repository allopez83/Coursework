package assignment_10.GUIExtra;

/**
 * Contains methods for View class, a program that manages Employee Objects.
 * These methods are involved in data-handling.
 * 
 * @author HW
 * 
 */
public class Model {
	private static LinkedList linkedEmployees = new LinkedList();
	
	/**
	 * Returns the LinkedList containing user created employees
	 * @return LinkedList containing the user's employees
	 */
	public static LinkedList LinkedList() {
		return linkedEmployees;
	}
	
	/*//// Employee Object Creation Methods ////*/
	/**
	 * Adds an Employee Object to the LinkedList
	 * @param inputName name of the Employee
	 * @param inputSSN SSN of the Employee
	 * @param inputSalary salary of the Employee
	 * @return Employee new Employee created from user input values
	 */
	public static Employee inputToEmployee(String inputName, String inputSSN, String inputSalary) {
		if ("".equals(inputName) || "".equals(inputSSN) || "".equals(inputSalary))
			throw new IllegalArgumentException("Fill in all the fields!");	//If one or more field is empty
		return new Employee(retrieveName(inputName), retrieveInt(inputSSN)+"", retrieveDouble(inputSalary));
	}
	/**
	 * Adds a Manager Object to the LinkedList
	 * @param inputName name of the Manager
	 * @param inputSSN SSN of the Manager
	 * @param inputSalary salary of the manager
	 * @param inputBonus Manager's bonus
	 * @param inputTitle Manager's title
	 * @return Manager new Manager created from user input values
	 */
	public static Manager inputToManager(String inputName, String inputSSN, String inputSalary, String inputBonus, String inputTitle) {
		if ("".equals(inputName) || "".equals(inputSSN) || "".equals(inputSalary) || "".equals(inputBonus) || "".equals(inputTitle))
			throw new IllegalArgumentException("Fill in all the fields!");	//If one or more field is empty
		return new Manager(retrieveName(inputName), retrieveInt(inputSSN)+"", retrieveDouble(inputSalary), retrieveDouble(inputBonus), inputTitle);
	}
	/**
	 * Adds a Worker Object to the LinkedList
	 * @param inputName name of the Worker 
	 * @param inputSSN SSN of the Worker
	 * @param inputSalary salary of the Worker
	 * @param inputBoss Worker's boss
	 * @param inputDepartment Worker's department
	 * @return Worker new Worker created from user input values
	 */
	public static Worker inputToWorker(String inputName, String inputSSN, String inputSalary, String inputBoss, String inputDepartment) {
		if ("".equals(inputName) || "".equals(inputSSN) || "".equals(inputSalary) || "".equals(inputBoss) || "".equals(inputDepartment))
			throw new IllegalArgumentException("Fill in all the fields!");	//If one or more field is empty
		return new Worker(retrieveName(inputName), retrieveInt(inputSSN)+"", retrieveDouble(inputSalary), inputBoss, inputDepartment);
	}

	/*////  Employee Object Handling Methods ////*/
	/**
	 * Adds a new Employee Object to the end of the LinkedList
	 * @param newEmployee that Employee Object that will be added
	 */
	public static void addEmplToList(Employee newEmployee) {
		linkedEmployees.addAtEnd(newEmployee);
	}
	/**
	 * Raises the salaries of all Employees in the user's LinkedList using LinkedList's raise() method
	 * @param percentRaise the percent by which to raise the salaries of all employees in the LinkedList 
	 */
	public static void raiseEmployees(double percentRaise) {
		linkedEmployees.raise(percentRaise);
	}

	/*//// Data Checking and Parsing Methods ////*/ 
	/**
	 * Takes a string input and checks if it is a name. Only one word allowed. Only capital and lower case letters are allowed.
	 * @param rawInput unaffected String from user that will be checked
	 * @return String the input after being verified it is a recognized name
	 * @throws IllegalArgumentException if input is not a recognized name
	 */
	public static String retrieveName(String rawInput) {
		if (false == rawInput.matches("[a-zA-Z]+"))
			throw new IllegalArgumentException("Unable to find an acceptable name in a required field!");
		return rawInput;
	}
	/**
	 * Takes a String input and checks if it is a number. No decimals allowed. Only numbers 0 through 9 are allowed.
	 * @param rawInput unaffected String from user that will be checked
	 * @return Int user input after converted and is confirmed to be consisted only of numbers
	 * @throws IllegalArgumentException if input is not a recognized number
	 */
	public static int retrieveInt(String rawInput) {
		if (false == rawInput.matches("[0-9]+"))
			throw new IllegalArgumentException("Unable to find an acceptable number in a required field!");
		return Integer.parseInt(rawInput);
	}
	/**
	 * Takes a string input and checks if it is a number. Decimals are allowed, but not required.
	 * @param rawInput unaffected String from user that will be checked
	 * @return Double input after converted into a double
	 * @throws IllegalArgumentException if input contains anything that makes it not a number or decimal
	 */
	public static double retrieveDouble(String rawInput) {
		String error = "Unable to find an acceptable number or decimal value in a required field!";
		if (rawInput.matches("[ ]*[.]*[ ]*"))
			throw new IllegalArgumentException(error);
		if (false == rawInput.matches("[0-9_.]+"))
			throw new IllegalArgumentException(error);
		return Double.parseDouble(rawInput);
	}
	
}
